/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import Controller.controllerPlayer;
import Interface.IDaoPlayer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Phillip
 */
public class Level1 extends Application {

    MediaPlayer mediaplayer;
    public Level1(ArrayList list, IDaoPlayer dao, player player) { // Dificuldade dificil
        comandos = list;
        this.dao = dao;
        this.player = player;
    }
    
    public Level1(IDaoPlayer dao, player player){ // dificuldade facil
        this.dao = dao;
        this.modoEasy =  true;
        this.player = player;
    }
    private player player;
    private boolean modoEasy = false;
    public ArrayList comandos = new ArrayList();
    private IDaoPlayer dao;
    private controllerPlayer CP;
    private AnimationTimer loop;
    private int tempo;
    private int animacao = 0;
    int pos = 0;
    boolean animar = false;
    private double posicaoX = 110;
    private double posicaoY = 18;
    private static Stage tela;
    private Image left[] = new Image[3];
    private Image right[] = new Image[3];
    private Image up[] = new Image[3];
    private Image down[] = new Image[3];
    private Image boneco;
    private double posicaoAndar;
    private boolean andar = false;
    private boolean MoverE, MoverD, MoverC, MoverB;
    private int contadorComandos = 0;
    private int pontuacao = 0;
    private double tempoDecorrido = 0;
    private viewConclusao telaFinal;
    private String mensagemFinal;
    Boolean obstaculo = false;
    Boolean fim = false;
    Stage stage2 = new Stage();
   
