package test.GameMapShortestPath;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    int m; int n;
    int[][] dirs = {{0,-1}, {0,1}, {-1, 0}, {1, 0}};
    boolean[][] visited;
    int[][] maps;
    Queue<int[]> queue = new LinkedList<int[]>();

    public int solution(int[][] maps) {
        n= maps.length;
        m = maps[0].length;
        int min = -1;
        visited = new boolean[n][m];
        this.maps = maps;

        // (0,0) 위치를 큐에 삽입
        int[] first = {0,0,1};
        queue.add(first);

        // bfs
        while(queue.peek() != null)
        {
            int[] now = queue.remove();
            if(now[0] == n-1 && now[1] == m-1) {
                min = now[2];
                break;
            }
            if(isValidLocation(now[0], now[1])) {
                visited[now[0]][now[1]] = true;
                for (int[] dir : dirs) {
                    int[] path = Arrays.copyOf(now, now.length);
                    path[0] += dir[0];
                    path[1] += dir[1];
                    path[2]++;
                    queue.add(path);
                }
            }
        }
        System.out.println(min);
        return min;
    }


    public boolean isValidLocation(int row, int col)
    {
        if(row <0 || row >= n) return false;
        else if(col <0 || col >= m) return false;
        else if(visited[row][col] || maps[row][col] == 0) return false;
        else return true;
    }

    public static void main(String[] args)
    {
        Solution test = new Solution();
        int[][] maps = {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}};
        test.solution(maps);
    }
}




