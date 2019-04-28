/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.login;

import Controller.controllerPlayer;
import Interface.IDaoPlayer;
import View.menuPrincipal.menuPrincipal;
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
public class login extends Application {
    
    public login(){
        
    }
    private controllerPlayer CP;
    private IDaoPlayer dao;
    public login(IDaoPlayer dao){
        this.dao = dao;
    }
    
    
    
    
    @Override
    public void start(Stage PrimaryStage) throws IOException {
        FXMLLoader LoginView = new FXMLLoader();
        LoginView.setLocation(getClass().getResource("/View/login/Login.fxml"));
        try{
            LoginView.load();
        }catch(IOException ex){
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        LoginController LG = LoginView.getController();
        LG.setControllerPlayer(dao);
        Parent p = LoginView.getRoot();
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
