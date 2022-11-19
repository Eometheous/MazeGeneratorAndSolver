# Project Description
In this project, we implement a random maze generator and solver. Mazes can be solved with either Breadth First Search or Depth First Search. We output the mazes and their solutions into a text file located in ```src/main/saved_mazes/```


# Sample output
located at ```src/main/saved_mazes/3x4maze925858445.txt```
```
+-----+-----+-----+
|     |           |
+     +-----+     +
|           |     |
+-----+     +     +
|     |           |
+     +     +     +
|           |     |
+-----+-----+-----+

Solving Using DFS
+-----+-----+-----+
|  0  |           |
+     +-----+     +
|  1     2  |     |
+-----+     +     +
|  6  |  3     7  |
+     +     +     +
|  5     4  |  8  |
+-----+-----+-----+

+-----+-----+-----+
|  #  |           |
+     +-----+     +
|  #     #  |     |
+-----+     +     +
|     |  #     #  |
+     +     +     +
|           |  #  |
+-----+-----+-----+

Solution Path: 0 3 4 7 8 11 
Tiles Visited: 8

Solving Using BFS
+-----+-----+-----+
|  0  |           |
+     +-----+     +
|  1     2  |     |
+-----+     +     +
|     |  3     5  |
+     +     +     +
|  6     4  |  7  |
+-----+-----+-----+

+-----+-----+-----+
|  #  |           |
+     +-----+     +
|  #     #  |     |
+-----+     +     +
|     |  #     #  |
+     +     +     +
|           |  #  |
+-----+-----+-----+

Solution Path: 0 3 4 7 8 11 
Tiles Visited: 7
```

# Testing
Testing is done in MazeSolverTest.java located at ```src\main\java\main\mazes\MazeSolverTest.java``` It reads a textfile to generate test mazes with the expected solution path, tiles visited for DFS, and tiles visited for BFS. The test file is located at ```src\main\java\main\mazes\testMazes.txt``` Once the test is ran, it will output the maze files in the saved_mazes folder
