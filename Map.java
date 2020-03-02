package models;

public class Map {
    private int row;
    private int col;
    private Icons[][] grid;

    public void populateMap(Icons[][] grid){

    }

    public boolean checkValidSpace(int row, int cols){
        throw new UnsupportedOperationException("This has not yet been implemented.");
    }

    public Icons[][] getGrid() {
        return grid;
    }
}
