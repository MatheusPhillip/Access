/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.menuPrincipal;

import Interface.IDaoPlayer;
import Model.MenuDificuldade;
import Model.ScoreBoard;
import Model.level1FXML;
import Model.player;
import View.login.login;
import access.*;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Phillip
 */
public class menuPrincipal extends Application {
    MediaPlayer mediaplayer;
    private IDaoPlayer dao;
    private static final Font FONT = Font.font("", FontWeight.BOLD, 18);
    private static final Font titleFont = Font.font("", FontWeight.BOLD, 60);
    private level1FXML level1;
    private VBox menuBox;
    private int currentItem = 0;
    private Stage stage = new Stage();
    private ScheduledExecutorService bgThread = Executors.newSingleThreadScheduledExecutor();
    private ScoreBoard scoreBoard;
    private player player;
    private MenuDificuldade menuDificuldadeView;
    private login loginView;
    
    public menuPrincipal(IDaoPlayer dao, player player){
        this.dao = dao;
        this.player = player;
    }
    
    
     
    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(800, 600);
        
        Image cenario = new Image("/img/level1.png");
        ImageView fundo = new ImageView(cenario);
        fundo.setFitHeight(600);
        fundo.setFitWidth(800);
        //fundo.setPreserveRatio(true);

        String title = "Access";
        HBox letters = new HBox(0);
        letters.setAlignment(Pos.CENTER);
        letters.setTranslateX(300);
        letters.setTranslateY(120);
        Text letter = new Text(title);
        letter.setFont(titleFont);
        letter.setFill(Color.BLACK);
        letters.getChildren().add(letter);

        MenuItem itemExit = new MenuItem("EXIT");
        itemExit.setOnActivate(() -> {
            try {
                mediaplayer.stop();
                loginView.start(stage);
            } catch (IOException ex) {
                Logger.getLogger(menuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
                
        MenuItem itemPlay = new MenuItem("PLAY");
        itemPlay.setOnActivate(() -> {
            try {
                mediaplayer.stop();
                menuDificuldadeView.start(stage);
            } catch (IOException ex) {
                Logger.getLogger(menuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        MenuItem itemScore = new MenuItem("SCORE");
        itemScore.setOnActivate(() -> {
            try{
                mediaplayer.stop();
                scoreBoard.start(stage);
            }catch (IOException ex){
                Logger.getLogger(menuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        menuBox = new VBox(10,
                itemPlay,
                itemScore,
                itemExit);
        menuBox.setAlignment(Pos.TOP_CENTER);
        menuBox.setTranslateX(360);
        menuBox.setTranslateY(320);

        Text about = new Text("Phillip");
        about.setTranslateX(50);
        about.setTranslateY(550);
        about.setFill(Color.GRAY);
        about.setFont(FONT);
        about.setOpacity(0.2);

        getMenuItem(0).setActive(true);

        root.getChildren().addAll(fundo, letters, menuBox, about);
        return root;
    }

    private MenuItem getMenuItem(int index) {
        return (MenuItem)menuBox.getChildren().get(index);
    }
    
    private static class MenuItem extends HBox {
        //private TriCircle c1 = new TriCircle(), c2 = new TriCircle();
        private Text text;
        private Runnable script;

        public MenuItem(String name) {
            super(15);
            setAlignment(Pos.CENTER);

            text = new Text(name);
            text.setFont(FONT);
            text.setEffect(new GaussianBlur(2));

            getChildren().addAll(text);
            setActive(false);
            setOnActivate(() -> System.out.println(name + " activated"));
        }
        
        public void setActive(boolean b) {
            //c1.setVisible(b);
            //c2.setVisible(b);
            text.setFill(b ? Color.WHITE : Color.RED);
        }

        public void setOnActivate(Runnable r) {
            script = r;
        }

        public void activate() {
            if (script != null)
                script.run();
        }
    }
    
    
    
    
    @Override
    public void start(Stage Primarystage) throws IOException {
        Media musicFile = new Media(this.getClass().getResource("/music/MenuSong.mpeg").toExternalForm());
        mediaplayer = new MediaPlayer(musicFile);
        mediaplayer.play();
        
        scoreBoard = new ScoreBoard(dao, player);
        level1 = new level1FXML(dao, player);
        menuDificuldadeView = new MenuDificuldade(dao, player);
        loginView = new login(dao);
        stage = Primarystage;
        
        Scene scene = new Scene(createContent());
        
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                if (currentItem > 0) {
                    getMenuItem(currentItem).setActive(false);
                    getMenuItem(--currentItem).setActive(true);
                }
            }

            if (event.getCode() == KeyCode.DOWN) {
                if (currentItem < menuBox.getChildren().size() - 1) {
                    getMenuItem(currentItem).setActive(false);
                    getMenuItem(++currentItem).setActive(true);
                }
            }

            if (event.getCode() == KeyCode.ENTER) {
                getMenuItem(currentItem).activate();
            }
        });
        
        
        stage.setScene(scene);
        /*
        stage.setOnCloseRequest(event -> {
            bgThread.shutdownNow();
        });
        */
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
