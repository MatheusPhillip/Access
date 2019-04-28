/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Interface.IDaoComandos;
import java.util.ArrayList;

/**
 *
 * @author Phillip
 */
public class daoComandos implements IDaoComandos{
    
    private ArrayList comandos = new ArrayList();

    @Override
    public void criarComandos(ArrayList list) {
        for (int i = 0; i < comandos.size(); i++) {
            comandos.add(i, list);
            return;
        }
        comandos.add(list);
        for(int i = 0; i < comandos.size(); i++){
            System.out.println(comandos.get(i));
        }
    }

    @Override
    public ArrayList Array() {
        if(comandos != null){
            return comandos;
        }
        return null;
    }

    @Override
    public void removerComandos() {
        comandos.removeAll(comandos);
    }
    
}
