package controllers;

import models.Icons;
import models.Player;

import java.awt.*;
import java.util.Stack;

public class AIPathing {
    public static Stack<Point> path = new Stack<>();
    public static Stack<Point> neighbors = new Stack<>();
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

    public static Stack<Point> getNeighbors(Icons[][] map, Point p){
        Stack<Point> surroundingNodes = new Stack<Point>();
        Point left = new Point();
        Point right = new Point();
        Point down = new Point();
        Point up = new Point();
        down.x = p.x + 1;
        down.y = p.y;
        if (valid(map, down)) surroundingNodes.add(down);
        up.x = p.x - 1;
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
        if (p.x < map.length && p.x > -1 && p.y < map[0].length  && p.y > -1){
            return true;
        } else
            return false;
    }

    public static boolean canTraverse(Icons[][] map, Point p){
        if (map[p.x][p.y] == Icons._) return true;
        else
            return false;
    }

    public static boolean depthFirstSearch(Icons[][] map, Point p, int maxDepth) {
        return depthFirstSearch(map, p, maxDepth, 0);
    }

    public static boolean depthFirstSearch(Icons[][] map, Point p, int maxDepth, int currentDepth){
        visited = createVisitedArray(map);
        neighbors = getNeighbors(map, p);
        if (currentDepth > maxDepth) {
            return false;
        }
        if (map[p.x][p.y] == Icons.A){
            return true;
        }
        if (neighbors.isEmpty()){
            return false;
        }
        for (int i=0; i<neighbors.size(); i++){
            visited[neighbors.get(i).x][neighbors.get(i).y] = true;
            //depthFirstSearch(map, neighbors.get(i), maxDepth, currentDepth + 1);
            // if above returns true, add neighbors[i] to stack
            boolean b = depthFirstSearch(map, p, maxDepth, currentDepth + 1);
            if(b){
                path.add(neighbors.get(i));
            }
            // return whatever DFS returned
            return b;
        }
        return false;
    }


    public static void AIMoveTowardsOpponent(Icons[][] map, Player AIPlayer, Player opponent) {
        int currentRow = AIPlayer.getRow();
        int currentCol = AIPlayer.getCol();
        Point AICurrentLocation = new Point(currentRow, currentCol);
        // clear the stack
        path.clear();
        if (depthFirstSearch(map, AICurrentLocation, 10)) {
            for (int i = 0; i < AIPlayer.getDexterity(); i++) {
                Point p = path.pop();
                AIPlayer.setCol(p.y);
                AIPlayer.setRow(p.x);
            }
        } else {
            int moveCount = 0;
            do {


                int spaceAbove = AIPlayer.getRow() - 1;
                int spaceBelow = AIPlayer.getCol() + 1;
                int spaceRight = AIPlayer.getRow() + 1;
                int spaceLeft = AIPlayer.getCol() - 1;

                Point pointAbove = new Point(spaceAbove, currentCol);
                Point pointBelow = new Point(spaceBelow, currentCol);
                Point pointLeft = new Point(currentRow, spaceLeft);
                Point pointRight = new Point(currentRow, spaceRight);
                Point directionPoint = getDirectionBetweenPlayers(AIPlayer, opponent);
                int x = directionPoint.x;
                int y = directionPoint.y;
                Point primDir = (Math.abs(x) > y ? (x > -1 ? primDir = pointBelow : pointAbove) : (y > -1 ? primDir = pointRight : pointLeft));

                if(primDir == pointAbove){
                    if(valid(map, pointAbove)){
                        moveUp(map, AIPlayer);
                        moveCount++;
                        } else {
                            if(valid(map, pointBelow)){
                                moveDown(map, AIPlayer);
                                moveCount++;
                            }
                    }

                } else if(primDir == pointBelow){
                    if(valid(map, pointBelow)){

                    }

                } else if(primDir == pointLeft){
                    if(valid(map, pointLeft)){
                        
                    }
                } else if(primDir == pointRight && valid(map, pointRight)){
                    moveRight(map, AIPlayer);
                    moveCount++;
                    if(!valid(map, pointRight)){
                        if(valid(map, pointLeft)){
                            moveLeft(map, AIPlayer);
                            moveCount++;
                        } else {
                            moveDown(map, AIPlayer);
                            moveCount++;
                        }
                    }
                }
            } while(moveCount <= AIPlayer.getDexterity());
        }
    }

    public static Point getDirectionBetweenPlayers(Player AI, Player opponent){
        Point AILocation = new Point(AI.getRow(), AI.getCol());
        Point opponentLocation = new Point(opponent.getRow(), opponent.getCol());
        Point directionPoint = new Point((opponentLocation.x - AILocation.x), (opponentLocation.y - opponentLocation.y));
        return directionPoint;
    }

    public static void moveUp(Icons[][] map, Player AIPlayer){
        int row = AIPlayer.getRow();
        int col = AIPlayer.getCol();
        AIPlayer.setRow(row - 1);
        AIPlayer.setCol(col);
    }

    public static void moveDown(Icons[][] map, Player AIPlayer){
        int row = AIPlayer.getRow();
        int col = AIPlayer.getCol();
        AIPlayer.setRow(row + 1);
        AIPlayer.setCol(col);
    }

    public static void moveLeft(Icons[][] map, Player AIPlayer){
        int row = AIPlayer.getRow();
        int col = AIPlayer.getCol();
        AIPlayer.setRow(row);
        AIPlayer.setCol(col - 1);
    }

    public static void moveRight(Icons[][] map, Player AIPlayer){
        int row = AIPlayer.getRow();
        int col = AIPlayer.getCol();
        AIPlayer.setRow(row);
        AIPlayer.setCol(col + 1);
    }
}
