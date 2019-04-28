/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package access;

import Arquivo.CadastroArquivoPlayers;
import Controller.controllerPlayer;
import DAO.daoPlayer;
import Interface.IDaoPlayer;
import Model.player;
import View.login.LoginController;
import View.login.login;
import java.io.File;
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
public class Access extends Application {
    
    controllerPlayer CP;
    
    CadastroArquivoPlayers cadastroplayers;
    player playerArq;
    @Override
    public void start(Stage PrimaryStage) throws IOException {
        IDaoPlayer dao = new daoPlayer();
        CP = new controllerPlayer(dao);
        cadastroplayers = new CadastroArquivoPlayers();
        String arquivoPlayers = "Players.txt";
        File filePlayers = new File(arquivoPlayers);
        if(filePlayers.exists()){
            cadastroplayers.openToRead(arquivoPlayers);
            do{
                playerArq = cadastroplayers.leObjeto();
                if(playerArq != null){
                    CP.cadastrarPlayer(playerArq);
                    CP.adicionarPontuacaoEasy(playerArq);
                    CP.adicionarPontuacaoHard(playerArq);
                }
            }while(playerArq != null);
            cadastroplayers.closeAfterRead();
        }
        
        login loginView = new login(dao);
        loginView.start(PrimaryStage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
