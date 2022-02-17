/*
프로그래머스 level2, 숫자의 표현

자연수 n을 연속한 자연수로 표현하는 방법의 개수를 구하시오.
ex. 15 = 1+2+3+4+5, 15 = 4+5+6

주어진 자연수:n, k:연속한 자연수의 개수, m:연속한 자연수에서 가장 작은 수
라고 할 때,

n = m+(m+1)+ ... +(m+k-1) = {(2m+k-1)*k}/2

2n = (2m+k-1)*k

2mk = 2n - k^2+k

m = (2n- k^2+k)/2k

따라서 우변의 수가 자연수가 될 때, k개의 연속된 자연수로 표현하는 방법이 존재한다.

 */

package test.RepresentationOfNumber;

class Solution {
    public int solution(int n) {
        int answer = 1;
        int numerator = 0;
        for(int k=2; (numerator = (2*n-k*k+k))>0; k++)
        {
            if(numerator % (2*k) ==0)
                answer++;
        }
        return answer;
    }
}
