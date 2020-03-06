package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
    private Map savedMap = new Map();
    private ArrayList<Player> savedPlayers  = new ArrayList();
    private boolean savedIsPlayer1turn;

    public void setSavedMap(Map map){
        savedMap = map;
    }

    public void setSavedPlayers(ArrayList<Player> players){
        savedPlayers = players;
    }

    public void setSavedIsPlayer1turn(boolean isPlayer1turn){
        savedIsPlayer1turn = isPlayer1turn;
    }
}
