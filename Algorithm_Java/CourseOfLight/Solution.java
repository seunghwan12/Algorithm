package test.CourseOfLight;

import java.util.ArrayList;

class Solution {
    char[][] gridWithChar;
    boolean gridWithDir[][][];
    int rowLen; int colLen;
    int curRow, curCol, curOutDir;
    int nextRow, nextCol, nextOutDir;
    ArrayList<Integer> cycles = new ArrayList<>();

    public int[] solution(String[] grid) {

        rowLen = grid.length; colLen = grid[0].length();
        /*
        각 격자의 방향까지 표현할 수 있는 3차원 배열 생성
        0: 위, 1: 오른쪽, 2: 아래쪽, 3: 왼쪽
        */
        gridWithDir = new boolean[rowLen][colLen][4];

        // character로 채워진 격자 생성
        gridWithChar = new char[rowLen][colLen];
        makeGrid(grid);

        /*
        격자에서 외부로 빛을 쏘는 방향을 표현하는 격자에서 모든 값이
        true가 될 때까지, 즉 격자의 방향이 모두 채워질 때까지 빛을
        쏜다
        */
        for(int row= 0 ; row < rowLen; row++)
        {
            for(int col=0; col< colLen; col++)
            {
                for(int outDir=0; outDir<4; outDir++)
                {
                    if(!gridWithDir[row][col][outDir]) {
                        nextRow = row; nextCol = col;
                        nextOutDir = outDir;
                        emitLight();
                    }
                }
            }
        }
        int[] answer = cycles.stream().mapToInt((number) -> number).sorted().toArray();
        return answer;
    }

    public void emitLight()
    {
        int count = 0;

        do {
//            System.out.printf("curRow = %d ",curRow);
//            System.out.printf("curCol = %d ",curCol);
//            System.out.printf("nextOutDir = %d " ,nextOutDir);
//            System.out.printf("count = %d " ,count);

            curRow = nextRow; curCol = nextCol;
            curOutDir = nextOutDir;
            gridWithDir[curRow][curCol][curOutDir]
                    = true;
            count++;

            findNextLoc(); findNextDir();

//            System.out.printf("nextRow = %d ",nextRow);
//            System.out.printf("nextCol = %d ",nextCol);
//            System.out.printf("nextOutDir = %d " ,nextOutDir);
//            System.out.println();

        } while(!gridWithDir[nextRow][nextCol][nextOutDir]);
//        System.out.println();
//        System.out.println();

        cycles.add(count);
    }

    public void findNextLoc()
    {
        // 현재 위치에서 빛을 외부로 쏘는 방향으로 다음 위치를 계산
        switch (curOutDir) {

            // 위쪽
            case 0:
                nextRow = curRow+1; nextCol = curCol;
                break;
            case 1:
                nextRow = curRow; nextCol = curCol+1;
                break;
            case 2:
                nextRow = curRow-1; nextCol = curCol;
                break;
            case 3:
                nextRow = curRow; nextCol = curCol-1;
                break;
        }

        // 다음 위치가 격자를 벗어나는 위치라면 격자 안으로 이동
        if(nextRow < 0) {
            nextRow = rowLen-1;
        } else if(nextRow >= rowLen) {
            nextRow = 0;
        }

        if(nextCol < 0) {
            nextCol = colLen-1;
        } else if(nextCol >= colLen) {
            nextCol = 0;
        }
    }

    // 현재 방향과 다음 위치로 다음 위치의 빛이 나가는 방향을 구한다.
    public void findNextDir() {
        switch(gridWithChar[nextRow][nextCol]) {
            case 'S':
                nextOutDir = curOutDir;
                break;
            case 'L':
                nextOutDir = curOutDir -1;
                if(nextOutDir == -1)
                    nextOutDir = 3;
                break;
            case 'R':
                nextOutDir = curOutDir +1;
                if(nextOutDir == 4)
                    nextOutDir = 0;
                break;
        }
    }

    public void makeGrid(String[] grid)
    {
        for(int row=0; row<grid.length; row++)
        {
            for(int col=0; col<grid[row].length(); col++)
                gridWithChar[row][col]
                        = grid[row].charAt(col);
        }
    }

    public static void main(String[] args) {
        Solution test = new Solution();
        String[] grid = {"SRLR", "RRLR", "SLRR"};
        int[] answer = test.solution(grid);
//        System.out.println();
//        System.out.println();

//        for(int ans: answer)
//            System.out.println(ans);
    }
}
