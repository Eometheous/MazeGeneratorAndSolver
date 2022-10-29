package main.mazes;

import main.graphs.AdjacencyListGraph;

import java.util.Random;

public class Maze {
    private final int length;
    private final int height;
    private final AdjacencyListGraph mazeGraph;
    private final MazeWalls[][] mazeWalls;
    private final int startingTile, endingTile;

    /**
     * Create a maze from a set AdjacencyListGraph and a set length and height
     * @param maze      the graph being used to generate the maze
     * @param length    the length of the maze
     * @param height    the height of the maze
     */
    public Maze(AdjacencyListGraph maze, int length, int height) {
        startingTile = 0;
        endingTile = (length * height) - 1;
        this.length = length;
        this.height = height;
        this.mazeGraph = maze;
        mazeWalls = new MazeWalls[this.length][this.height];
        createMazeWalls();
    }

    /**
     * Creates a random maze with a fixed length and height
     * @param length    the length of the maze
     * @param height    the height of the maze
     */
    public Maze(int length, int height) {
        startingTile = 0;
        endingTile = (length * height) - 1;
        this.length = length;
        this.height = height;
        mazeGraph = new AdjacencyListGraph(this.length * this.height);
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
                    && mazeGraph.getAdjList()[node(nextX, nextY)].numberOfItems() == 0) {
                mazeGraph.addEdge(node(x, y), node(nextX,nextY));
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
        int current = node(x, y);
        int top = node(x, y - 1);
        int bottom = node(x, y + 1);
        int left = node(x - 1, y);
        int right = node(x + 1, y);

        // initialize the MazeWalls object
        mazeWalls[x][y] = new MazeWalls();

        // if any edge is found in a given direction, add the connection
        if (neighborExists(y + Directions.up.dy, height))
            mazeWalls[x][y].setTopConnection(mazeGraph.searchEdge(current,top));
        if (neighborExists(y + Directions.down.dy, height))
            mazeWalls[x][y].setBottomConnection(mazeGraph.searchEdge(current,bottom));
        if (neighborExists(x + Directions.left.dx, length))
            mazeWalls[x][y].setLeftConnection(mazeGraph.searchEdge(current,left));
        if (neighborExists(x + Directions.right.dx, length))
            mazeWalls[x][y].setRightConnection(mazeGraph.searchEdge(current,right));
        if (x == length - 1) mazeWalls[x][y].setEnd(true);
    }

    /**
     * Converts x and y coordinates to a position in the AdjacencyList Graph
     * @param x     the x coordinate
     * @param y     the y coordinate
     * @return      the position
     */
    public int node(int x, int y) {
        if (y == 0) return x;
        return x + (y * length);
    }

    /**
     * Finds the coordinates of a node in the maze
     * @param node  the node we are finding the coordinates for
     * @return      the coordinates
     */
    public Coordinate position(int node) {
        Coordinate coords = new Coordinate();

        int nodeX = node % length;
        int nodeY = (int) Math.floor(((double) node / (double) length) % height);

        coords.setX(nodeX);
        coords.setY(nodeY);
        return coords;
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

    public int getLength() {return length;}
    public int getHeight() {return height;}
    public AdjacencyListGraph getMazeGraph() {return mazeGraph;}
    public MazeWalls[][] getMazeWalls() {return mazeWalls;}
    public int getStartingTile() {return startingTile;}
    public int getEndingTile() {return endingTile;}
}