    @Override
    public void start(Stage stage) {
        Media musicFile = new Media(this.getClass().getResource("/music/ActionSong.mpeg").toExternalForm());
        mediaplayer = new MediaPlayer(musicFile);
        mediaplayer.play();
        CP = new controllerPlayer(dao);
        //telaFinal = new viewConclusao(dao, player, mensagemFinal);
        /*
        for(int i = 0; i < comandos.size(); i++){
            System.out.println(comandos.get(i));
        }
        */
        
        tela =  stage;
        tela.setTitle("Game");
        tela.setResizable(false);
        Group root = new Group();
        Scene theScene = new Scene(root);
        tela.setScene(theScene);
        Canvas canvas = new Canvas(800, 580);
        ImageView fundo = new ImageView("/img/level1.png");
        fundo.setFitHeight(580);
        fundo.setFitWidth(800);
        root.getChildren().addAll(fundo, canvas);
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        PreeLeft();
        PreeRight();
        PreeUp();
        PreeDown();
        boneco = new Image("/img/down0.png");
        gc.drawImage(boneco, posicaoX, posicaoY);
        
        tempo = 0;
        loop = new AnimationTimer(){
            
            @Override
            public void handle(long currentNanoTime) {
                if(obstaculo == true || fim == true){
                    loop.stop();
                    stage.close();
                }
                gc.clearRect(0, 0, 800, 600);
                tempoDecorrido = tempoDecorrido + 0.1;
                if(animacao == 0){
                    gc.drawImage(boneco, posicaoX, posicaoY);
                }else if(animacao == 1){
                    gc.drawImage(left[pos], posicaoX, posicaoY);
                }else if(animacao == 2){
                    gc.drawImage(right[pos], posicaoX, posicaoY);
                }else if(animacao == 3){
                    gc.drawImage(up[pos], posicaoX, posicaoY);
                }else{
                    gc.drawImage(down[pos], posicaoX, posicaoY);
                }
                
                if(animar == true){
                    tempo = tempo + 1;
                }
                if(animar == true && tempo % 20 == 0){
                    pos = pos + 1;
                }
                if(tempo == 60){
                    pos = 0;
                    //animar = false;
                    tempo = 0;
                }
                if(MoverD){
                    posicaoX = posicaoX + 0.5;
                    if(posicaoAndar == posicaoX){
                        andar = false;
                        animacao = 0;
                        animar = false;
                        tempo =0;
                        pos =0;
                        MoverD = false;
                        System.out.println(posicaoX);
                        System.out.println(posicaoY);
                        try {
                            obstaculo();
                        } catch (IOException ex) {
                            Logger.getLogger(Level1.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if(obstaculo == true){
                            loop.stop();
                            stage.close();
                        }
                        try {
                            VerificarPosicaoFinal();
                        } catch (IOException ex) {
                            Logger.getLogger(Level1.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if(fim == true){
                            loop.stop();
                            stage.close();
                        }
                    }
                }
                if(MoverE){
                    posicaoX = posicaoX - 0.5;
                     if(posicaoAndar == posicaoX){
                        andar = false;
                        animacao = 0;
                        animar = false;
                        tempo =0;
                        pos =0;
                        MoverE = false;
                        System.out.println(posicaoX);
                        System.out.println(posicaoY);
                        try {
                            obstaculo();
                        } catch (IOException ex) {
                            Logger.getLogger(Level1.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if(obstaculo == true){
                            loop.stop();
                            stage.close();
                        }
                        try {
                            VerificarPosicaoFinal();
                        } catch (IOException ex) {
                            Logger.getLogger(Level1.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if(fim == true){
                            loop.stop();
                            stage.close();
                        }
                    }
                }
                if(MoverC){
                    posicaoY = posicaoY - 0.5;
                     if(posicaoAndar == posicaoY){
                        andar = false;
                        animacao = 0;
                        animar = false;
                        tempo =0;
                        pos =0;
                        MoverC = false;
                        System.out.println(posicaoX);
                        System.out.println(posicaoY);
                        try {
                            obstaculo();
                        } catch (IOException ex) {
                            Logger.getLogger(Level1.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if(obstaculo == true){
                            loop.stop();
                            stage.close();
                        }
                        try {
                            VerificarPosicaoFinal();
                        } catch (IOException ex) {
                            Logger.getLogger(Level1.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if(fim == true){
                            loop.stop();
                            stage.close();
                        }
                    }
                }
                if(MoverB){
                    posicaoY = posicaoY + 0.5;
                     if(posicaoAndar == posicaoY){
                        andar = false;
                        animacao = 0;
                        animar = false;
                        tempo =0;
                        pos =0;
                        MoverB = false;
                        System.out.println(posicaoX);
                        System.out.println(posicaoY);
                        try {
                            obstaculo();
                        } catch (IOException ex) {
                            Logger.getLogger(Level1.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if(obstaculo == true){
                            loop.stop();
                            stage.close();
                        }
                        try {
                            VerificarPosicaoFinal();
                        } catch (IOException ex) {
                            Logger.getLogger(Level1.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if(fim == true){
                            loop.stop();
                            stage.close();
                        }
                    }
                 
                }
                if(!andar && modoEasy == false){
                    if(contadorComandos < comandos.size()){
                        if(comandos.get(contadorComandos).equals(1)){ // direita
                            andar = true;
                                    MoverD = true;
                                    //posicaoAtual = posicaoX;
                                    posicaoAndar = posicaoX + 90;
                                    //posicaoX = posicaoX + 2;
                                    animar = true;
                                    animacao = 2;
                        }
                        else if(comandos.get(contadorComandos).equals(2)){ // esquerda
                            andar = true;
                                    MoverE = true;
                                    //posicaoAtual = posicaoX;
                                    posicaoAndar = posicaoX - 90;
                                    animacao = 1;
                                    //posicaoX = posicaoX - 2;
                                    animar = true;
                        }else if(comandos.get(contadorComandos).equals(3)){ // cima
                            andar = true;
                                    MoverC = true;
                                    //posicaoAtual = posicaoY;
                                    posicaoAndar = posicaoY - 97;
                                    //posicaoY = posicaoY - 2;
                                    animar = true;
                                    animacao = 3;
                        }else{                                              // baixo
                            andar = true;
                                    MoverB = true;
                                    //posicaoAtual = posicaoY;
                                    posicaoAndar = posicaoY + 97;
                                    //posicaoY = posicaoY + 2;
                                    animar = true;
                                    animacao = 4;
                        }
                        contadorComandos++;
                    }else {
                        try {
                            VerificarPosicaoFinal();
                        } catch (IOException ex) {
                            Logger.getLogger(Level1.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        loop.stop();
                        stage.close();
                    }
                }
                
                theScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
                    public void handle(KeyEvent key){
                        if(modoEasy){
                            if(key.getCode() == KeyCode.LEFT){
                                if(!andar){
                                    andar = true;
                                    MoverE = true;
                                    //posicaoAtual = posicaoX;
                                    posicaoAndar = posicaoX - 90;
                                    animacao = 1;
                                    //posicaoX = posicaoX - 2;
                                    animar = true;
                                    contadorComandos++;
                                }


                            }
                            if(key.getCode() == KeyCode.RIGHT){
                                if(!andar){
                                    andar = true;
                                    MoverD = true;
                                    //posicaoAtual = posicaoX;
                                    posicaoAndar = posicaoX + 90;
                                    //posicaoX = posicaoX + 2;
                                    animar = true;
                                    animacao = 2;
                                    contadorComandos++;
                                }

                            }
                            if(key.getCode() == KeyCode.UP){
                                if(!andar){
                                    andar = true;
                                    MoverC = true;
                                    //posicaoAtual = posicaoY;
                                    posicaoAndar = posicaoY - 97;
                                    //posicaoY = posicaoY - 2;
                                    animar = true;
                                    animacao = 3;
                                    contadorComandos++;
                                }

                            }
                            if(key.getCode() == KeyCode.DOWN){
                                if(!andar){
                                    andar = true;
                                    MoverB = true;
                                    //posicaoAtual = posicaoY;
                                    posicaoAndar = posicaoY + 97;
                                    //posicaoY = posicaoY + 2;
                                    animar = true;
                                    animacao = 4;
                                    contadorComandos++;
                                }

                            }   
                        }
                    }
                });
                
                /*
                theScene.setOnKeyReleased(new EventHandler<KeyEvent>(){
                   
                    @Override
                    public void handle(KeyEvent key) {
                        if (key.getCode() == KeyCode.LEFT) {
                            animacao = 0;
                            animar = false;
                            tempo =0;
                            pos =0;
                        }
                        if (key.getCode() == KeyCode.RIGHT) {
                            animacao = 0;
                            animar = false;
                            tempo =0;
                            pos =0;
                        }
                        if (key.getCode() == KeyCode.UP) {
                            animacao = 0;
                            animar = false;
                            tempo =0;
                            pos =0;
                            //boneco = new Image("/Imagem/down0.png"); // IMAGEM PADRÃO
                        }
                        if (key.getCode() == KeyCode.DOWN) {
                            animacao = 0;
                            animar = false;
                            tempo =0;
                            pos =0;
                        }
                    }    
                });
*/
            }    
        };
        
        loop.start();
        stage.show();
        
    }
    
    public void obstaculo() throws IOException{
        if(posicaoX == 110 && posicaoY == 115){
            System.out.println("Obstaculo");
            obstaculo = true;
        }
        if(posicaoX == 110 && posicaoY == 309){
            System.out.println("Obstaculo");
            obstaculo = true;
        }
        if(posicaoX == 200 && posicaoY == 309){
            System.out.println("Obstaculo");
            obstaculo = true;
        }
        if(posicaoX == 110 && posicaoY == 503){
            System.out.println("Obstaculo");
            obstaculo = true;
        }
        if(posicaoX == 650 && posicaoY == 406){
            System.out.println("Obstaculo");
            obstaculo = true;
        }
        if(posicaoX == 560 && posicaoY == 309){
            System.out.println("Obstaculo");
            obstaculo = true;
        }
        if(posicaoY < 0 || posicaoY > 600 || posicaoX < 0 || posicaoX > 800){
            System.out.println("Obstaculo");
            obstaculo = true;
        }
        if(posicaoX >= 290 && posicaoX <= 470 && posicaoY >= 18 && posicaoY <= 406){
            System.out.println("Obstaculo");
            obstaculo = true;
        }
        if(obstaculo){
            mediaplayer.stop();
            mensagemFinal = "Quase lá. Tente de novo.";
            telaFinal = new viewConclusao(dao, player, mensagemFinal);
            //Stage stage2 = new Stage();
            telaFinal.start(stage2);
        }
        
        
    }
    
    public void PreeLeft(){
        
        int i;
        for(i = 0; i < left.length; i++){
            left[i] = new Image("/img/left" + i + ".png");
        }
    }
    
    public void PreeRight(){
        
        int i;
        for(i = 0; i < right.length; i++){
            right[i] = new Image("/img/right" + i + ".png");
        }
    }
    
    public void PreeUp(){
        
        int i;
        for(i = 0; i < up.length; i++){
            up[i] = new Image("/img/up" + i + ".png");
        }
    }
    
    public void PreeDown(){
        
        int i;
        for(i = 0; i < down.length; i++){
            down[i] = new Image("/img/down" + i + ".png");
        }
    }
    private void VerificarPosicaoFinal() throws IOException {
        /*
            if(posicaoX == 650 && posicaoY == 212){
               pontuacao = (int)(10000 - (tempoDecorrido + (contadorComandos * 13)));
               if(modoEasy){
                   if(pontuacao > player.getScoreEasy()){
                       CP.adicionarPontuacaoEasy(player.getUserName(), pontuacao);
                   }
               }
               else{
                   if(pontuacao > player.getScoreHard()){
                       CP.adicionarPontuacaoHard(player.getUserName(), pontuacao);
                   }
               }
               player = CP.buscarPlayer(player.getUserName());
               CP.removerPlayer(player.getUserName());
               CP.cadastrarPlayer(player);
               mensagemFinal = "\tVocê conseguiu!\n"
                             + "\t Pontuação: "+ pontuacao;
            }
            else{
                mensagemFinal = "Quase lá. Tente de novo.";
            }
            telaFinal = new viewConclusao(dao, player, mensagemFinal);
            Stage stage2 = new Stage();
            telaFinal.start(stage2);
            */
        
            if(modoEasy == false){ // verifica para dificuldade dificil
                if(posicaoX == 650 && posicaoY == 212){
                    pontuacao = (int)(10000 - (tempoDecorrido + (contadorComandos * 13)));
                    if(pontuacao > player.getScoreHard()){
                        //player = CP.adicionarPontuacaoHard(player.getUserName(), pontuacao);
                        player.setScoreHard(pontuacao);
                        CP.removerPlayer(player.getUserName());
                        CP.cadastrarPlayer(player);
                        CP.adicionarPontuacaoEasy(player);
                        CP.adicionarPontuacaoHard(player);
                    }
                    mensagemFinal = "\tVocê conseguiu!\n"
                                 + "\t Pontuação: "+ pontuacao;
                    //player = CP.buscarPlayer(player.getUserName());
                    //CP.removerPlayer(player.getUserName());
                    //CP.cadastrarPlayer(player);
                    //mensagemFinal = "\tVocê conseguiu!\n"
                       //          + "\t Pontuação: "+ pontuacao;
            
                }else{
                    mensagemFinal = "Quase lá. Tente de novo.";
                }
                if(contadorComandos == comandos.size()){
                    mediaplayer.stop();
                    telaFinal = new viewConclusao(dao, player, mensagemFinal);
                    telaFinal.start(stage2);
                }
            }else{ // verifica para dificuldade facil
                if(posicaoX == 650 && posicaoY == 212){
                    fim = true;
                    pontuacao = (int)(10000 - (tempoDecorrido + (contadorComandos * 13)));
                    if(pontuacao > player.getScoreEasy()){
                        //player = CP.adicionarPontuacaoEasy(player.getUserName(), pontuacao);
                        player.setScoreEasy(pontuacao);
                        CP.removerPlayer(player.getUserName());
                        CP.cadastrarPlayer(player);
                        CP.adicionarPontuacaoEasy(player);
                        CP.adicionarPontuacaoHard(player);
                    }
                    mensagemFinal = "\tVocê conseguiu!\n"
                                 + "\t Pontuação: "+ pontuacao;
                    /*
                    //player = CP.buscarPlayer(player.getUserName());
                    CP.removerPlayer(player.getUserName());
                    CP.cadastrarPlayer(player);
                    mensagemFinal = "\tVocê conseguiu!\n"
                                 + "\t Pontuação: "+ pontuacao;
                    */
                    mediaplayer.stop();
                    telaFinal = new viewConclusao(dao, player, mensagemFinal);
                    telaFinal.start(stage2);
                }else{
                    obstaculo();
                }
            }
        }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
