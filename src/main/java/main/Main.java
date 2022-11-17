package main;

import main.mazes.Maze;
import main.mazes.MazeSolver;

public class Main {
    public static void main(String[] args) {
        Maze maze34 = new Maze(3, 4);
        MazeSolver.solveMazeUsingDFS(maze34);
        MazeSolver.solveMazeUsingBFS(maze34);
    }
}
