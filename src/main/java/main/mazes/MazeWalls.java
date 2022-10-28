package main.mazes;

public class MazeWalls {
    private static final String H_WALL = "-----";
    private static final String EMPTY_H_WALL = "     ";
    private static final String V_WALL = "|";
    private static final String EMPTY_V_WALL = " ";
    private static final String CORNER = "+";

    private String top, bottom, left, right;
    private boolean isEnd;
    private int number;

    public MazeWalls() {
        number = -1;
        top = H_WALL;
        bottom = H_WALL;
        left = V_WALL;
        right = V_WALL;
        isEnd = false;
    }
    public String getTop() {
        if (isEnd) return CORNER + top + CORNER + "\n";
        return CORNER + top;
    }

    public String getMiddle() {
        if (isEnd) return left + "     " + right + "\n";
        return left + "     ";
    }

    public String getBottom() {
        if (isEnd) return CORNER + bottom + CORNER + "\n";
        return CORNER + bottom;
    }

    public void setTopConnection(boolean connection) {
        if (connection) top = EMPTY_H_WALL;
        else top = H_WALL;
    }

    public void setBottomConnection(boolean connection) {
        if (connection) bottom = EMPTY_H_WALL;
        else bottom = H_WALL;
    }

    public void setLeftConnection(boolean connection) {
        if (connection) left = EMPTY_V_WALL;
        else left = V_WALL;
    }

    public void setRightConnection(boolean connection) {
        if (connection) right = EMPTY_V_WALL;
        else right = V_WALL;
    }

    public void setEnd(boolean e) {
        isEnd = e;
    }

    public void setNumber(int n) {
        number = n;
    }

}
