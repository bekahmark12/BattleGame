package models;

public class Map {
    private int row;
    private int col;
    private Icons[][] grid = new Icons[25][20];

    public void populateMap(Icons[][] grid){

    }

    public boolean checkValidSpace(int row, int cols){
        throw new UnsupportedOperationException("This has not yet been implemented.");
    }

    public Icons[][] getGrid() {
        return grid;
    }
}
