package DSA_LAB;

import DSA_LAB.GenericStack;
import java.util.*;

class Cell {
    int x, y, dir;

    Cell(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}

public class RatInMazeUsingStack {

    static int N = 4;

    static boolean isSafe(int[][] maze, int x, int y, boolean[][] visited) {
        return (x >= 0 && x < N &&
                y >= 0 && y < N &&
                maze[x][y] == 1 &&
                !visited[x][y]);
    }

    static void solveMaze(int[][] maze) {

        GStack<Cell> stack = new GStack<>();
        stack.setCapacity(N*N);
        boolean[][] visited = new boolean[N][N];

        int[] dx = {1, 0, -1, 0};  // D, R, U, L
        int[] dy = {0, 1, 0, -1};

        stack.push(new Cell(0, 0, 0));
        visited[0][0] = true;

        while (!stack.isEmpty()) {

            Cell current = stack.peek();
            int x = current.x;
            int y = current.y;
            int dir = current.dir;

            // Destination reached
            if (x == N - 1 && y == N - 1) {
                System.out.println("Path Found:");
                while (!stack.isEmpty()) {
                    Cell c = stack.pop();
                    System.out.print("(" + c.x + "," + c.y + ") ");
                }
                return;
            }

            if (dir < 4) {
                current.dir++;  // try next direction next time

                int newX = x + dx[dir];
                int newY = y + dy[dir];

                if (isSafe(maze, newX, newY, visited)) {
                    stack.push(new Cell(newX, newY, 0));
                    visited[newX][newY] = true;
                }

            } else {
                // Backtrack
                stack.pop();
            }
        }

        System.out.println("No Path Found");
    }

    public static void main(String[] args) {

        int[][] maze = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 1, 0, 0},
                {1, 1, 1, 1}
        };

        solveMaze(maze);
    }
}