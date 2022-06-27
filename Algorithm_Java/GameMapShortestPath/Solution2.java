package test.GameMapShortestPath;

class Solution2 {
    int min;
    int m; int n;
    int[][] dirs = {{0,-1}, {0,1}, {-1, 0}, {1, 0}};
    boolean[][] visited;
    int[][] maps;
    public int solution(int[][] maps) {
        n= maps.length;
        m = maps[0].length;
        min = n*m+1;
        visited = new boolean[n][m];
        this.maps = maps;

        dfs(0, 0, 0);

        return min == m*n+1 ? -1 : min;
    }

    public void dfs(int row, int col, int cnt)
    {

        if(row== n-1 && col==m-1)
        {
            cnt++;
            if(min > cnt) min = cnt;
        }
        if(isValidLocation(row, col))
        {
            visited[row][col] = true;
            for(int[] dir: dirs)
                dfs(row+dir[0], col+dir[1], cnt+1);
            visited[row][col] = false;
        }
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
        Solution2 test = new Solution2();
        int[][] maps = {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}};
        test.solution(maps);
    }
}
