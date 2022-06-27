package test.WayToSchool;

public class Solution {

    final int denominator = 1000_000_007;

    public int solution(int col, int row, int[][] puddles) {
        int[][] grid = new int[row][col];

        for (int[] puddle : puddles) {
            int tempCol = puddle[0]-1;
            int tempRow = puddle[1]-1;

            grid[tempRow][tempCol] = -1;
        }
        print(grid);

        // row의 길이가 colum의 길이보다 길다면 역행렬로 만든다.
        grid = changeMatrix(grid);

        print(grid);

        int rowLen = grid.length; int colLen = grid[0].length;
        grid[0][0] = 1;
        int highCol = 1;
        int lowCol = colLen - rowLen + 1;

        for ( ; highCol < rowLen; highCol++) {
            for (int idx = 0; idx <= highCol; idx++) {
                if(grid[idx][highCol- idx] == -1)
                    grid[idx][highCol- idx] = 0;
                else if(idx == 0)
                    grid[idx][highCol- idx] = grid[idx][highCol - idx - 1];
                else if(idx == highCol)
                    grid[idx][highCol- idx] = grid[idx - 1][highCol- idx];
                else
                    grid[idx][highCol- idx] = grid[idx - 1][highCol- idx] + grid[idx][highCol - idx - 1];
            }
        }
        print(grid);
        for (; highCol < colLen; highCol++) {
            for (int idx = 0; idx <= rowLen-1; idx++) {
                if(grid[idx][highCol- idx] == -1)
                    grid[idx][highCol- idx] = 0;
                else if(idx == 0)
                    grid[idx][highCol- idx] = grid[idx][highCol - idx - 1];
                else {
                    grid[idx][highCol - idx] = grid[idx - 1][highCol - idx] + grid[idx][highCol - idx - 1];
                }
            }
        }
        print(grid);
        for ( ; lowCol < colLen; lowCol++) {
            for (int idx = 0; idx < colLen - lowCol; idx++) {
                if(grid[rowLen - 1 - idx][lowCol + idx] == -1)
                    grid[rowLen - 1 - idx][lowCol + idx] = 0;
                else
                    grid[rowLen - 1 - idx][lowCol + idx]
                        = grid[rowLen - 1 - idx - 1][lowCol + idx]
                        + grid[rowLen - 1 - idx][lowCol + idx - 1];
            }
        }
        print(grid);

        return grid[rowLen-1][colLen-1];
    }

    public int[][] changeMatrix(int[][] grid) {
        int rowLength = grid.length;
        int colLength = grid[0].length;

        if (rowLength <= colLength) {
            return grid;
        } else {
            int[][] newGrid = new int[colLength][rowLength];

            for (int row = 0; row < rowLength; row++) {
                for (int col = 0; col < colLength; col++) {
                    newGrid[col][row] = grid[row][col];
                }
            }

            return newGrid;
        }
    }

    public static void print(int[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                System.out.printf(" %d", grid[row][col]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution test = new Solution();
        int row= 4; int col = 3;
        int[][] puddles = {{2, 2}, {3,2}};
        test.solution(col, row, puddles);
    }
}
