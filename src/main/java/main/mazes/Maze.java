package main.mazes;

import main.graphs.AdjacencyListGraph;

import java.util.Random;

public class Maze {
    private final int length;
    private final int height;
    private final AdjacencyListGraph maze;
    private final MazeWalls[][] mazeWalls;

    /**
     * Create a maze from a set AdjacencyListGraph and a set length and height
     * @param maze      the graph being used to generate the maze
     * @param length    the length of the maze
     * @param height    the height of the maze
     */
    public Maze(AdjacencyListGraph maze, int length, int height) {
        this.length = length;
        this.height = height;
        this.maze = maze;
        mazeWalls = new MazeWalls[this.length][this.height];
        createMazeWalls();
    }

    /**
     * Creates a random maze with a fixed length and height
     * @param length    the length of the maze
     * @param height    the height of the maze
     */
    public Maze(int length, int height) {
        this.length = length;
        this.height = height;
        maze = new AdjacencyListGraph(this.length * this.height);
        mazeWalls = new MazeWalls[this.length][this.height];
        createPath(0,0);
    }

    /**
     * Generates a random maze from the starting position
     * @param x         the starting x coordinate
     * @param y         the starting y coordinate
     */
    public void createPath(int x, int y) {
        // store directions into an array
        Directions[] directions = Directions.values();
        Random randomDirectionGenerator = new Random();

        // make the order of directions random
        for (int i = 0; i < directions.length; i++) {
            int randomDirection = randomDirectionGenerator.nextInt(4);
            Directions temp = directions[i];
            directions[i] = directions[randomDirection];
            directions[randomDirection] = temp;
        }
        // for every direction in directions, create the path for the maze
        for (Directions direction: directions) {
            int nextX = x + direction.dx;   // get next x coordinate
            int nextY = y + direction.dy;   // get next y coordinate

            // if the nextX && nextY are in the maze, and it hasn't been visited yet
            // add edge to maze and create path with next x and y coordinates
            if (neighborExists(nextX, length) && neighborExists(nextY, height)
                    && maze.getAdjList()[position(nextX, nextY)].numberOfItems() == 0) {
                maze.addEdge(position(x, y),position(nextX,nextY));
                createPath(nextX, nextY);
            }
        }

        createMazeWalls();
    }

    /**
     * Creates the maze walls to represent the AdjacencyListGraph as an actual maze
     */
    private void createMazeWalls() {
        int x;
        int y;
        for (y = 0; y < height; y++) {
            for (x = 0; x < length; x++) {
                createWallsAt(x, y);
            }
        }
    }

    /**
     * Creates walls at the x and y coordinate
     * @param x     the x coordinate
     * @param y     the y coordinate
     */
    private void createWallsAt(int x, int y) {
        // convert coordinates into nodes in the graph
        int current = position(x, y);
        int top = position(x, y - 1);
        int bottom = position(x, y + 1);
        int left = position(x - 1, y);
        int right = position(x + 1, y);

        // initialize the MazeWalls object
        mazeWalls[x][y] = new MazeWalls();
        mazeWalls[x][y].setNumber(current); // doesn't actually do anything yet
        // if any edge is found in a given direction, add the connection
        if (neighborExists(y + Directions.up.dy, height))
            mazeWalls[x][y].setTopConnection(maze.searchEdge(current,top));
        if (neighborExists(y + Directions.down.dy, height))
            mazeWalls[x][y].setBottomConnection(maze.searchEdge(current,bottom));
        if (neighborExists(x + Directions.left.dx, length))
            mazeWalls[x][y].setLeftConnection(maze.searchEdge(current,left));
        if (neighborExists(x + Directions.right.dx, length))
            mazeWalls[x][y].setRightConnection(maze.searchEdge(current,right));
        if (x == length - 1) mazeWalls[x][y].setEnd(true);
    }

    /**
     * Converts x and y coordinates to a position in the AdjacencyList Graph
     * @param x     the x coordinate
     * @param y     the y coordinate
     * @return      the position
     */
    private int position(int x, int y) {
        if (y == 0) return x;
        return x + (y * this.length);
    }

    /**
     * Checks if a cell is actually in the maze
     * @param cell  the cell we are checking
     * @param bound the boundary of the maze
     * @return      true if it is in the maze, false if it isn't
     */
    public boolean neighborExists(int cell, int bound) {
        return (cell >= 0) && (cell < bound);
    }

    /**
     * Displays the Adjacency List Representation of
     * the maze as well as the maze itself
     */
    public void display() {
        System.out.println(maze);   // print adjacency list representation

        // print maze representation with walls
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < length; x++) {
                System.out.print(mazeWalls[x][y].getTop());
            }
            for (int x = 0; x < length; x++) {
                System.out.print(mazeWalls[x][y].getMiddle());
            }
        }
        // print bottom walls
        for (int x = 0; x < length; x++) {
            System.out.print(mazeWalls[x][height - 1].getBottom());
        }
    }
}
