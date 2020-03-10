package models;

import models.enums.Icons;
import models.players.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Map implements Serializable {
    private static Icons[][] grid = new Icons[12][24];
    private static Random rng = new Random();


    public static Icons[][] randomMap(ArrayList<Player> players){
        grid = new Icons[12][24];
        int randomP2;
        int randomP1;
        Player p1 = players.get(0);
        Player p2 = players.get(1);

        Icons obstacle = Icons.T;
        for(int o = 40; o >= 0; o--){
            int randomRow = rng.nextInt(12);
            int randomCols = rng.nextInt(24);
            if(Map.checkValidSpace(randomRow, randomCols)){
                grid[randomRow][randomCols] = obstacle;
                if(obstacle == Icons.T){
                    obstacle = Icons.O;
                }else{
                    obstacle = Icons.T;
                }
            }
        }
        placePlayer(players);

        return grid;
    }

    public static boolean checkValidSpace(int row, int cols){
        boolean isValid = false;
        try {
            if (grid[row][cols] == null || grid[row][cols] == Icons._) {
                isValid = true;
            }
        }catch(ArrayIndexOutOfBoundsException OOB){

        }
        return isValid;
    }

    public static void printBoard(){
        for(Icons[] icons : getGrid()){
            for (Icons icon : icons){
                if(icon == null){
                    System.out.printf("%-3s", Icons._);
                }
                else{
                    System.out.printf("%-3s", icon);
                }
            }
            System.out.println();
        }
    }

    public static void placePlayer(ArrayList<Player> players){
        int randomP = 0;
        boolean validSpaceP = true;
        for(int i = 0; i < players.size(); i++){
            Player player = players.get(i);
            int pCol = -1;
            do{
                if(i == 0){
                    pCol = 0;
                    randomP = rng.nextInt(12);
                    if(!Map.checkValidSpace(randomP, pCol)){
                        validSpaceP = false;
                    }else if(Map.checkValidSpace(randomP + 1, pCol) || Map.checkValidSpace(randomP + 1, pCol) || Map.checkValidSpace(randomP, pCol)){
                        validSpaceP = false;
                    }
                }
                else if(i == 1){
                    pCol = 23;
                    randomP = rng.nextInt(12);
                    if(!Map.checkValidSpace(randomP, pCol)){
                        validSpaceP = false;
                    }else if(!Map.checkValidSpace(randomP + 1, pCol) || !Map.checkValidSpace(randomP + 1, pCol) || !Map.checkValidSpace(randomP, pCol)){
                        validSpaceP = false;
                    }
                }

            }while (validSpaceP);
            grid[randomP][pCol] = player.getIcon();
            player.setCol(pCol);
            player.setRow(randomP);
        }
    }

    public static void setIcon(int row, int col, Icons icon){
        grid[row][col] = icon;
    }

    public static void setGrid(Icons[][] grid) {
        Map.grid = grid;
    }

    public static Icons[][] getGrid() {
        return grid;
    }
}
