package main;

import main.graphs.AdjacencyListGraph;
import main.mazes.Maze;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MazeSolverTest {
    private static final AdjacencyListGraph mazeGraph = new AdjacencyListGraph(30);
    private static Maze testMaze;
    @BeforeAll
    static void createMaze() {
        // add paths to testMaze
        mazeGraph.addEdge(0,1);
        mazeGraph.addEdge(1,6);
        mazeGraph.addEdge(6,7);
        mazeGraph.addEdge(7,12);
        mazeGraph.addEdge(12,13);
        mazeGraph.addEdge(13,14);
        mazeGraph.addEdge(14,9);
        mazeGraph.addEdge(9,4);
        mazeGraph.addEdge(4,3);
        mazeGraph.addEdge(3,8);
        mazeGraph.addEdge(3,2);
        mazeGraph.addEdge(14,19);
        mazeGraph.addEdge(19,18);
        mazeGraph.addEdge(18,17);
        mazeGraph.addEdge(17,22);
        mazeGraph.addEdge(22,27);
        mazeGraph.addEdge(27,26);
        mazeGraph.addEdge(26,25);
        mazeGraph.addEdge(25,20);
        mazeGraph.addEdge(20,15);
        mazeGraph.addEdge(15,16);
        mazeGraph.addEdge(16,21);
        mazeGraph.addEdge(16,11);
        mazeGraph.addEdge(11,10);
        mazeGraph.addEdge(10,5);
        mazeGraph.addEdge(27,28);
        mazeGraph.addEdge(28,23);
        mazeGraph.addEdge(23,24);
        mazeGraph.addEdge(24,29);

        // create testMaze with testGraph
        testMaze = new Maze(mazeGraph, 5, 6);
    }

    @Test
    public void TestDisplay() {
        testMaze.display();
    }
}
