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

public class MazeSolverTest {
    private static final AdjacencyListGraph[] testGraphs = new AdjacencyListGraph[10];
    private static final Maze[] testMazes = new Maze[10];
    private static final LinkedList[] testSolutionPaths = new LinkedList[10];
    private static final int[] DFStilesVisited = new int[10];
    private static final int[] BFStilesVisited = new int[10];

    @BeforeAll
    static void beforeAll() {

        String filename = "src/main/java/main/mazes/testMazes.txt";     // file we are reading to get the test arrays

        try {
            Scanner scannerTextFile = new Scanner(new File(filename));
            for (int i = 0; i < 10; i++) {      // file contains 10 arrays
                int length = scannerTextFile.nextInt();
                int height = scannerTextFile.nextInt();
                int numberOfEdges = scannerTextFile.nextInt();
                testGraphs[i] = new AdjacencyListGraph(length * height);
                for (int j = 0; j < numberOfEdges; j++) { // read the 100 integers for the array
                    int edge1 = scannerTextFile.nextInt();
                    int edge2 = scannerTextFile.nextInt();
                    testGraphs[i].addEdge(edge1, edge2);
                }
                int solutionPathLength = scannerTextFile.nextInt();
                testSolutionPaths[i] = new LinkedList();
                for (int j = 0; j < solutionPathLength; j++) {
                    testSolutionPaths[i].add(scannerTextFile.nextInt());
                }
                DFStilesVisited[i] = scannerTextFile.nextInt();
                BFStilesVisited[i] = scannerTextFile.nextInt();
                testMazes[i] = new Maze(testGraphs[i], length, height);
            }
            scannerTextFile.close();    // we are done reading the file
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TestDFSMazeSolver() {
        for (int i = 0; i < 10; i++) {
            // solve the maze and return the path
            LinkedList path = MazeSolver.solveMazeUsingDFS(testMazes[i]);
            // print out the solution path
            testMazes[i].display();
            System.out.println(path + "\n");
            // make sure path length is = expected path length
            assertEquals(DFStilesVisited[i], testMazes[i].getTilesVisited());
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

    @Test
    public void TestBFSMazeSolver() {
        for (int i = 0; i < 10; i++) {
            // solve the maze and return the path
            LinkedList path = MazeSolver.solveMazeUsingBFS(testMazes[i]);
            // print out the solution path
            testMazes[i].display();
            System.out.println(path + "\n");
            // make sure path length is = expected path length
            assertEquals(BFStilesVisited[i], testMazes[i].getTilesVisited());
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
}
