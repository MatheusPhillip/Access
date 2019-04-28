/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.login.*;
import Controller.controllerPlayer;
import Interface.IDaoPlayer;
import View.menuPrincipal.menuPrincipal;
import access.*;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Phillip
 */
public class viewConclusao extends Application {
    
    
    private IDaoPlayer dao;
    private player player;
    public viewConclusao(IDaoPlayer dao, player player, String mensagem){
        this.dao = dao;
        this.player = player;
        this.mensagem = mensagem;
    }
    private String mensagem;
    private menuPrincipal menuPrincipal;
    private static final Font FONT = Font.font("", FontWeight.BOLD, 18);
    private static final Font titleFont = Font.font("", FontWeight.BOLD, 60);
    private level1FXML level1;
    private VBox menuBox;
    private int currentItem = 0;
    private Stage stage = new Stage();
    private ScheduledExecutorService bgThread = Executors.newSingleThreadScheduledExecutor();
    private ScoreBoard scoreBoard;
    private MenuDificuldade menuDificuldadeView;
    
    
    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(800, 600);
        
        Image cenario = new Image("/img/level1.png");
        ImageView fundo = new ImageView(cenario);
        fundo.setFitHeight(600);
        fundo.setFitWidth(800);

        String title = mensagem;
        HBox letters = new HBox(0);
        letters.setAlignment(Pos.CENTER);
        letters.setTranslateX(60);
        letters.setTranslateY(120);
        Text letter = new Text(title);
        letter.setFont(titleFont);
        letter.setFill(Color.BLACK);
        letters.getChildren().add(letter);
                
        MenuItem itemTryAgain = new MenuItem("Try Again");
        itemTryAgain.setOnActivate(() -> {
            try {
                menuDificuldadeView.start(stage);
            } catch (IOException ex) {
                Logger.getLogger(viewConclusao.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        MenuItem itemScore = new MenuItem("SCORE");
        itemScore.setOnActivate(() -> {
            try{
                scoreBoard.start(stage);
            }catch (IOException ex){
                Logger.getLogger(viewConclusao.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        MenuItem itemMenuPrincipal = new MenuItem("Menu Principal");
        itemMenuPrincipal.setOnActivate(() -> {
            try{
                menuPrincipal.start(stage);
            }catch (IOException ex){
                Logger.getLogger(viewConclusao.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        
        
        
        menuBox = new VBox(10,
                itemTryAgain,
                itemMenuPrincipal,
                itemScore);
        menuBox.setAlignment(Pos.TOP_CENTER);
        menuBox.setTranslateX(330);
        menuBox.setTranslateY(320);

        Text about = new Text("Obrigado por jogar");
        about.setTranslateX(50);
        about.setTranslateY(550);
        about.setFill(Color.GRAY);
        about.setFont(FONT);

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
        scoreBoard = new ScoreBoard(dao, player);
        level1 = new level1FXML(dao, player);
        menuPrincipal = new menuPrincipal(dao, player);
        menuDificuldadeView = new MenuDificuldade(dao, player);
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
