/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.login;

import Arquivo.CadastroArquivoPlayers;
import Controller.controllerPlayer;
import Interface.IDaoPlayer;
import Model.player;
import View.menuPrincipal.menuPrincipal;
import View.register.RegisterController;
import View.register.register;
import access.Access;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Phillip
 */
public class LoginController implements Initializable {

    @FXML
    private Label Warning;
    
    public LoginController(){
        
    }
    public LoginController(controllerPlayer CP){
       this.CP = CP; 
    }
    private ArrayList<player> Players;
    private IDaoPlayer dao;
    private controllerPlayer CP;
    private String mensagem;
    private menuPrincipal menu;
    private register signUpView = new register(dao);
    CadastroArquivoPlayers cadastroplayers = new CadastroArquivoPlayers();
    player playerArq;
    String arquivoPlayers = "Players.txt";
    File filePlayers = new File(arquivoPlayers);
    
    private player player;
    
    @FXML
    private PasswordField Password;
    @FXML
    private TextField Username;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void handleRegistesAction(ActionEvent event) {
        
        
        mensagem = "Usuario Cadastrado";
        System.out.println(mensagem);
        System.out.println(Username.getText());
        System.out.println(Password.getText());
        
    }

    @FXML
    private void handleCloseAction(MouseEvent event) {
        CP = new controllerPlayer(dao);
        cadastroplayers.openToWrite(arquivoPlayers);
        Players = CP.ArrrayPlayers();
        if(Players != null){
            for(int i = 0; i < Players.size(); i++){
                playerArq = Players.get(i);
                if(playerArq != null){
                    cadastroplayers.gravaObjeto(playerArq);
                }
            }
        }
        cadastroplayers.closeAfterWrite();
        System.exit(0);
    }

    @FXML
    private void OpenSignUpScene(ActionEvent event) throws IOException {
        
        FXMLLoader RegisterView = new FXMLLoader();
        RegisterView.setLocation(getClass().getResource("/View/register/register.fxml"));
        try{
            RegisterView.load();
        }catch(IOException ex){
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        RegisterController RG = RegisterView.getController();
        RG.setControllerPlayer(dao);
        Parent p = RegisterView.getRoot();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(p));
        stage.show();
       
    }

    @FXML
    private void handleLoginAction(ActionEvent event) throws IOException {
        if(CP == null){
            CP = new controllerPlayer(dao);
        }
        if(Username.getText().equalsIgnoreCase("") || Password.getText().equalsIgnoreCase("")){
            Warning.setText("Preencha todos os campos");
        }
        else{
            player = CP.buscarPlayer(Username.getText());
            if(player != null){
                if(player.getSenha().equals(Password.getText())){
                    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    menu = new menuPrincipal(dao, player);
                    menu.start(stage);
                }
                else{
                    Warning.setText("Senha Incorreta");
                }
            
            }
            else{
                Warning.setText("UserName Inválido");
            }
        }
    


    }
    
    public void setControllerPlayer(IDaoPlayer dao){
        this.dao = dao;
    }
}
