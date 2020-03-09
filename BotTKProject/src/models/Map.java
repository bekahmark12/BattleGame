package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Map implements Serializable {
    private static Icons[][] grid = new Icons[12][24];
    private static Random rng = new Random();


    public static Icons[][] randomMap(ArrayList<Player> players){
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
        do{
            randomP2 = rng.nextInt(12);
        }while (!Map.checkValidSpace(randomP2, 23));
        grid[randomP2][23] = p2.getIcon();
        p2.setCol(23);
        p2.setRow(randomP2);

        do{
            randomP1 = rng.nextInt(12);
        }while (!Map.checkValidSpace(randomP1, 0));
        grid[randomP1][0] = p1.getIcon();
        p1.setCol(0);
        p1.setRow(randomP1);


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

    public void setIcon(int row, int col, Icons icon){
        grid[row][col] = icon;
    }

    public static Icons[][] getGrid() {
        return grid;
    }
}
