package controllers;

import models.Coordinate;
import models.Icons;
import models.Player;

import java.awt.*;
import java.util.Stack;

public class AIPath {

    public boolean[][] createVisitedArray(Icons[][] map) {
        int mapHeight = map.length;
        if (mapHeight == 0)
            throw new IllegalStateException("Board must exist");
        //Checking to make sure that an Icon[][] board was generated properly.

        int mapLength = map[0].length;

        //create nodes visited array
        boolean[][] visitedNodes = new boolean[mapHeight][mapLength];
        return visitedNodes;
    }


    public Point locateOpponent(Icons[][] map) {
        Point opponentLocation = new Point();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == Icons.P)
                    opponentLocation.setLocation(i, j);
            }
        }
        return opponentLocation;
    }

    //store path in a stack. Stack will update each turn as the opponent/target moves.
    //Depending on player type, AI can move 'dexterity' number of times along that stack


    public Point depthFirstSearch(Icons[][] map, int row, int col, boolean[][] visited) {
        visited = createVisitedArray(map);
        boolean cellIsPassable = map[row][col] == Icons._;
        Point opponentPosition = null;
        int height = map.length;
        int length = map[0].length;

            if (row < 0 || col < 0 || row >= height || col >= length || visited[row][col])
                cellIsPassable = false;

            //mark cell visited
            if (cellIsPassable) {
                visited[row][col] = true;
                depthFirstSearch(map, row + 1, col, visited); //go right
                depthFirstSearch(map, row - 1, col, visited); //go left
                depthFirstSearch(map, row, col + 1, visited); //go down
                depthFirstSearch(map, row, col - 1, visited); //go up
            }
        return new Point();
        }


    public void AIMoveTowardsOpponent(Icons[][] map, Point opponentLocation, Player AIPlayer) {
        Point AICurrentLocation =
                opponentLocation = locateOpponent(map);
        int maxSpacesCanMove = AIPlayer.getDexterity(); //I think they set it up so that dexterity is the max number a player can move on the board.


        //**how do I run my depth first search to find a path towards (opponentLocation) and then
        // tell it to move from (AICurrentLocation) (maxSpacesCanMove) towards (opponentLocation)?
    }
}
