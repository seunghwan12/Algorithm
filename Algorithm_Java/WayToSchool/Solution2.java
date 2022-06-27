package test.WayToSchool;

public class Solution2 {

    final int denominator = 1000_000_007;

    public int solution(int colLen, int rowLen, int[][] puddles) {

        int[][] grid = new int[rowLen][colLen];
        grid[0][0] = 1;
        for (int[] puddle : puddles)
            grid[puddle[1]-1][puddle[0]-1] = -1;


        for (int row = 0; row < rowLen ; row++) {
            for (int col = 0; col < colLen ; col++) {
                if (grid[row][col] == -1) {
                    grid[row][col] = 0;
                } else {
                    if(row != 0) grid[row][col] += grid[row - 1][col];
                    if(col != 0) grid[row][col] += grid[row][col - 1];
                    grid[row][col] %= denominator;
                }
            }
        }

//        Solution.print(grid);

        return grid[rowLen-1][colLen-1];
    }

    public static void main(String[] args) {
        Solution2 test = new Solution2();
        int row= 4; int col = 3;
        int[][] puddles = {{2, 2}, {3,2}};
        test.solution(col, row, puddles);
    }
}
