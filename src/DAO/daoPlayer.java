/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Interface.IDaoPlayer;
import Model.player;
import java.util.ArrayList;

/**
 *
 * @author Phillip
 */
public class daoPlayer implements IDaoPlayer{
    
    ArrayList<player> Players = new ArrayList<>();
    ArrayList<player> TopPlayersEasy = new ArrayList<>();
    ArrayList<player> TopPlayersHard = new ArrayList<>();
    private player player;
    private boolean adicionar = false;

    @Override
    public void cadastrarPlayer(player player) {
        Players.add(player);   
    }

    @Override
    public player buscarPlayer(String nome) {
        for(int i = 0; i < Players.size(); i++){
            if(nome.equals(Players.get(i).getUserName())){
                return Players.get(i);
            }
        }
        return null;   
    }

    @Override
    public void removerPlayer(String nome) {
        for(int i = 0; i < Players.size(); i++){
            if(nome.equals(Players.get(i).getUserName())){
                Players.remove(i);
            }
        }
         for(int i = 0; i < TopPlayersEasy.size(); i++){
            if(nome.equals(TopPlayersEasy.get(i).getUserName())){
                TopPlayersEasy.remove(i);
            }
        }
          for(int i = 0; i < TopPlayersHard.size(); i++){
            if(nome.equals(TopPlayersHard.get(i).getUserName())){
                TopPlayersHard.remove(i);
            }
        }
        
        
    }

    @Override
    public ArrayList<player> topPlayersEasy() {
        return TopPlayersEasy;
    }

    @Override
    public ArrayList<player> topPlayersHard() {
        return TopPlayersHard;
    }

    @Override
    public void adicionarPontuacaoHard(player player) {
        for(int i = 0; i < TopPlayersHard.size(); i++){
                if(player.getScoreHard() > TopPlayersHard.get(i).getScoreHard()){
                    TopPlayersHard.add(i, player);
                    return;
                }
            }
        TopPlayersHard.add(player);
        
    }

    @Override
    public void adicionarPontuacaoEasy(player player) {
        for(int i = 0; i < TopPlayersEasy.size(); i++){
                if(player.getScoreEasy()> TopPlayersEasy.get(i).getScoreEasy()){
                    TopPlayersEasy.add(i, player);
                    return;
                }
            }
        TopPlayersEasy.add(player);
        
    }

    @Override
    public ArrayList<player> arrayPlayers() {
        return Players;
    }
    
}
