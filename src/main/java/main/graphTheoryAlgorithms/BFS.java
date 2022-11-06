package main.graphTheoryAlgorithms;
import java.util.*;

import main.linkedLists.LinkedList;
import main.linkedLists.Node;
import main.mazes.Coordinate;
import main.mazes.Maze;
import main.mazes.MazeWalls;

public class BFS {
    ArrayList<String> maze;

    PriorityQueue<Tile> to_process;
    // HashSet<Tile> processed;
    HashMap<Boolean, Coordinate> hashtable;

    int width=0, height=0;

    public BFS(ArrayList<String> maze, int width, int height){
        this.maze = maze;
        this.width = width;
        this.height = height;
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

    public static void bfsAlgo(Maze maze, Queue<Coordinate> tiles, Coordinate start){
        MazeWalls[][] actualMaze = maze.getMazeWalls();
        int x = start.getX();
        int y = start.getY();

        int node = maze.node(x, y);

        Node nextTile = maze.getMazeGraph().getAdjList()[node].getHead();
        // Iterate until queue is empty. 
        while(nextTile != null && !tiles.isEmpty()){
            Coordinate current = tiles.poll(); // poll() grabs and removes item, which helps iterate through entire.

            if(!current.getVisited()){
                current.setVisited(true);
                actualMaze[x][y].setChar(String.valueOf(maze.getTilesVisited()));
                maze.incrementTilesVisited();

                bfsAlgo(maze, tiles, start);
            }

            // Backtracking.
            if(nextTile.getItem() == maze.getEndingTile()){
                actualMaze[current.getX()][current.getY()].setChar(String.valueOf(maze.getTilesVisited()));
                backtrack(maze, current);
            }

            nextTile = nextTile.getNext();
        }
    }

    public static void backtrack(Maze maze, Coordinate current){
        maze.display();
        int x = current.getX();
        int y = current.getY();
        MazeWalls[][] actualMaze = maze.getMazeWalls();

        initMaze(maze);

        actualMaze[x][y].setChar("#");

        int node = maze.node(x, y);

        Node tile = maze.getMazeGraph().getAdjList()[node].getHead();

        LinkedList path = new LinkedList();
        path.add(maze.getEndingTile());

        while(tile.getItem() != maze.getEndingTile()){
            if(tile.getNext() == null){
                path.add(tile.getItem());
                current = maze.position(tile.getItem());
                actualMaze[x][y].setChar("#");
                tile = maze.getMazeGraph().getAdjList()[tile.getItem()].getHead();
            }

            tile = tile.getNext();
        }

        current = maze.position(maze.getStartingTile());
        actualMaze[current.getX()][current.getY()].setChar("#");
        path.add(maze.getStartingTile());

        maze.setSolution(path);
    }

    public void printInfo(){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                System.out.print(maze.get(i).charAt(j));
            }
            System.out.print('\n');
        }
    }
}
