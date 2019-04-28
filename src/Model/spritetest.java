/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Matheus
 */
public class spritetest extends Application {
    
    private AnimationTimer loop;
    private int tempo;
    private int animacao = 0;
    int pos = 0;
    boolean animar = false;
    private double posicaoX = 100;
    private double posicaoY = 100;
    private static Stage tela;
    private Image left[] = new Image[3];
    private Image right[] = new Image[3];
    private Image up[] = new Image[3];
    private Image down[] = new Image[3];
    private Image boneco;
    private double posicaoAndar;
    private boolean andar = false;
    private boolean MoverE, MoverD, MoverC, MoverB;
    
    //   ----> Essa eh uma alteração!!!!!!!! <----
    
    
    @Override
    public void start(Stage stage) {
        tela =  stage;
        tela.setTitle("Game");
        tela.setResizable(false);
        Group root = new Group();
        Scene theScene = new Scene(root);
        tela.setScene(theScene);
        Canvas canvas = new Canvas(800, 600);
        ImageView fundo = new ImageView("/img/street_dark.png");
        root.getChildren().addAll(fundo, canvas);
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        PreeLeft();
        PreeRight();
        PreeUp();
        PreeDown();
        
        gc.drawImage(boneco, posicaoX, posicaoY, 70, 70);
        gc.drawImage(boneco, posicaoX, posicaoY);
        boneco = new Image("/img/down0.png");
        tempo = 0;
        loop = new AnimationTimer(){
            
            @Override
            public void handle(long currentNanoTime) {
                gc.clearRect(0, 0, 800, 600);
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
                    }
                }
                
                theScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
                    public void handle(KeyEvent key){
                        
                        if(key.getCode() == KeyCode.LEFT){
                            if(!andar){
                                andar = true;
                                MoverE = true;
                                //posicaoAtual = posicaoX;
                                posicaoAndar = posicaoX - 30;
                                animacao = 1;
                                //posicaoX = posicaoX - 2;
                                animar = true;
                            }

                           
                        }
                        if(key.getCode() == KeyCode.RIGHT){
                            if(!andar){
                                andar = true;
                                MoverD = true;
                                //posicaoAtual = posicaoX;
                                posicaoAndar = posicaoX + 30;
                                //posicaoX = posicaoX + 2;
                                animar = true;
                                animacao = 2;
                            }
                            
                        }
                        if(key.getCode() == KeyCode.UP){
                            if(!andar){
                                andar = true;
                                MoverC = true;
                                //posicaoAtual = posicaoY;
                                posicaoAndar = posicaoY - 30;
                                //posicaoY = posicaoY - 2;
                                animar = true;
                                animacao = 3;
                            }
                            
                        }
                        if(key.getCode() == KeyCode.DOWN){
                            if(!andar){
                                andar = true;
                                MoverB = true;
                                //posicaoAtual = posicaoY;
                                posicaoAndar = posicaoY + 30;
                                //posicaoY = posicaoY + 2;
                                animar = true;
                                animacao = 4;
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
    
  

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
