package main.mazes;

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
        maze.resetTilesVisited();
        maze.resetSolution();
        boolean[][] visited = initializeFalse(maze);

        Coordinate startingCoords = getStartingCoordinate(maze);

        maze.fileWrite("Solving Using DFS");

        // do DFS to find the maze solution
        DFS.depthFirstSearch(maze,visited,startingCoords);

        // save the maze with the solution path to the file
        maze.saveMazeToFile(true);
        maze.fileWrite(String.format("Solution Path: %s", maze.getSolution()));
        maze.fileWrite(String.format("Tiles Visited: %d\n",maze.getTilesVisited()));
        return maze.getSolution();
    }
    /**
     * Solves a maze using a BFS algorithm
     * @param maze  the maze we are solving
     * @return      the solution path
     */
    public static LinkedList solveMazeUsingBFS(Maze maze) {
        maze.resetTilesVisited();
        maze.resetSolution();
        boolean[][] visited = initializeFalse(maze);

        maze.fileWrite("Solving Using BFS");

        // do BFS to find the maze solution
        BFS.breadthFirstSearch(maze,visited);

        // save the maze with the solution path to the file
        maze.saveMazeToFile(true);
        maze.fileWrite(String.format("Solution Path: %s", maze.getSolution()));
        maze.fileWrite(String.format("Tiles Visited: %d\n",maze.getTilesVisited()));
        return maze.getSolution();
    }

    /**
     * Initializes a 2d array by setting every index to false
     * @param maze  the maze the 2d array is for
     * @return      initialized array
     */
    private static boolean[][] initializeFalse(Maze maze) {
        int length = maze.getLength();
        int height = maze.getHeight();
        boolean[][] visited = new boolean[length][height];

        // initialize the 2d array
        for (int x = 0; x < length; x++)
            for (int y = 0; y < height; y++)
                visited[x][y] = false;
        return visited;
    }

    /**
     * Gets the starting position for the maze
     * @param maze  the maze we need the starting position of
     * @return      the starting position
     */
    private static Coordinate getStartingCoordinate(Maze maze) {
        return maze.positionOf(maze.getStartingTile());
    }
}
