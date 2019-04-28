/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.controllerPlayer;
import Interface.IDaoPlayer;
import View.menuPrincipal.menuPrincipal;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Phillip
 */

public class ScoreBoardController implements Initializable {
    private player player;
    private ArrayList<player> arrayRetorno;
    private ArrayList arrayTopPlayersEasy;
    private ArrayList arrayTopPlayersHard;
    private ArrayList ScoreTopPlayersEasy;
    private ArrayList ScoreTopPlayersHard;
    private IDaoPlayer dao;
    private controllerPlayer CP;
    private Label PlayerEasy1;
    private Label PlayerEasy2;
    private Label ScoreEasy1;
    private Label ScoreEasy2;
    private ListView list1;
    private ListView list2;
    private ListView list3;
    private ListView list4;
    
    @FXML
    private GridPane scoreGrid;
    @FXML
    private VBox LayoutPlayersEasy;
    @FXML
    private VBox LayoutScoreEasy;
    @FXML
    private VBox LayoutPlayersHard;
    @FXML
    private VBox LayoutScoreHard;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }  
    


    @FXML
    private void handleReturnMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        menuPrincipal menu = new menuPrincipal(dao, player);
        menu.start(stage);
    }
    
    public void setControllerPlayer(IDaoPlayer dao, player player){
        this.dao = dao;
        this.player = player;
    }

    @FXML
    private void showScore(ActionEvent event) {
        CP = new controllerPlayer(dao);
        arrayTopPlayersEasy = new ArrayList();
        arrayTopPlayersHard = new ArrayList();
        ScoreTopPlayersEasy = new ArrayList();
        ScoreTopPlayersHard = new ArrayList();
        
        list1 = new ListView();
        list2 = new ListView();
        list3 = new ListView();
        list4 = new ListView();
        arrayRetorno = CP.topPlayersEasy();
        for(int i = 0; i < arrayRetorno.size(); i++){
            arrayTopPlayersEasy.add(i, arrayRetorno.get(i).getNickName());
            ScoreTopPlayersEasy.add(i, arrayRetorno.get(i).getScoreEasy());
        }

        list1.getItems().addAll(arrayTopPlayersEasy);
        list1.setBackground(Background.EMPTY);
        list1.setOrientation(Orientation.VERTICAL);
        LayoutPlayersEasy.setPadding(new Insets(20, 20, 20, 20));
        LayoutPlayersEasy.getChildren().addAll(list1);
        
        list2.getItems().addAll(ScoreTopPlayersEasy);
        list2.setBackground(Background.EMPTY);
        list2.setOrientation(Orientation.VERTICAL);
        LayoutScoreEasy.setPadding(new Insets(20, 20, 20, 20));
        LayoutScoreEasy.getChildren().addAll(list2);
        
        arrayRetorno = CP.topPlayersHard();
        for(int i = 0; i < arrayRetorno.size(); i++){
            arrayTopPlayersHard.add(i, arrayRetorno.get(i).getNickName());
            ScoreTopPlayersHard.add(i, arrayRetorno.get(i).getScoreHard());
        }
        
        list3.getItems().addAll(arrayTopPlayersHard);
        list3.setBackground(Background.EMPTY);
        list3.setOrientation(Orientation.VERTICAL);
        LayoutPlayersHard.setPadding(new Insets(20, 20, 20, 20));
        LayoutPlayersHard.getChildren().addAll(list3);
        
        list4.getItems().addAll(ScoreTopPlayersHard);
        list4.setBackground(Background.EMPTY);
        list4.setOrientation(Orientation.VERTICAL);
        LayoutScoreHard.setPadding(new Insets(20, 20, 20, 20));
        LayoutScoreHard.getChildren().addAll(list4);
        
        
    }
    
}
