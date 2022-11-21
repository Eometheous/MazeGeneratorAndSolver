package main.mazes;
import main.graphs.AdjacencyListGraph;
import main.linkedLists.LinkedList;
import main.linkedLists.Node;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests MazeSolver
 * Test mazes get outputted in the saved_mazes folder located at
 * src/main/saved_mazes/
 */
public class MazeSolverTest {
    private static final AdjacencyListGraph[] testGraphs = new AdjacencyListGraph[10];
    private static final Maze[] testMazes = new Maze[10];
    private static final LinkedList[] testSolutionPaths = new LinkedList[10];
    private static final int[] DFStilesVisited = new int[10];
    private static final int[] BFStilesVisited = new int[10];

    /**
     * Reads testMazes.txt which includes 10 mazes for testing
     *
     * Each test maze within the file has 3 lines
     *
     * The first line creates the test maze
     * The first three integers in this line are the maze length, height, and number of edges
     * The rest of the integers are the graph edges for the maze
     *
     * The second line is the expected solution path.
     * The first iteger is the length of the solution path and the rest of the integers
     * are the solution path itself
     *
     * THe third line is the expected tiles visited for DFS and BFS
     */
    @BeforeAll
    static void beforeAll() {

        String filename = "src/main/java/main/mazes/testMazes.txt";         // file we are reading to get the test mazes
        try {
            Scanner scannerTextFile = new Scanner(new File(filename));
            for (int i = 0; i < 10; i++) {                                  // file contains 10 mazes
                int length = scannerTextFile.nextInt();
                int height = scannerTextFile.nextInt();
                int numberOfEdges = scannerTextFile.nextInt();              // get the number of connections within the maze
                testGraphs[i] = new AdjacencyListGraph(length * height);
                for (int j = 0; j < numberOfEdges; j++) {                   // read all edges
                    int node1 = scannerTextFile.nextInt();
                    int node2 = scannerTextFile.nextInt();
                    testGraphs[i].addEdge(node1, node2);
                }
                int solutionPathLength = scannerTextFile.nextInt();         // get the length of the solution path
                testSolutionPaths[i] = new LinkedList();
                for (int j = 0; j < solutionPathLength; j++) {
                    testSolutionPaths[i].add(scannerTextFile.nextInt());
                }
                DFStilesVisited[i] = scannerTextFile.nextInt();
                BFStilesVisited[i] = scannerTextFile.nextInt();
                testMazes[i] = new Maze(testGraphs[i], length, height);
            }
            scannerTextFile.close();                                        // we are done reading the file
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Tests DFS within MazeSolver and makes sure
     * the outputs match the expected outputs read
     * from testMazes.txt
     */
    @Test
    public void TestDFSMazeSolver4x4() {
        for (int i = 0; i < 2; i++) {
            // solve the maze and return the path
            testDFSOnMaze(i);
        }
    }
    @Test
    public void TestDFSMazeSolver6x6() {
        for (int i = 2; i < 4; i++) {
            // solve the maze and return the path
            testDFSOnMaze(i);
        }
    }
    @Test
    public void TestDFSMazeSolver10x10() {
        for (int i = 4; i < 6; i++) {
            // solve the maze and return the path
            testDFSOnMaze(i);
        }
    }
    @Test
    public void TestDFSMazeSolver20x20() {
        for (int i = 6; i < 8; i++) {
            // solve the maze and return the path
            testDFSOnMaze(i);
        }
    }
    @Test
    public void TestDFSMazeSolver50x50() {
        for (int i = 8; i < 10; i++) {
            testDFSOnMaze(i);
        }
    }
    private void testDFSOnMaze(int i) {
        // solve the maze and return the path
        LinkedList path = MazeSolver.solveMazeUsingDFS(testMazes[i]);

        // make sure tiles visited matches expected value
        assertEquals(DFStilesVisited[i], testMazes[i].getTilesVisited());

        // make sure solution path length matches expected length
        assertEquals(testSolutionPaths[i].numberOfItems(), path.numberOfItems());

        // make sure nodes in path are = expected nodes
        Node expectedNode = testSolutionPaths[i].getHead();
        Node actualNode = path.getHead();
        while (expectedNode != null && actualNode != null) {
            assertEquals(expectedNode.getItem(),actualNode.getItem());
            expectedNode = expectedNode.getNext();
            actualNode = actualNode.getNext();
        }
    }

    /**
     * Tests BFS within MazeSolver and makes sure
     * the outputs match the expected outputs read
     * from testMazes.txt
     */
    @Test
    public void TestBFSMazeSolver4x4() {
        for (int i = 0; i < 2; i++) {
            // solve the maze and return the path
            testBFSOnMaze(i);
        }
    }
    @Test
    public void TestBFSMazeSolver6x6() {
        for (int i = 2; i < 4; i++) {
            // solve the maze and return the path
            testBFSOnMaze(i);
        }
    }
    @Test
    public void TestBFSMazeSolver10x10() {
        for (int i = 4; i < 6; i++) {
            // solve the maze and return the path
            testBFSOnMaze(i);
        }
    }
    @Test
    public void TestBFSMazeSolver20x20() {
        for (int i = 6; i < 8; i++) {
            testBFSOnMaze(i);
        }
    }
    @Test
    public void TestBFSMazeSolver50x50() {
        for (int i = 8; i < 10; i++) {
            // solve the maze and return the path
            testBFSOnMaze(i);
        }
    }
    private void testBFSOnMaze(int i) {
        // solve the maze and return the path
        LinkedList path = MazeSolver.solveMazeUsingBFS(testMazes[i]);

        // make sure tiles visited matches expected value
        assertEquals(BFStilesVisited[i], testMazes[i].getTilesVisited());

        // make sure solution path length matches expected length
        assertEquals(testSolutionPaths[i].numberOfItems(), path.numberOfItems());

        // make sure nodes in path are = expected nodes
        Node expectedNode = testSolutionPaths[i].getHead();
        Node actualNode = path.getHead();
        while (expectedNode != null && actualNode != null) {
            assertEquals(expectedNode.getItem(),actualNode.getItem());
            expectedNode = expectedNode.getNext();
            actualNode = actualNode.getNext();
        }
    }
}
