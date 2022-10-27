package main.graphs;


public class AdjacencyMatrixGraph extends Graph {
    private int[][] adjMatrix;

    public AdjacencyMatrixGraph(int size) {
        super(size);
        adjMatrix = new int[size][size];
    }

    @Override
    public void addEdge(int n1, int n2) {
        adjMatrix[n1][n2] = 1;
        adjMatrix[n2][n1] = 1;
    }

    @Override
    public boolean searchEdge(int n1, int n2) {
        return adjMatrix[n1][n2] == 1 || adjMatrix[n2][n1] == 1;
    }

    @Override
    public void iterable(int n) {
        System.out.println("\nNodes adjacent to " + n + ":");
        for (int i = 0; i < size; i++) {
            if (adjMatrix[n][i] == 1) System.out.print(i + " ");
        }
    }

    public int[][] getAdjMatrix() {
        return adjMatrix;
    }
}
