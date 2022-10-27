package main.mazes;

public class Cell {
    private static final String H_WALL = "---";
    private static final String EMPTY_H_WALL = "   ";
    private static final String V_WALL = "|";
    private static final String EMPTY_V_WALL = " ";
    private static final String CORNER = "+";

    boolean top, bottom, left, right;

    public Cell(boolean top, boolean bottom, boolean left, boolean right) {

    }
}
