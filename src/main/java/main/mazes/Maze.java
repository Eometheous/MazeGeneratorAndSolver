package main.mazes;

import main.graphs.AdjacencyListGraph;

import java.util.Random;

public class Maze {
    private final int length;
    private final int height;
    private final AdjacencyListGraph maze;
    private final Cell[][] mazeCells;
    public Maze(int length, int height) {
        this.length = length;
        this.height = height;
        maze = new AdjacencyListGraph(this.length * this.height);
        mazeCells = new Cell[this.length][this.height];
        generate(0,0);
    }

    public void generate(int x, int y) {
        Directions[] directions = Directions.values();
        Random randomDirectionGenerator = new Random();

        // randomize directions order
        for (int i = 0; i < directions.length; i++) {
            int randomDirection = randomDirectionGenerator.nextInt(4);
            Directions temp = directions[i];
            directions[i] = directions[randomDirection];
            directions[randomDirection] = temp;
        }

        for (Directions direction: directions) {
            int nextX = x + direction.dx;
            int nextY = y + direction.dy;
            if (neighborExists(nextX, length) && neighborExists(nextY, height) && maze.getAdjList()[position(nextX, nextY)].numberOfItems() == 0) {
                maze.addEdge(position(x, y),position(nextX,nextY));
                generate(nextX, nextY);
            }
        }

        for (y = 0; y < height; y++) {
            for (x = 0; x < length; x++) {
                createMazeWalls(x, y);
            }
        }
    }
    private int position(int x, int y) {
        if (y == 0) return x;
        return x + (y * this.length);
    }
    public boolean neighborExists(int cell, int bound) {
        return (cell >= 0) && (cell < bound);
    }

    public void display() {
        System.out.println(maze);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < length; x++) {
                System.out.print(mazeCells[x][y].getTop());
            }
            for (int x = 0; x < length; x++) {
                System.out.print(mazeCells[x][y].getMiddle());
            }
        }
        // print bottom row
        for (int x = 0; x < length; x++) {
            System.out.print(mazeCells[x][height - 1].getBottom());
        }
    }

    private void createMazeWalls(int x, int y) {
        int current = position(x, y);
        int top = position(x, y - 1);
        int bottom = position(x, y + 1);
        int left = position(x - 1, y);
        int right = position(x + 1, y);
        mazeCells[x][y] = new Cell();
        mazeCells[x][y].setNumber(current);
        if (neighborExists(y + Directions.up.dy, height))
            mazeCells[x][y].setTopConnection(maze.searchEdge(current,top));
        if (neighborExists(y + Directions.down.dy, height))
            mazeCells[x][y].setBottomConnection(maze.searchEdge(current,bottom));
        if (neighborExists(x + Directions.left.dx, length))
            mazeCells[x][y].setLeftConnection(maze.searchEdge(current,left));
        if (neighborExists(x + Directions.right.dx, length))
            mazeCells[x][y].setRightConnection(maze.searchEdge(current,right));
        if (x == length - 1) mazeCells[x][y].setEnd(true);
    }
}
