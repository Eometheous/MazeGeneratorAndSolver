package main.GraphTheoryAlgorithms;
import java.util.*;

public class BFS {
    ArrayList<String> maze;

    PriorityQueue<Tile> to_process;
    HashSet<Tile> processed;

    int width=0, height=0;

    public BFS(ArrayList<String> maze, int width, int height){
        this.maze = maze;
        this.width = width;
        this.height = height;
        processed = new HashSet<Tile>(width * height);
    }

    // Handling bad inputs.
    public void die(String message){
        System.out.print(message);
        System.exit(0);
    }

    public void bfsAlgo(Tile start, Tile end){
        // Bounds checking so we dont go outside the maze.
        // Validating starting rows and cols, before doing BFS
        if(start.y < 0 || start.x >= width) die("Invalid starting row");
        if(start.x < 0 || start.y >= height) die("Invalid starting col.\n");
        if(end.x < 0 || end.x >= width) die("Invalid ending row\n");
        if(end.y < 0 || end.y >= height) die("Invalid ending col.\n");

        to_process.add(start);

        while(to_process.size() != 0){
            Tile current = to_process.remove(); // Does both top() and pop()

            if(processed.contains(current)) continue;

            to_process.add(current);

            // Handles all 8 direction of each tile (N/W/S/E/NW/SW/NE/SE)
            for(int i = -1; i <= 1; i++){
                for(int j = -1; j <= 1; j++){
                    int newX = current.x + i;
                    int newY = current.y + j;

                    if(newX < 0 || newX >= width || newY < 0 || newY >= height) continue;
                }
            }
            if(backtrack(current, end)) return;
        }
    }

    public boolean backtrack(Tile current, Tile end){
        if(current.x == end.x && current.y == end.y){
            while(true){
                maze.set(current.y, maze.get(current.y).replace(maze.get(current.y).charAt(current.x), '#')); // does maze[current.y][current.x] = '#';
                
                if(current.from_x == -1 || current.from_y == -1) break;

                Tile prev = new Tile(current.from_x, current.from_y, 0, 0, 0);

                if(processed.contains(prev)) current = prev;
            }
            return true;
        }
        return false;
    }

    public void printInfo(){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                System.out.print(maze.get(i).charAt(j));
            }
            System.out.print('\n');
        }
    }
}
