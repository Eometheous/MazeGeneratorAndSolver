package main.graphTheoryAlgorithms;
import java.util.*;

import main.linkedLists.LinkedList;
import main.linkedLists.Node;
import main.mazes.Coordinate;
import main.mazes.Maze;
import main.mazes.MazeWalls;

public class BFS {
    int width=0, height=0;
    MazeWalls[][] actualMaze;
    public BFS(MazeWalls[][] maze) {
        actualMaze = maze;
    }

    private static void initMaze(Maze maze){
        MazeWalls[][] actualMaze = maze.getMazeWalls();

        for (int x = 0; x < maze.getLength(); x++) {
            for (int y = 0; y < maze.getHeight(); y++)
                actualMaze[x][y].setChar(" ");
        }
    }

    // Handling bad inputs.
    public void die(String message){
        System.out.print(message);
        System.exit(0);
    }

    public static void bfsAlgo(Maze maze, boolean[][] visited, Coordinate currentPosition){
        int x = currentPosition.getX();
        int y = currentPosition.getY();
        int currentNode = maze.node(x,y);

        MazeWalls[][] actualMaze = maze.getMazeWalls();

        Queue<Node> nodes = new java.util.LinkedList<>();

        // grab next tile from maze at current node and store it
        Node startTile = maze.getMazeGraph().getAdjList()[currentNode].getHead();

        nodes.add(startTile);
        while (!nodes.isEmpty() && startTile != null && maze.getSolution().getHead() == null) {
            // if current tile hasn't been visited yet
            // grab next coordinates
            Coordinate newCoords = maze.position(nodes.poll().getItem());
            // Coordinate nextTileCoords = maze.position(nextTile.getItem());

            int newX = newCoords.getX();
            int newY = newCoords.getY();

            if (!visited[x][y]) {
                visited[x][y] = true;
                actualMaze[x][y].setChar(String.valueOf(maze.getTilesVisited()));
                maze.incrementTilesVisited();

                // if next position hasn't been visited yet
                 if (!visited[newX][newY]) bfsAlgo(maze, visited, newCoords);
                nodes.add(startTile);
            }

            // if next tile is the end of the maze
            if (startTile.getItem() == maze.getEndingTile()) {
                actualMaze[newX][newY].setChar(String.valueOf(maze.getTilesVisited()));
                backTrack(maze, newCoords);
            }

            
            // if (!visited[nextTileCoords.getX()][nextTileCoords.getY()]) { bfsAlgo(maze, visited, nextTileCoords); }
            startTile = startTile.getNext();
        }
    }

    public static void backTrack(Maze maze, Coordinate currentPosition){
        maze.display();
        // clear all characters from the maze
        initMaze(maze);

        MazeWalls[][] actualMaze = maze.getMazeWalls();
        int x = currentPosition.getX();
        int y = currentPosition.getY();

        int start = maze.getStartingTile();
        int end = maze.getEndingTile();

        // start backtracking and adding # to the path
        actualMaze[x][y].setChar("#");

        Node tile = maze.getMazeGraph().getAdjList()[maze.node(x, y)].getHead();
        LinkedList path = new LinkedList();
        
        path.add(end);
        while (tile.getItem() != start) {
            if (tile.getNext() == null){
                path.add(tile.getItem());
                currentPosition = maze.position(tile.getItem());
                actualMaze[x][y].setChar("#");
                tile = maze.getMazeGraph().getAdjList()[tile.getItem()].getHead();
            }
            tile = tile.getNext();
        }
        currentPosition = maze.position(start);
        actualMaze[x][y].setChar("#");
        path.add(start);

        maze.setSolution(path);
    }
}
