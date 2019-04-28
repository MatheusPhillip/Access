/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arquivo;

import Model.player;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Phillip
 */
public class CadastroArquivoPlayers {
    private ObjectInputStream input;
    private ObjectOutputStream output;
    public void openToWrite(String nomeArq){
        try{
            FileOutputStream arq = new FileOutputStream(nomeArq);
            output = new ObjectOutputStream(arq);
            System.out.println("Arquivo aberto para escrita");
        }
        catch(IOException iOException){
            System.err.println("Erro ao tentar abrir o arquivo para gravação.");
            System.exit(1);
        }
    }
    
    public void openToRead(String nomeArq){
        try{
            FileInputStream arq = new FileInputStream(nomeArq);
            input = new ObjectInputStream(arq);
            System.out.println("Arquivo aberto para leitura");
        }
        catch(IOException iOException){
            System.err.println("Erro ao tentar abrir o arquivo para leitura.");
            System.exit(1);
        }
    }
    
    public void closeAfterRead(){
        try{
            if(input != null){
                input.close();
                input = null;
                System.out.println("Arquivo fechado com sucesso. ");
            }
        }
        catch(IOException iOException){
            System.err.println("Erro ao tentar fehar o arquivo.");
            System.exit(1);
        }
    }
    
    public void closeAfterWrite(){
        try{
            if(output != null){
                output.close();
                output = null;
                System.out.println("Arquivo fechado com sucesso. ");
            }
        }
        catch(IOException iOException){
            System.err.println("Erro ao tentar fehar o arquivo.");
            System.exit(1);
        }
    }
    
    public void gravaObjeto(player player){
        try{
            if(output != null){
                output.writeObject(player);               
                output.flush();
                System.out.println("Player gravado com sucesso. ");
            }
        }
        catch(IOException iOException){
            System.err.println("Erro ao tentar gravar no arquivo.");
            System.exit(1);
        }
    }
    
    public player leObjeto(){
        player player;
        try{
            if(input != null){
                player = (player)input.readObject();
                return player;
            }
        }
        catch(EOFException eOFException){
            System.out.println("Fim de Arquivo.");
            return null;
        }
        catch(IOException iOException){
            System.err.println("Erro ao tentar ler o arquivo.");
            return null;
        }
        catch(ClassNotFoundException classNotFoundException){
            System.err.println("Classe não encontrada.");
            return null;
        }
        return null;
    }
}
