/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.register;

import Interface.IDaoPlayer;
import View.login.*;
import access.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
public class register extends Application {
    private IDaoPlayer dao;
    
    public register(IDaoPlayer dao){
        this.dao = dao;
    }
    
    @Override
    public void start(Stage PrimaryStage) throws IOException {
        /*
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        
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
        FXMLLoader RegisterView = new FXMLLoader();
        RegisterView.setLocation(getClass().getResource("/View/register/register.fxml"));
        try{
            RegisterView.load();
        }catch(IOException ex){
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
        }
        RegisterController RG = RegisterView.getController();
        RG.setControllerPlayer(dao);
        Parent p = RegisterView.getRoot();
        Stage stage = new Stage();
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
