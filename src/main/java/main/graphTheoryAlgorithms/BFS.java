package main.graphTheoryAlgorithms;
import java.util.Queue;

import main.linkedLists.LinkedList;
import main.linkedLists.Node;
import main.mazes.Coordinate;
import main.mazes.Maze;
import main.mazes.MazeWalls;

public class BFS {
    private static int x;
    private static int y;

    /**
     * Resets the MazeWalls characters by setting it to " "
     * @param maze  The Maze we are resetting the walls of
     */
    private static void resetMazeWallsCharacters(Maze maze) {
        MazeWalls[][] actualMaze = maze.getMazeWalls();

        for (int i = 0; i < maze.getLength(); i++) {
            for (int j = 0; j < maze.getHeight(); j++) {
                actualMaze[i][j].setChar(" ");
            }
        }
    }

    /**
     * Resets the coordinates to the starting position
     */
    private static void resetCoords(){
        x = 0;
        y = 0;
    }

    /**
     * Updates the x and y coordinates with new coordinates
     * @param coords    the coordinate being used to update x and y
     */
    private static void updateXYWith(Coordinate coords){
        x = coords.getX();
        y = coords.getY();
    }

    /**
     * Gets the adjacency list graph representation of the maze
     * @param maze   the maze we are getting the adjacency list of
     * @param node   the node we want the head of
     * @return       the head of the linked list at the node
     */
    private static Node getAdjListHeadAt(Maze maze, int node){ return maze.getMazeGraph().getAdjList()[node].getHead(); }

    /**
     * Depth first search algorithm that searches for the end of the maze
     * @param maze          the maze we are searching
     * @param visited       the tiles visited
     */
    public static void breadthFirstSearch(Maze maze, boolean[][] visited){
        // reset local variables
        resetCoords();
        Queue<Integer> visitNextQueue = new java.util.LinkedList<>();

        int start = maze.getStartingTile();
        int end = maze.getEndingTile();
        MazeWalls[][] wallsAt = maze.getMazeWalls();

        Coordinate currentCoords = maze.positionOf(start);
        updateXYWith(currentCoords);

        visitNextQueue.add(start);
        visited[x][y] = true;

        // breadth fist search through the maze until we've found the solution
        while(!maze.foundSolution()){
            int currentTile = visitNextQueue.poll();
            currentCoords = maze.positionOf(currentTile);
            Node neighbors = getAdjListHeadAt(maze, currentTile);

            updateXYWith(currentCoords);

            // set character of walls at x y to current tiles visited
            wallsAt[x][y].setChar(String.valueOf(maze.getTilesVisited() % 10));

            // Backtracks if we find the ending location
            if(currentTile == end){
                // save the current maze with all the tiles visited to the file
                maze.saveMazeToFile(true);
                backtrack(maze, currentCoords);
                return;
            }

            maze.incrementTilesVisited(); // This is what increments based on how many tiles we have already visited.

            // Traversing through the neighboring tiles.
            while (neighbors != null) {
                int neighbor = neighbors.getItem();
                currentCoords = maze.positionOf(neighbor);
                updateXYWith(currentCoords);

                // if we haven't visited this position add the neighbor to the queue
                if (!visited[x][y]) {
                    visited[x][y] = true;
                    visitNextQueue.add(neighbor);
                }
                neighbors = neighbors.getNext();
            }
        }
    }

    /**
     * Backtracks through the maze to return the solution of the maze
     * @param maze              maze we are backtracking through
     * @param current  current position in the maze
     */
    public static void backtrack(Maze maze, Coordinate current){
        MazeWalls[][] wallsAt = maze.getMazeWalls();
        LinkedList path = new LinkedList();

        int start = maze.getStartingTile();
        int end = maze.getEndingTile();

        resetMazeWallsCharacters(maze);
        updateXYWith(current);

        wallsAt[x][y].setChar("#");
        Node tile = getAdjListHeadAt(maze, maze.nodeAt(x, y));
        int tileLocation = tile.getItem();

        path.add(end); // Add the ending, then we traverse backwards to the starting point of the maze.
        while(tileLocation != start){
            // we are at the final node in the list, add it to the solution path
            if (tile.getNext() == null){
                tileLocation = tile.getItem();
                path.add(tileLocation);
                current = maze.positionOf(tileLocation);

                updateXYWith(current);

                wallsAt[x][y].setChar("#");
                // tile = maze.getMazeGraph().getAdjList()[tileLocation].getHead();
                tile = getAdjListHeadAt(maze, tileLocation);
            }
            tile = tile.getNext();
        }
        wallsAt[x][y].setChar("#");
        path.add(start);

        maze.setMazeSolution(path);
    }
}