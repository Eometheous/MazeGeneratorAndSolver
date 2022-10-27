package main.mazes;

public enum Directions {


    up(0, -1), down(0,1), left(-1, 0), right(1, 0);
    public final int dx, dy;

    Directions(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
};
