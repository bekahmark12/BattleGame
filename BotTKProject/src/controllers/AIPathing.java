package controllers;

import models.enums.Icons;
import models.players.Player;

import java.awt.*;
import java.util.Stack;

public class AIPathing {
    private Stack<Point> path = new Stack<>();
    private Stack<Point> neighbors = new Stack<>();
    public static boolean[][] visited;


    public static boolean[][] createVisitedArray(Icons[][] map) {
        int mapHeight = map.length;
        if (mapHeight == 0)
            throw new IllegalStateException("Board must exist");
        //Checking to make sure that an Icon[][] board was generated properly.

        int mapLength = map[0].length;

        //create nodes visited array
        boolean[][] visitedNodes = new boolean[mapHeight][mapLength];
        return visitedNodes;
    }


//    public static Point locateOpponent(Icons[][] map, Player opponent) {
//        for (int i = 0; i < map.length; i++) {
//            for (int j = 0; j < map[i].length; j++) {
//                if (map[i][j] == Icons.P)
//                    opponentLocation.setLocation(i, j);
//            }
//        }
//        return opponentLocation;
//        //or give it access to opponents data since players already have locations?
//    }

    public static Stack<Point> findPath(Icons[][] map, Point p){
        Stack<Point> surroundingNodes = new Stack<Point>();
        Point left = new Point();
        Point right = new Point();
        Point down = new Point();
        Point up = new Point();
        down.x = p.x - 1;
        down.y = p.y;
        if (valid(map, down)) surroundingNodes.add(down);
        up.x = p.x + 1;
        up.y = p.y;
        if (valid(map, up)) surroundingNodes.add(up);
        left.x = p.x;
        left.y = p.y - 1;
        if (valid(map, left)) surroundingNodes.add(left);
        right.x = p.x;
        right.y = p.y + 1;
        if (valid(map, right)) surroundingNodes.add(right);
        return surroundingNodes;
    }

    public static boolean valid(Icons[][] map, Point p){
        if (isInBounds(map, p) && canTraverse(map, p) && visited[p.x][p.y] == false)
            return true;
        else
            return false;
    }

    public static boolean isInBounds(Icons[][] map, Point p){
        if (p.x < map[0].length && p.x > -1 && p.y < map.length  && p.y > -1){
            return true;
        } else
            return false;
    }

    public static boolean canTraverse(Icons[][] map, Point p){
        if (map[p.x][p.y] == Icons._) return true;
        else
            return false;
    }

    public boolean depthFirstSearch(Icons[][] map, Point p){
        visited = createVisitedArray(map);
        neighbors = findPath(map, p);
        if (map[p.x][p.y] == Icons.P){
            return true;
        }
        if (neighbors.isEmpty()){
            return false;
        }
        for (int i=0; i<neighbors.size(); i++){
            visited[neighbors.get(i).x][neighbors.get(i).y] = true;
            depthFirstSearch(map, neighbors.get(i));
            // if above returns true, add neighbors[i] to stack
            boolean b = depthFirstSearch(map, p);
            if(b){
                path.add(neighbors.get(i));
            }
            // return whatever DFS returned
            return b;
        }
        return false;
    }


//    public Point depthFirstSearch(Icons[][] map, int row, int col, boolean[][] visited) {
//        visited = createVisitedArray(map);
//        boolean cellIsPassable = map[row][col] == Icons._;
//        Point opponentPosition = null;
//        int height = map.length;
//        int length = map[0].length;
//
//            if (row < 0 || col < 0 || row >= height || col >= length || visited[row][col])
//                cellIsPassable = false;
//
//            //mark cell visited
//            if (cellIsPassable) {
//                visited[row][col] = true;
//                depthFirstSearch(map, row + 1, col, visited); //go right
//                depthFirstSearch(map, row - 1, col, visited); //go left
//                depthFirstSearch(map, row, col + 1, visited); //go down
//                depthFirstSearch(map, row, col - 1, visited); //go up
//            }
//        return new Point();
//        }


    public void AIMoveTowardsOpponent(Icons[][] map, Player AIPlayer) {
        int currentRow = AIPlayer.getRow();
        int currentCol = AIPlayer.getCol();
        Point AICurrentLocation = new Point(currentRow, currentCol);
        // clear the stack
        path.clear();
        Stack<Point> path = findPath(map, AICurrentLocation);
        for (int i = 0; i < AIPlayer.getDexterity(); i++) {
            Point p = path.pop();
            AIPlayer.setCol(p.x);
            AIPlayer.setRow(p.y);
        }
    }
}
