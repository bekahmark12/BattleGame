package models;

import java.io.Serializable;
import java.util.Random;

public class Map implements Serializable {
    private static Icons[][] grid = new Icons[12][24];
    private static Random rng = new Random();

//    public void run(){
//        printBoard(randomMap());
//    }

    public static Icons[][] randomMap(){
        int randomE;
        int randomP;

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
            randomE = rng.nextInt(12);
        }while (!Map.checkValidSpace(randomE, 23));
        grid[randomE][23] = Icons.E;

        do{
            randomP = rng.nextInt(12);
        }while (!Map.checkValidSpace(randomP, 23));
        grid[randomP][0] = Icons.P;

        return grid;
    }

    public static boolean checkValidSpace(int row, int cols){
        boolean isInvalid = false;
        if(grid[row][cols] == null || grid[row][cols] == Icons._){
            isInvalid = true;
        }
        return isInvalid;
    }


    public static void printBoard(Icons[][] board){
        for(Icons[] icons : board){
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

    public static Icons[][] getGrid() {
        return grid;
    }
}
