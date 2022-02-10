/*
프로그래머스 level2, 쿼드압축 후 개수 세기

0과 1로 이루어진 2^n*2^n 크기의 배열이 있다. 이 트리를 쿼드 트리의 방식으로 압축하고
트리 내에 존재하는 모든 0과 1의 개수를 구한다.

처음 문제를 풀 때는 brute force방식으로 1*1, 2*2 ... 2^n*2^n 크기의 사각형을
모든 영역에서 검사하였다. 다만 2차원 boolean배열을 선언하여 해당 위치가 압축되면, true를
저장한다. 따라서 다른 크기의 사각형을 검사할 때 해당 위치를 포함하는 사각형을 검사하는 것을
생략할 수 있다.

하지만 이렇게 문제를 풀 경우, 이미 압축이 된 공간을 재탐색하는 오버헤드가 발생한다.
따라서 이러한 비효율성을 줄이기 위해 재귀함수를 사용하였다. 해당 영역이 압축가능한 경우에는
함수를 종료하지만, 압축이 불가능한 경우 해당 영역을 4개의 정사각형으로 쪼개어 다시 검사하도록 한다.


 */

package test.QuadTreeCompression;

public class Solution {
    int[] answer = new int[2];

    public int[] solution(int[][] arr) {
        int end = arr.length-1;

        divide(arr, 0,end,0,end);

        return answer;
    }

    // rs: rowStart, re: rowEnd, cs: colStart, ce: colEnd
    public void divide(int[][] arr, int rs, int re, int cs, int ce)
    {
        // cell 한개만을 확인하거나 n*n개의 cell이 모두 동일한 값을 가지는 경우
        if((rs == re && cs == ce)|| check(arr, rs, re, cs, ce))
            answer[arr[rs][cs]]++;
        else
        {
            int rm = (rs+re)/2; int cm = (cs+ce)/2;
            divide(arr, rs, rm, cs, cm);
            divide(arr, rm+1, re, cs, cm);
            divide(arr, rs, rm, cm+1, ce);
            divide(arr, rm+1, re, cm+1, ce);
        }
    }

    public boolean check(int[][] arr, int rs, int re, int cs, int ce)
    {
        for(int i=rs; i<= re; i++)
        {
            for(int j=cs; j<=ce; j++)
            {
                if(arr[rs][cs] != arr[i][j])
                    return false;
            }
        }

        return true;
    }

    public static void main(String[] args)
    {
        Solution test = new Solution();
        int[][] arr = {{1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 1, 1, 1, 1},
                {0, 1, 0, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 0, 0, 1},
                {0, 0, 0, 0, 1, 1, 1, 1}};

        test.solution(arr);
    }
}
