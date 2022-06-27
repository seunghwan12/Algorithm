/*
프로그래머스, level2, 행렬 테두리 회전하기

행렬에서 두 점이 주어질 때, 두 점이 만드는 사각형의 테두리에 있는 원소들을
시계방향으로 회전시키는 문제이다.

(1)행렬이 주어지지 않고 row, column이 주어지므로 두 값으로 행렬을 만들고
원소들을 규칙에 따라 저장한다.
(2) queries배열에 각 행의 원소(두 점의 행과 열)로 테두리의 숫자를 이동시키는
블럭을 만들고
(3) 숫자들을 이동시킴과 동시에 최솟값을 구한다.
위와 같이 문제를 나누어서 각 과정을 구현하였다.

다른 사람의 풀이과 비교하였을 때, 내 풀이와 다른 부분은 모듈화와 간결함이다.
내 풀이는 solution메소드 안에서 모든 과정을 구현하였지만, 다른 사람의 풀이는
rotate메소드를 추가로 구현하여 보기 쉽게 만들었다. 또한 내 풀이는 사각형의 상하좌우 변을
움직이는 부분을 4개의 블럭으로 모두 구현하였지만, 다른 사람의 풀이는 상하좌우에서 움직이는
패턴을 미리 배열로 만들어두고 1개의 블록에서 각 상황에 따라 다른 패턴을 사용하도록 하여
코드의 중복성을 줄였다.
 */

package test.MatrixRotation;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[][] arr = new int[rows][columns];
        int num = queries.length;
        int[] answer = new int[num];

        // (rows * columns) 배열 생성
        for(int i=0; i< rows; i++)
        {
            for(int j=0; j<columns; j++)
            {
                arr[i][j] = i*columns+(j+1);
            }
        }

        // 회전 반복
        for(int k=0; k<num; k++)
        {
            int low = queries[k][0]-1;
            int high = queries[k][2]-1;
            int left = queries[k][1]-1;
            int right = queries[k][3]-1;

            int min = arr[low][left];
            int prev = min ; int now = 0;

            // 위쪽 변, 왼쪽에서 오른쪽으로 이동
            for(int i=left; i< right; i++)
            {
                now = arr[low][i+1];
                if(now < min)
                    min = now;
                arr[low][i+1] = prev;
                prev = now;
            }

            // 오른쪽 변, 위에서 아래로 이동
            for(int i=low; i < high; i++)
            {

                now = arr[i+1][right];
                if(now < min)
                    min = now;
                arr[i+1][right] = prev;
                prev = now;

            }

            // 아래쪽 변, 오른쪽에서 왼쪽으로 이동
            for(int i=right; i> left; i--)
            {

                now = arr[high][i-1];
                if(now < min)
                    min = now;
                arr[high][i-1] = prev;
                prev = now;
            }

            // 왼쪽 변, 아래에서 위로
            for(int i=high; i> low; i--)
            {

                now = arr[i-1][left];
                if(now < min)
                    min = now;
                arr[i-1][left] = prev;
                prev = now;

            }
            answer[k] = min;
        }

        return answer;
    }
}
