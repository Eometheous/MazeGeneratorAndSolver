package main.mazes;

import main.graphTheoryAlgorithms.DFS;
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
        //TODO make BFS algorithm that solves a maze
        return null;
    }
}
