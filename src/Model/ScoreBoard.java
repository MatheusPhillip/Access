/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.controllerPlayer;
import Interface.IDaoPlayer;
import View.register.*;
import View.login.*;
import access.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Phillip
 */

    

public class ScoreBoard extends Application {
    
    private controllerPlayer CP;
    private player player;
    private IDaoPlayer dao;
    public ScoreBoard(IDaoPlayer dao, player player){
        this.dao = dao;
        this.player = player;
    }
    
    @Override
    public void start(Stage PrimaryStage) throws IOException {
        /*
        Parent root = FXMLLoader.load(getClass().getResource("ScoreBoard.fxml"));
        //stage.initStyle(StageStyle.UNDECORATED);
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        //Screen screen = Screen.getPrimary();
        //Rectangle2D bound = screen.getVisualBounds();
        //stage.setX(bound.getMinX());
        //stage.setY(bound.getMinY());
        //stage.setWidth(bound.getWidth());
        //stage.setHeight(bound.getHeight());
        //stage.setMaximized(true);
        
        stage.show();
        */
        FXMLLoader ScoreView = new FXMLLoader();
        ScoreView.setLocation(getClass().getResource("ScoreBoard.fxml"));
        try{
            ScoreView.load();
        }catch(IOException ex){
            Logger.getLogger(ScoreBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        ScoreBoardController SC = ScoreView.getController();
        SC.setControllerPlayer(dao, player);
        Parent p = ScoreView.getRoot();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(p));
        stage.show();
        PrimaryStage.close();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
