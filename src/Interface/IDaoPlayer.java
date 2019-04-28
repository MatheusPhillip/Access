/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Model.player;
import java.util.ArrayList;

/**
 *
 * @author Phillip
 */
public interface IDaoPlayer {

    public void cadastrarPlayer(player player);

    public player buscarPlayer(String nome);

    public void removerPlayer(String nome);

    public ArrayList<player> topPlayersEasy();

    public ArrayList<player> topPlayersHard();

    public void adicionarPontuacaoEasy(player player);

    public void adicionarPontuacaoHard(player player);

    public ArrayList<player> arrayPlayers();
    
}
