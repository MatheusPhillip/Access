/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.controllerComandos;
import Controller.controllerPlayer;
import DAO.daoComandos;
import Interface.IDaoComandos;
import Interface.IDaoPlayer;
import View.menuPrincipal.menuPrincipal;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Phillip
 */
public class Level1FXMLController implements Initializable {
    
     MediaPlayer mediaplayer;
    
    private IDaoPlayer dao;
    private controllerPlayer CP;
    private Level1 level1;
    private player player;
     

    private Label Drag;
    @FXML
    private ImageView setaDireita;
    @FXML
    private ImageView target1;
    @FXML
    private ImageView setaCima;
    @FXML
    private ImageView setaEsquerda;
    @FXML
    private ImageView setaBaixo;

    private int contador = 1;
    @FXML
    private ImageView target2;
    @FXML
    private ImageView target3;
    @FXML
    private ImageView target4;
    @FXML
    private Button buttonClean;
    @FXML
    private ImageView target5;
    @FXML
    private ImageView target6;
    @FXML
    private ImageView target7;
    @FXML
    private ImageView target8;
    @FXML
    private ImageView target9;
    @FXML
    private ImageView target10;
    @FXML
    private ImageView target11;
    @FXML
    private ImageView target12;
    @FXML
    private ImageView target13;
    @FXML
    private ImageView target14;
    @FXML
    private ImageView target15;
    @FXML
    private ImageView target16;
    @FXML
    private ImageView target17;
    @FXML
    private ImageView target18;
    @FXML
    private ImageView target19;
    @FXML
    private ImageView target20;
    @FXML
    private ImageView target21;
    @FXML
    private ImageView target22;
    @FXML
    private ImageView target23;
    @FXML
    private ImageView target24;

    private ArrayList comandos = new ArrayList(); 
    
    
    private boolean BooleanD, BooleanE, BooleanC, BooleanB;
    @FXML
    private Button buttonClean1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 
        Media musicFile = new Media(this.getClass().getResource("/music/level1Song.mp3").toExternalForm());
        mediaplayer = new MediaPlayer(musicFile);
        mediaplayer.play();
    }

    /*
    @FXML
    private void handleDragDetection(MouseEvent event) {
        
        Dragboard db = Drag.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putString(Drag.getText());
        
        db.setContent(cb);
        
        event.consume();
        
        
        /*
        Dragboard db = DragGrid.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putString(DragGrid.getAlignment().toString());
        db.setContent(cb);
        event.consume();
       
        
    }
     */
    @FXML
    private void handleDragOver(DragEvent event) {
        if (event.getDragboard().hasImage()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    private void handleImageDrop(DragEvent event) {

        Image img = event.getDragboard().getImage();
        
       
        if(contador <= 24){
            String idName2 = "#target" + contador;
            ImageView imageview = (ImageView) target8.getScene().lookup(idName2);
            imageview.setImage(img);
            System.out.println(BooleanD);
            System.out.println(BooleanE);
            System.out.println(BooleanC);
            System.out.println(BooleanB);
            if(BooleanD){
                comandos.add(contador - 1, 1);
                BooleanD = false;
            }
            if(BooleanE){
                comandos.add(contador - 1, 2);
                BooleanE = false;
            }
            if(BooleanC){
                comandos.add(contador - 1, 3);
                BooleanC = false;
            }
            if(BooleanB){
                comandos.add(contador - 1, 4);
                BooleanB = false;
            }
            contador++;
        }
        

//        if (contador == 0) {
//            target1.setImage(img);
//            Comandos.add(contador);
//        } else if (contador == 1) {
//            target2.setImage(img);
//            Comandos.add(contador);
//        } else if (contador == 2) {
//            target3.setImage(img);
//            Comandos.add(contador);
//        } else if (contador == 3) {
//            target4.setImage(img);
//            Comandos.add(contador);
//        }
//        contador++;
    }

    @FXML
    private void handleDragDetection(MouseEvent event) {

        ImageView tempImg = (ImageView) event.getTarget();

        Dragboard db = tempImg.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putImage(tempImg.getImage());
        
        System.out.println(tempImg);
        System.out.println(tempImg.getId());
        
        
        
        if(tempImg.getId().equals("setaDireita")){
            BooleanD = true;
        }
        else if(tempImg.getId().equals("setaEsquerda")){
            BooleanE = true;
        }else if(tempImg.getId().equals("setaCima")){
            BooleanC = true;
        }else if(tempImg.getId().equals("setaBaixo")){
            BooleanB = true;
        }

        db.setContent(cb);

        event.consume();
    }

    @FXML
    private void Reset(ActionEvent event) {
        mediaplayer.stop();
        mediaplayer.play();
        for (int i = 1; i <= 24; i++) {
            String idName = "#target" + i;
            ImageView imageview = (ImageView) target8.getScene().lookup(idName);
            imageview.setImage(null); 
        }
        /*
        for(int i = 0; i < comandos.size(); i++){
            comandos.remove(i);
            comandos.removeAll(comandos);
        }
        */
        comandos.removeAll(comandos);
        
        
        
       
        

//        target1.setImage(null);
//        target2.setImage(null);
//        target3.setImage(null);
//        target4.setImage(null);
//
          contador = 1;
    }

    @FXML
    private void handleStartAnimation(ActionEvent event) {
        /*
        for(int i = 0; i < comandos.size(); i++){
            System.out.println(comandos.get(i));
        }
        */
        mediaplayer.stop();
        if(comandos.size() > 0){
            Level1 level1 = new Level1(this.comandos, dao, player);
            Stage stageatual = (Stage)((Node)event.getSource()).getScene().getWindow();
            Stage stage = new Stage();
            level1.start(stage);
            stageatual.close();
        }
    }

   

    @FXML
    private void handleRemoveBlock(MouseEvent event) {
        /*
        String idName2 = "#target" + contador;
        ImageView imageview = (ImageView) target8.getScene().lookup(idName2);
        
        for (int i = 1; i <= 24; i++) {
            String idName = "#target" + i;
            ImageView imageview = (ImageView) target8.getScene().lookup(idName);
            imageview.setImage(null);
            comandos.remove(i - 1);
        }
*/
        
        ImageView tempImg = (ImageView) event.getTarget();
        System.out.println(tempImg);
        for (int i = 1; i <= 24; i++) {
            String idName = "#target" + i;
            ImageView imageview = (ImageView) target8.getScene().lookup(idName);
            if(idName.equals(imageview.getId())){
                imageview.setImage(null);
                comandos.remove(i - 1);
                break;
            }
        }
        
        
    }

    public ArrayList getComandos() {
        return comandos;
    }

    public void setComandos(ArrayList comandos) {
        this.comandos = comandos;
    }

    @FXML
    private void handleReturnMenu(ActionEvent event) throws IOException {
        mediaplayer.stop();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        menuPrincipal menu = new menuPrincipal(dao, player);
        menu.start(stage);
    }
    
    
    public void setControllerPlayer(IDaoPlayer dao, player player){
        this.dao = dao;
        this.player = player;
    }
    
    

}
