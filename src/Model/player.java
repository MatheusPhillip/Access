/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author Phillip
 */
public class player implements Serializable{
    private String nickName;
    private String userName;
    private String senha;
    private String email;
    private double ScoreEasy;
    private double ScoreHard;

    public player(String nickName, String userName, String senha, String email) {
        this.nickName = nickName;
        this.userName = userName;
        this.senha = senha;
        this.email = email;
        this.ScoreEasy = 0.0;
        this.ScoreHard = 0.0;
    }
    public player(String nickName, String userName, String senha) {
        this.nickName = nickName;
        this.userName = userName;
        this.senha = senha;
        this.ScoreEasy = 0.0;
        this.ScoreHard = 0.0;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public double getScoreEasy() {
        return ScoreEasy;
    }

    public void setScoreEasy(double ScoreEasy) {
        this.ScoreEasy = ScoreEasy;
    }

    public double getScoreHard() {
        return ScoreHard;
    }

    public void setScoreHard(double ScoreHard) {
        this.ScoreHard = ScoreHard;
    }
    
    
    
}
