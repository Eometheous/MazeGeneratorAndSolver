package main;

import main.mazes.MazeSolver;
import main.graphs.AdjacencyListGraph;
import main.linkedLists.LinkedList;
import main.linkedLists.Node;
import main.mazes.Maze;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class MazeSolverBFSTest {
    private static final AdjacencyListGraph mazeGraph = new AdjacencyListGraph(30);
    private static Maze testMaze;
    private static final LinkedList expectedPath = new LinkedList();
    @Test
    public void test1(){
        mazeGraph.addEdge(0, 1);
        mazeGraph.addEdge(0, 2);
        mazeGraph.addEdge(1, 2);
        mazeGraph.addEdge(2, 0);
        mazeGraph.addEdge(2, 3);
        mazeGraph.addEdge(3, 3);

        testMaze = new Maze(mazeGraph, 5, 6);

        LinkedList path = MazeSolver.solveMazeUsingBFS(testMaze);

        System.out.println(path);

        testMaze.display();

        Node expectedNode = expectedPath.getHead();
        Node actualNode = path.getHead();
        while (expectedNode != null && actualNode != null) {
            // assertEquals(expectedNode.getItem(), actualNode.getItem());
            assertEquals(expectedNode.getItem(), actualNode.getItem());
            expectedNode = expectedNode.getNext();
            actualNode = actualNode.getNext();
        }
    }
}
