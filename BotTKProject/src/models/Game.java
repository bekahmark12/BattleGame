package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
    private Map savedMap = new Map();
    private ArrayList<Player> savedPlayers  = new ArrayList();
    private boolean savedIsPlayer1Turn;

    public void setSavedMap(Map map){
        savedMap = map;
    }

    public void setSavedPlayers(ArrayList<Player> players){
        savedPlayers = players;
    }

    public void setSavedIsPlayer1Turn(boolean isPlayer1Turn){
        savedIsPlayer1Turn = isPlayer1Turn;
    }

    public Map getSavedMap() {
        return savedMap;
    }

    public ArrayList<Player> getSavedPlayers() {
        return savedPlayers;
    }

    public boolean savedIsPlayer1Turn() {
        return savedIsPlayer1Turn;
    }
}
