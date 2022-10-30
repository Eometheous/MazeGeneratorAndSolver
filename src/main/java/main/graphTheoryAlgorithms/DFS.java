package main.graphTheoryAlgorithms;

import main.linkedLists.LinkedList;
import main.linkedLists.Node;
import main.mazes.Coordinate;
import main.mazes.Maze;

public class DFS {
    /**
     * A depth first search algorithm that goes through a maze
     * @param maze              the maze we are searching through
     * @param visited           the visited coordinates
     * @param currentPosition   current position in the maze
     */
    public static void depthFirstSearch(Maze maze, boolean[][] visited, Coordinate currentPosition) {
        int x = currentPosition.getX();
        int y = currentPosition.getY();
        int currentNode = maze.node(x,y);

        // grab next tile from maze at current node and store it
        Node nextTile = maze.getMazeGraph().getAdjList()[currentNode].getHead();
        while (nextTile != null && maze.getSolution().getHead() == null) {
            // if current tile hasn't been visited yet
            if (!visited[x][y]) {
                visited[x][y] = true;
                maze.getMazeWalls()[currentPosition.getX()][currentPosition.getY()].setChar(String.valueOf(maze.getTilesVisited() % 10));
                maze.incrementTilesVisited();
            }
            // grab next coordinates
            Coordinate nextTileCoords = maze.position(nextTile.getItem());

            // if next tile is the end of the maze
            if (nextTile.getItem() == maze.getEndingTile()) {
                maze.getMazeWalls()[nextTileCoords.getX()][nextTileCoords.getY()].setChar(String.valueOf(maze.getTilesVisited() % 10));
                backTrack(maze, nextTileCoords);
            }

            // if next position hasn't been visited yet
            if (!visited[nextTileCoords.getX()][nextTileCoords.getY()]) {
                depthFirstSearch(maze, visited, nextTileCoords);
            }
            nextTile = nextTile.getNext();
        }
    }

    /**
     * Backtracks through the maze to return the solution of the maze
     * @param maze              maze we are backtracking through
     * @param currentPosition   current position in the maze
     */
    private static void backTrack(Maze maze, Coordinate currentPosition) {
        maze.display();
        // clear all characters from the maze
        for (int x = 0; x < maze.getLength(); x++) {
            for (int y = 0; y < maze.getHeight(); y++)
                maze.getMazeWalls()[x][y].setChar(" ");
        }
        // start backtracking and adding # to the path
        maze.getMazeWalls()[currentPosition.getX()][currentPosition.getY()].setChar("#");
        Node tile = maze.getMazeGraph().getAdjList()[maze.node(currentPosition.getX(), currentPosition.getY())].getHead();
        LinkedList path = new LinkedList();
        path.add(maze.getEndingTile());
        while (tile.getItem() != maze.getStartingTile()) {
            if (tile.getNext() == null){
                path.add(tile.getItem());
                currentPosition = maze.position(tile.getItem());
                maze.getMazeWalls()[currentPosition.getX()][currentPosition.getY()].setChar("#");
                tile = maze.getMazeGraph().getAdjList()[tile.getItem()].getHead();
            }
            tile = tile.getNext();
        }
        currentPosition = maze.position(maze.getStartingTile());
        maze.getMazeWalls()[currentPosition.getX()][currentPosition.getY()].setChar("#");
        path.add(maze.getStartingTile());

        maze.setSolution(path);
    }
}
