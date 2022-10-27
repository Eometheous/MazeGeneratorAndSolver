package main.graphs;
import main.linkedLists.LinkedList;

public class AdjacencyListGraph extends Graph {
    private LinkedList[] adjList;

    public AdjacencyListGraph(int s) {
        super(s);
        adjList = new LinkedList[s];
        for (int i = 0; i < s; i++) {
            adjList[i] = new LinkedList();
        }
    }

    @Override
    public void addEdge(int n1, int n2) {
        adjList[n1].add(n2);
        adjList[n2].add(n1);
    }

    @Override
    public boolean searchEdge(int n1, int n2) {
        return adjList[n1].inList(n2) || adjList[n2].inList(n1);
    }

    @Override
    public void iterable(int n) {
        System.out.println("\nNodes adjacent to " + n + ":");
        System.out.println(adjList[n]);
    }

    public LinkedList[] getAdjList() {
        return adjList;
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < adjList.length; i ++) {
            string.append(i).append(": ").append(adjList[i]).append("\n");
        }
        return string.toString();
    }
}
