package models;

import models.players.Player;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
    private Icons[][] savedMap = new Icons[12][24];
    private ArrayList<Player> savedPlayers  = new ArrayList();
    private boolean savedIsPlayer1Turn;

    public void setSavedMap(Icons[][] icons){
        savedMap = icons;
    }

    public void setSavedPlayers(ArrayList<Player> players){
        savedPlayers = players;
    }

    public void setSavedIsPlayer1Turn(boolean isPlayer1Turn){
        savedIsPlayer1Turn = isPlayer1Turn;
    }

    public Icons[][] getSavedMap() {
        return savedMap;
    }

    public ArrayList<Player> getSavedPlayers() {
        return savedPlayers;
    }

    public boolean savedIsPlayer1Turn() {
        return savedIsPlayer1Turn;
    }
}
