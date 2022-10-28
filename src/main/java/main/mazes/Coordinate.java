package main.mazes;

public class Coordinate {
    private int x, y;

    public Coordinate() {
        x = 0;
        y = 0;
    }

    public int getX() {return x;}

    public int getY() {return y;}

    public void setX(int x) {this.x = x;}

    public void setY(int y) {this.y = y;}

    public String toString() {
        return String.format("XY: %d %d", x, y);
    }
}
