/*
프로그래머스 level1, 월간코드챌린지, 음양더하기
url: https://programmers.co.kr/learn/courses/30/lessons/76501

 */

package test.PlusMinusAddition;

class Solution {
    public int solution(int[] absolutes, boolean[] signs) {
        int answer = 0;
        int one;
        for(int i=0; i< signs.length; i++)
        {
            one = signs[i] ? absolutes[i] : -absolutes[i];
            answer += one;
        }
        return answer;
    }
}
