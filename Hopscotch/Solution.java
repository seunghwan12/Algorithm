/*
프로그래머스 level2, 땅따먹기
 */

package test.Hopscotch;

class Solution {
    final int column = 4;

    int solution(int[][] land) {
        int answer = 0;
        int maxRow = land.length -1;

        // 두번째 행부터 마지막행까지 각 열의 최댓값 찾기
        for(int row=1; row<land.length; row++)
        {
            for(int col = 0; col < column; col++)
                land[row][col] += findMax(land[row - 1], col);
        }

        // 마지막 행에서 최댓값 찾기
        for(int col = 0; col< column; col++)
        {
            if(land[maxRow][col] > answer)
                answer = land[maxRow][col];
        }

        return answer;
    }

    public int findMax(int[] land, int col)
    {
        int max = 0;

        for(int i=0; i<column; i++)
        {
            if(i != col && land[i] > max)
                max = land[i];
        }
        return max;
    }
}
