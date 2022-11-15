package main.graphTheoryAlgorithms;
import java.util.Queue;

import main.linkedLists.LinkedList;
import main.linkedLists.Node;
import main.mazes.Coordinate;
import main.mazes.Maze;
import main.mazes.MazeWalls;

public class BFS {

    public static void breadthFirstSearch(Maze maze, boolean[][] visited, Coordinate currentCoords){
        int x = currentCoords.getX();
        int y = currentCoords.getY();

        MazeWalls[][] actualMaze = maze.getMazeWalls();

        Queue<Integer> nextTilesQueue = new java.util.LinkedList<>();
        nextTilesQueue.add(maze.nodeAt(x,y));
        visited[x][y] = true;
        while(!nextTilesQueue.isEmpty() && !maze.foundSolution()){
            int node = nextTilesQueue.poll();
            currentCoords = maze.positionOf(node);
            x = currentCoords.getX();
            y = currentCoords.getY();
            if(node == maze.getEndingTile()){
                actualMaze[x][y].setChar(String.valueOf(maze.getTilesVisited() % 10));
                backtrack(maze, currentCoords);
                return;
            }
            actualMaze[x][y].setChar(String.valueOf(maze.getTilesVisited() % 10));
            maze.incrementTilesVisited();

            Node neighbors = maze.getMazeGraph().getAdjList()[node].getHead();
            while (neighbors != null) {
                currentCoords = maze.positionOf(neighbors.getItem());
                x = currentCoords.getX();
                y = currentCoords.getY();
                if (!visited[x][y]) {
                    visited[x][y] = true;
                    nextTilesQueue.add(neighbors.getItem());
                }
                neighbors = neighbors.getNext();
            }
        }
    }

    public static void backtrack(Maze maze, Coordinate current){
        maze.display();
        MazeWalls[][] actualMaze = maze.getMazeWalls();

        for(int i = 0; i < maze.getLength(); i++){
            for(int j = 0; j < maze.getHeight(); j++){
                actualMaze[i][j].setChar(" ");
            }
        }

        actualMaze[current.getX()][current.getY()].setChar("#");
        Node tile = maze.getMazeGraph().getAdjList()[maze.nodeAt(current.getX(), current.getY())].getHead();
        LinkedList path = new LinkedList();
        path.add(maze.getEndingTile());
        while (tile.getItem() != maze.getStartingTile()) {
            if (tile.getNext() == null){
                path.add(tile.getItem());
                current = maze.positionOf(tile.getItem());
                maze.getMazeWalls()[current.getX()][current.getY()].setChar("#");
                tile = maze.getMazeGraph().getAdjList()[tile.getItem()].getHead();
            }
            tile = tile.getNext();
        }
        current = maze.positionOf(maze.getStartingTile());
        maze.getMazeWalls()[current.getX()][current.getY()].setChar("#");
        path.add(maze.getStartingTile());

        maze.setSolution(path);
    }
}
