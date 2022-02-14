package test.FindMaximalRectangle;

class Solution
{
    public int solution(int [][]board)
    {
        int max = 0;

        for(int i=0;i<board.length;i++)
        {
            for(int j=0; j<board[0].length;j++)
            {
                if (i != 0 && j != 0) {
                    board[i][j] = (board[i][j] != 0)
                            ? Math.min(board[i - 1][j - 1], Math.min(board[i - 1][j], board[i][j - 1])) + 1
                            : 0;
                }
                if(board[i][j] > max)
                    max = board[i][j];
                // System.out.printf("(%d, %d): %d", i, j, board[i][j]);
            }
            // System.out.println();
        }
        return max*max;
    }
}
