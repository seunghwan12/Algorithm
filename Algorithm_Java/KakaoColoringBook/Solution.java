/*
프로그래머스 level2, 컬러링북

그림이 2차원으로 주어지고 그림에 있는 영역의 개수와 가장 큰 영역의 넓이를 계산하는 문제이다.
영역은 같은 색으로 구성되며 상하좌우로 연결된다.

각 위치에 방문여부를 나타내는 이차원 boolean visited 배열을 선언하였다. 그리고 영역의 정의에 따라
영역의 넓이를 구하는 알고리즘을 dfs방식으로 구현하였다. 상하좌우를 탐색하여 같은 색이고 방문된 적이 없으면
그 위치로 이동하여 방문한 것으로 visited를 변경하고 다시 함수를 호출하는 재귀함수로 함수를 만들었다.

solution 메소드에서는 그림의 모든 위치를 방문하고, 해당 위치가 방문된 적이 없으면 dfs함수를 호출하여 영역의
넓이를 구하고 영역의 개수를 누적한다. 이때 구한 영역의 넗이와 최대 영역 넓이를 계속 비교하여
반복문 종료 이후에 최대 영역의 넓이를 구할 수 있다.
 */

package test.KakaoColoringBook;

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
                if(!visited[row][col] && picture[row][col] != 0)
                {
                    size = 0;
                    size = dfsCallStack(size, picture, row, col);
                    numberOfArea++;

                    if(size > maxSize)
                        maxSize = size;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSize;
        // System.out.printf("numberOfArea: %d, maxSize:%d\n", numberOfArea, maxSize);

        return answer;
    }

    public int dfsCallStack(int size, int[][] picture, int row, int col)
    {
        visited[row][col] = true;
        size++;

        if(row >= 1 && !visited[row - 1][col]
                && picture[row-1][col] == picture[row][col])
        {
            size = dfsCallStack(size, picture, row-1, col);
        }

        if(row < height - 1 && !visited[row + 1][col]
                && picture[row+1][col] == picture[row][col])
        {
            size = dfsCallStack(size, picture, row + 1, col);
        }

        if(col >= 1 && !visited[row][col - 1]
                && picture[row][col-1] == picture[row][col])
        {
            size = dfsCallStack(size, picture, row, col - 1);
        }

        if(col < width - 1 && !visited[row][col + 1]
                && picture[row][col+1] == picture[row][col])
        {
            size = dfsCallStack(size, picture, row, col + 1);
        }

        return size;
    }
}