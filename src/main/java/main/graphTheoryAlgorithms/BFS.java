package main.graphTheoryAlgorithms;
import java.util.Queue;

import main.linkedLists.LinkedList;
import main.linkedLists.Node;
import main.mazes.Coordinate;
import main.mazes.Maze;
import main.mazes.MazeWalls;

public class BFS {

    static Queue<Integer> tileLocations = new java.util.LinkedList<>();
    public static int x;
    public static int y;

    static void initializeMaze(Maze maze){
        MazeWalls[][] actualMaze = maze.getMazeWalls();

        for(int i = 0; i < maze.getLength(); i++){
            for(int j = 0; j < maze.getHeight(); j++){
                actualMaze[i][j].setChar(" ");
            }
        }
    }

    // Updates the X and Y coordinates for better readability and debugging
    static void updateLocation(Coordinate coords){
        x = coords.getX();
        y = coords.getY();
    }

    // Add visitors, also for better readability.
    static void addVisited(int neighbor){ tileLocations.add(neighbor); }

    // Returns a linked list for better readability.
    static LinkedList getAdjList(Maze maze, int current){ return maze.getMazeGraph().getAdjList()[current]; }

    public static void breadthFirstSearch(Maze maze, boolean[][] visited, Coordinate currentCoords){
        BFS.updateLocation(currentCoords);
        MazeWalls[][] actualMaze = maze.getMazeWalls();
        int end = maze.getEndingTile();
        
        addVisited(maze.nodeAt(x,y));
        visited[x][y] = true;

        while(!tileLocations.isEmpty() && maze.foundSolution()){
            int currentTileLocation = tileLocations.poll();
            currentCoords = maze.positionOf(currentTileLocation);
            Node neighbors = getAdjList(maze, currentTileLocation).getHead();
            
            updateLocation(currentCoords); // This updates the coordinates {X,Y} location in 2-Dimensional array of MazeWall.

            // Backtracks after we find the ending location
            if(currentTileLocation == end){
                actualMaze[x][y].setChar(String.valueOf(maze.getTilesVisited() % 10));
                backtrack(maze, currentCoords);
                return;
            }

            actualMaze[x][y].setChar(String.valueOf(maze.getTilesVisited() % 10));
            maze.incrementTilesVisited(); // This is what increments based on how many tiles we have already visited.

            // Traversing through the neighboring tiles.
            while (neighbors != null) {
                int neighborLocation = neighbors.getItem();
                currentCoords = maze.positionOf(neighborLocation);
                updateLocation(currentCoords);

                if (!visited[x][y]) {
                    visited[x][y] = true;
                    addVisited(neighborLocation);
                }
                neighbors = neighbors.getNext();
            }
        }
    }

    public static void backtrack(Maze maze, Coordinate current){
        maze.saveMazeToFile(true);
        MazeWalls[][] actualMaze = maze.getMazeWalls();
        LinkedList path = new LinkedList();

        int start = maze.getStartingTile();
        int end = maze.getEndingTile();
        
        initializeMaze(maze);
        updateLocation(current); // Updates {X,Y} coordinates on maze.

        actualMaze[x][y].setChar("#");
        Node tile = getAdjList(maze, maze.nodeAt(x, y)).getHead();
        int tileLocation = tile.getItem();

        path.add(end); // Add the ending, then we traverse backwards to the starting point of the maze.
        while(tileLocation != start){
            if (tile.getNext() == null){
                tileLocation = tile.getItem();
                path.add(tileLocation);
                current = maze.positionOf(tileLocation);
                
                updateLocation(current);
                
                actualMaze[x][y].setChar("#");
                tile = maze.getMazeGraph().getAdjList()[tileLocation].getHead();
            }
            tile = tile.getNext();
        }
        current = maze.positionOf(start);
        actualMaze[x][y].setChar("#");
        path.add(start);

        maze.setSolution(path);
    }
}
