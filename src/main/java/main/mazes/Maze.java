package main.mazes;

import main.graphs.AdjacencyListGraph;

import java.util.Random;

public class Maze {
    private int length, height;
    private AdjacencyListGraph maze;
    public Maze(int length, int height) {
        this.length = length;
        this.height = height;
        maze = new AdjacencyListGraph(this.length * this.height);
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
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < height; j++) {

            }
        }
    }
}
