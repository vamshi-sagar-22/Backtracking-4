import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
/*
Optimal Placement of Buildings
approach: similar to n-queens, for each combination of building placement find the minimum distance
time: (h*w)*(h*w)Cn
space: O(h*w)
 */
public class Problem1 {
    int min;
    private int optimumPlacement(int n, int h, int w) {
        min = Integer.MAX_VALUE;
        int[][] grid = new int[h][w];

        for (int i=0;i<h;i++) {
            Arrays.fill(grid[i], -1);
        }
        backtrack(n, h, w, grid, 0);
        return min;
    }

    private void backtrack(int n, int h, int w, int[][] grid, int index) {

        if (n==0) {
            min = Math.min(min, calculateDistance(h, w, grid));
        }

        for (int i=index;i<h*w;i++) {
            int r = i/w;
            int c = i%w;
            grid[r][c] = 0;
            backtrack(n-1, h, w, grid, i+1);
            grid[r][c] = -1;
        }
    }

    private int calculateDistance(int h, int w, int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        int[][] dirs  = {{1,0},{-1,0},{0,1},{0,-1}};
        boolean[][] visited = new boolean[h][w];
        int dist = 0;
        for (int i=0;i<h;i++) {
            for (int j=0;j<w;j++) {
                if (grid[i][j]==0) {
                    q.add(new int[]{i,j});
                    visited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i=0;i<size;i++) {
                int[] curr = q.poll();

                for (int[] dir:dirs) {
                    int nr = curr[0]+dir[0];
                    int nc = curr[1]+dir[1];

                    if (nr>=0 && nc>=0 && nr<h && nc<w && !visited[nr][nc]) {
                        q.add(new int[]{nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }
            dist++;
        }
        return dist-1;
    }

    public static void main(String[] args) {
        Problem1 problem1 = new Problem1();
        problem1.optimumPlacement(3, 4, 4);
    }
}
