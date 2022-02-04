package test.FriendsForBlock;

class Solution {
    int[][][] rect = {{{0,-1}, {-1,0}, {-1,-1}}, {{0,1}, {-1, 0}, {-1,1}},{{0,-1}, {1,0}, {1,-1}},{{0,1}, {1,0}, {1,1}}};
    char[][] boards = null;
    int[] top = null;
    int m =0; int n=0;

    public int solution(int m, int n, String[] board)     {
        this.m = m; this.n = n;
        top = new int[n];
        boolean[][] found = new boolean[m][n];
        int prev = -1; int now = 0;

        // String[]을 char[][]로 변환
        makeBoard(board);

        // 더 이상 블록이 지워지지 않으면 종료한다.
        while(prev != now)
        {
            prev = now;

            // 보드 위에서 정사각형을 찾아 그 위치를 found배열에 기록한다.
            for(int j=0; j< n ; j++)
            {
                for(int i=top[j]; i<m; i++)
                {
                    if(!found[i][j])
                        findRect(i, j, found);
                }
            }

            // found배열에 따라서 boards배열을 업데이트한다.
            changeBoard(found);

            // 지워진 블록의 개수를 계산한다.
            now = 0;
            for(int j=0; j< n; j++)
                now += top[j];
        }
        return now;
    }

    public void makeBoard(String[] board)
    {
        boards = new char[m][n];

        for(int i=0; i<m; i++)
        {
            for(int j=0; j<n; j++)
                boards[i][j] = board[i].charAt(j);
        }
    }

    //4방면에 정사각형이 있는지를 찾고 정사각형이 존재하는 위치를 found에 기록한다.
    public void findRect(int row, int col, boolean[][] found)
    {
        for(int[][] dir: rect)
        {
            if(!isRightPosition(row+dir[2][0], col+dir[2][1]))
                continue;

            boolean isRect = true;
            for(int[] pos: dir)
            {
                if(boards[row][col] != boards[row+pos[0]][col+pos[1]])
                {
                    isRect = false;
                    break;
                }
            }

            if(isRect)
            {
                found[row][col] = true;
                for(int[] pos:dir)
                    found[row+pos[0]][col+pos[1]] = true;
            }
        }
    }

    public void changeBoard(boolean found[][])
    {
        for(int j=0; j<n; j++)
        {
            for(int i = m-1; i>= top[j]; i--)
            {
                if(!found[i][j]) continue;

                int next = getNextSwapPosition(i,j, found);
                if(next != -1)
                {
                    found[i][j] = false;
                    boards[i][j] = boards[next][j];

                    found[next][j] = true;
                    boards[next][j] = '-';
                }
                else if(boards[i][j] != '-')
                    boards[i][j] = '-';
            }
            for(int i = m-1; i>= top[j]; i--)
            {
                if(found[i][j] == true)
                {
                    top[j] = i+1;
                    break;
                }
            }
        }
    }

    public int getNextSwapPosition(int row, int col, boolean[][] found)
    {
        for(int i = row-1; i>= top[col]; i--)
        {
            if(!found[i][col])
                return i;
        }

        return -1;
    }

    public boolean isRightPosition(int row, int col)
    {
        if(row >= 0 && row < m && col >= 0 && col < n)
            return true;
        else
            return false;
    }

    public static void main(String[] args)
    {
        Solution test = new Solution();
        int m = 8; int n = 2;
        String[] board = {"FF", "AA", "CC", "AA", "AA", "CC", "DD", "FF"};
        test.solution(m, n, board);
    }

}



