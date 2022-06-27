/*

프로그래머스 level1, 모의고사
url: https://programmers.co.kr/learn/courses/30/lessons/42840

3명의 수포자가 찍는 방식과 정답이 주어지고, 가장 많은 문제를 맞힌 사람을 배열에 담아 반환하는 문제.
문제는 크게 세 가지로 분리하여 풀었다. 수포자들이 만드는 정답패턴으로 문제수에 맞춰서 답안을 만드는 부분.
,수포자들의 답안과 정답을 비교하여 맞힌 문제의 수를 계산하는 부분 그리고 가장 많이 맞춘 수포자를 찾는 부분.

 */

package test.MockTest;

import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int len = answers.length;
        int[][] student= new int[3][len];
        int numberOfAnswer[] = new int[3];
        int secondSeq[] = {1,3,4,5};
        int thirdSeq[] = {3,1,2,4,5};

        // 정답패턴으로 수포자의 답안을 생성
        // 수포자1
        for(int i=0; i<len;i++)
            student[0][i] = (i % 5) + 1;
        // 수포자2
        for(int i=0; i<len; i++)
        {
            if(i%2 ==0 )
                student[1][i] = 2;
            else
            {
                int index = (i % 8) /2;
                student[1][i] = secondSeq[index];
            }
        }
        //수포자3
        for(int i=0; i< len; i++)
        {
            int index = (i % 10) / 2;
            student[2][i] = thirdSeq[index];
        }

        // 맞힌 문제의 수를 계산
        for(int i=0; i<3;i++)
        {
            for(int j=0; j< len;j++)
            {
                if(answers[j] == student[i][j])
                    numberOfAnswer[i]++;
            }
        }

        //가장 많이 맞춘 사람을 찾기
        int max = getMax(numberOfAnswer);
        int sol[] = getArray(numberOfAnswer, max);
        return sol;
    }

    public int getMax(int number[])
    {
        int max = 0;
        for(int temp:number)
        {
            if(max < temp)
                max = temp;
        }

        return max;
    }

    // max인 index의 배열을 오름차순으로 리턴
    public int[] getArray(int number[], int max)
    {
        ArrayList<Integer> list = new ArrayList<>();
        int answer[];
        for(int i=0; i< number.length; i++)
        {
            if(number[i] == max)
            {
                list.add(i+1);
            }
        }

        answer= new int[list.size()];
        for(int j=0; j<list.size(); j++)
            answer[j] = list.get(j);

        return answer;
    }
}
