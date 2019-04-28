/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Interface.IDaoPlayer;
import Model.player;
import java.util.ArrayList;

/**
 *
 * @author Phillip
 */
public class controllerPlayer {
    
    private IDaoPlayer dao;
    public controllerPlayer(IDaoPlayer dao){
        this.dao = dao;
    }
    
    public void cadastrarPlayer(player player){
        dao.cadastrarPlayer(player);
    }  
    public player buscarPlayer(String Nome){
        return dao.buscarPlayer(Nome);
    }
    
    public ArrayList<player> topPlayersEasy(){
        return dao.topPlayersEasy();
    }
    
    public ArrayList<player> topPlayersHard(){
        return dao.topPlayersHard();
    }
    public void removerPlayer(String Nome){
        dao.removerPlayer(Nome);
    }
    public ArrayList<player> ArrrayPlayers(){
        return dao.arrayPlayers();
    }
    
    public void adicionarPontuacaoHard(player player){
        dao.adicionarPontuacaoHard(player);
    }
    
    public void adicionarPontuacaoEasy(player player){
        dao.adicionarPontuacaoEasy(player);
    }
    
    
}
