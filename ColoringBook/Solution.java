package test.ColoringBook;

import java.util.*;

class Solution
{
    boolean[][] visited = new boolean[100][100];
    int height, width;

    public int[] solution(int m, int n, int[][] picture)
    {
        height = m; width = n;

        int numberOfArea = 0;
        int maxSize = 0;
        int size = 0;
        for(int row =0; row< height ; row++)
        {
            for(int col =0; col< width ; col++)
            {
                if(visited[row][col] == false && picture[row][col] != 0)
                {
                    size = 0;
                    size = dfs(size, picture, row, col);
                    numberOfArea++;

                    if(size > maxSize)
                        maxSize = size;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSize;
//        System.out.printf("numberOfArea: %d, maxSize:%d\n", numberOfArea, maxSize);

        return answer;
    }

    public int dfs(int size, int[][] picture, int row, int col)
    {
        visited[row][col] = true;
        size++;

//        System.out.printf("row: %d, col:%d\n", row, col);

        if(row >= 1 && visited[row-1][col] == false
                && picture[row-1][col] == picture[row][col])
        {
            size = dfs(size, picture, row-1, col);
        }

        if(row < height - 1 && visited[row+1][col] == false
                && picture[row+1][col] == picture[row][col])
        {
            size = dfs(size, picture, row + 1, col);
        }

        if(col >= 1 && visited[row][col-1] == false
                && picture[row][col-1] == picture[row][col])
        {
            size = dfs(size, picture, row, col - 1);
        }

        if(col < width - 1 && visited[row][col+1] == false
                && picture[row][col+1] == picture[row][col])
        {
            size = dfs(size, picture, row, col + 1);
        }

        return size;
    }

    public static void main(String[] args)
    {
        Solution test = new Solution();
        int m = 6; int n = 4;
        int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
        test.solution(m,n,picture);
    }
}