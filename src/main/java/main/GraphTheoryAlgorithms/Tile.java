package main.GraphTheoryAlgorithms;

import java.util.*;

public class Tile {
    final int MAX_SIZE = 1000; // Max size is 1000x1000 size map

    public int x, y;
    public int from_x, from_y;
    double distance;

    public Tile(){}

    public Tile(int x, int y, int from_x, int from_y, double distance){
        this.x = x;
        this.y = y;
        this.from_x = from_x;
        this.from_y = from_y;
    }

    public boolean greaterThan(Tile other){
        return this.distance < other.distance;
    }

    @Override
    public boolean equals(Object object){
        return (object == this);
    }
}
