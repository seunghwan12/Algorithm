package test;

/*
프로그래머스 level1, monthly code challenge, 내적
 */
class Solution {
    public int solution(int[] a, int[] b) {
        int answer = 0;
        for(int i=0; i< a.length; i++)
            answer += a[i]*b[i];
        return answer;
    }
}
