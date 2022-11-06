package main.mazes;

public class Coordinate {
    private int x, y;
    boolean visited;
    public Coordinate() {
        x = 0;
        y = 0;
        visited = false;
    }

    public int getX() {return x;}

    public int getY() {return y;}

    public void setX(int x) {this.x = x;}

    public void setY(int y) {this.y = y;}

    public boolean getVisited() { return visited; }
    public void setVisited(boolean visited) { this.visited = visited; }

    public String toString() {
        return String.format("XY: %d %d", x, y);
    }
}
