package main.graphs;

public abstract class Graph {
    protected int size;

    public Graph(int s) {
        size = s;
    }

    public abstract void addEdge(int n1, int n2);
    public abstract boolean searchEdge(int n1, int n2);
    public abstract void iterable(int n);
}
