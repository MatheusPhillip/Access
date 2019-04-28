/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Interface.IDaoComandos;
import java.util.ArrayList;

/**
 *
 * @author Phillip
 */
public class controllerComandos {
    
    
    private IDaoComandos dao;
    public controllerComandos(IDaoComandos dao){
        this.dao = dao;
    }
    
    public void criarComandos(ArrayList list){
        dao.criarComandos(list);
    }  
    public ArrayList Array(){
        return dao.Array();
    }
    
    public void removerComandos(){
        dao.removerComandos();
    }
    
}
