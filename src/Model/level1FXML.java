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
public class level1FXML extends Application {
    
    private controllerPlayer CP;
    private IDaoPlayer dao;
    private player player;
    public level1FXML(IDaoPlayer dao, player player){
        this.dao = dao;
        this.player = player;
    }
    
    @Override
    public void start(Stage PrimaryStage) throws IOException {
        /*
        Parent root = FXMLLoader.load(getClass().getResource("level1FXML.fxml"));
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
        
        FXMLLoader Level1 = new FXMLLoader();
        Level1.setLocation(getClass().getResource("level1FXML.fxml"));
        try{
            Level1.load();
        }catch(IOException ex){
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        Level1FXMLController LFC = Level1.getController();
        LFC.setControllerPlayer(dao, player);
        Parent p = Level1.getRoot();
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
