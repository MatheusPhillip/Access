/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.register;

import Controller.controllerPlayer;
import Interface.IDaoPlayer;
import Model.player;
import View.login.LoginController;
import View.login.login;
import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Phillip
 */
public class RegisterController implements Initializable {

    private controllerPlayer CP;
    private IDaoPlayer dao;
    @FXML
    private TextField UsernameRegister;
    @FXML
    private TextField EmailRegister;
    @FXML
    private TextField NicknameRegister;
    @FXML
    private TextField ConfirmNicknameRegister;
    @FXML
    private PasswordField Password;
    @FXML
    private PasswordField ConfirmPasswordRegister;
    @FXML
    private Label Warning;
    
    private player player;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void CancelOption(ActionEvent event) throws IOException {
        FXMLLoader LoginView = new FXMLLoader();
        LoginView.setLocation(getClass().getResource("/View/login/Login.fxml"));
        try{
            LoginView.load();
        }catch(IOException ex){
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        LoginController LG = LoginView.getController();
        LG.setControllerPlayer(dao);
        Parent p = LoginView.getRoot();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(p));
        stage.show();
    }
    
    public void setControllerPlayer(IDaoPlayer dao){
        this.dao = dao;
    }

    @FXML
    private void handleSignUpAction(ActionEvent event) {
        if(CP == null){
            CP = new controllerPlayer(dao);
        }
        if(UsernameRegister.getText().equals("") || Password.getText().equals("") 
            || ConfirmPasswordRegister.getText().equals("") || NicknameRegister.getText().equals("")
            || ConfirmNicknameRegister.getText().equals(""))
        {
           Warning.setText("Preencha todos os campos obrigatórios(*)");
        }
        else{
            if(!ConfirmNicknameRegister.getText().equals(NicknameRegister.getText())){
                Warning.setText("NickName precisa ser igual à sua confirmação");
            }
            else if(!ConfirmPasswordRegister.getText().equals(Password.getText())){
                Warning.setText("Senha precisa ser igual à sua confirmação");
            }
            else{
                player = CP.buscarPlayer(UsernameRegister.getText());
                if(player != null){
                    Warning.setText("Username já existe");
                }
                else{
                    if(EmailRegister.getText().equals("")){
                        player = new player(NicknameRegister.getText(), UsernameRegister.getText(), Password.getText());
                        CP.cadastrarPlayer(player);
                    }
                    else{
                        player = new player(NicknameRegister.getText(), UsernameRegister.getText(), Password.getText(), EmailRegister.getText());
                        CP.cadastrarPlayer(player);
                    }
                    FXMLLoader LoginView = new FXMLLoader();
                    LoginView.setLocation(getClass().getResource("/View/login/Login.fxml"));
                    try{
                        LoginView.load();
                    }catch(IOException ex){
                        Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    LoginController LG = LoginView.getController();
                    LG.setControllerPlayer(dao);
                    Parent p = LoginView.getRoot();
                    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(p));
                    stage.show();
                }
            }
        }
    }
    
}
