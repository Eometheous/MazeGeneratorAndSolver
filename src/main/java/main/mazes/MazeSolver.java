package main.mazes;

import java.util.Queue;

import main.graphTheoryAlgorithms.DFS;
import main.graphTheoryAlgorithms.BFS;
import main.linkedLists.LinkedList;

public class MazeSolver {
    /**
     * Solves a maze using a DFS algorithm
     * @param maze  the maze we are solving
     * @return      the solution path
     */

    public static LinkedList solveMazeUsingDFS(Maze maze) {
        int length = maze.getLength();
        int height = maze.getHeight();
        boolean[][] visited = new boolean[length][height];

        // initialize the 2d array
        for (int x = 0; x < length; x++)
            for (int y = 0; y < height; y++)
                visited[x][y] = false;

        // convert starting node to starting coordinates
        int node = maze.getStartingTile();
        Coordinate startingCoords = maze.positionOf(node);

        // do DFS to find the maze solution
        DFS.depthFirstSearch(maze,visited,startingCoords);
        return maze.getSolution();
    }

    public static LinkedList solveMazeUsingBFS(Maze maze) {


        Queue<Coordinate> tiles = initQueue(maze);

        int startingPos = maze.getStartingTile();
        Coordinate startTile = maze.positionOf(startingPos);

        BFS.bfsAlgo(maze, tiles, startTile);


        return maze.getSolution();
    }
    private static Queue<Coordinate> initQueue(Maze maze){
        int length = maze.getLength();
        int height = maze.getHeight();
        Queue<Coordinate> queue = new java.util.LinkedList<>();
        // initialize the 2d array
        for (int x = 0; x < length; x++)
            for (int y = 0; y < height; y++)
                // visited[x][y] = false;
                queue.add(new Coordinate());
        return queue;
    }
}
