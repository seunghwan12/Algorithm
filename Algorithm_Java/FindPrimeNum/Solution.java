/*
프로그래머스 level2, k진수에서 소수 개수 구하기

양의 정수 n을 k진수로 바꾸었을 때, 변환된 수 안에 조건에 맞는 소수의 개수 구하는 문제

조건: 변환된 수 안에 0P0, P0, 0P, P 모두에 해당되는 소수
(P는 각 자릿수에 0을 포함하지 않는 소수)
ex. 437674 -> 211020101011(3진수), p= 211, 2, 11

알고리즘
1. 주어진 양의 정수 n을 k진수로 변환한다.
2. 정규식을 사용하여 k진수를 0을 포함한 부분을 기준으로 자른다.
3. 잘린 부분들이 소수인지 확인한다.
 */

package test.FindPrimeNum;

class Solution {
    public int solution(int n, int k) {
        String str = Integer.toString(n, k);
        int answer = 0;

        String[] arr = str.split("0+");

        // for(String division: arr)
        //     System.out.println(division);

        for(String division: arr)
        {
            long number = Long.parseLong(division);

            if(isPrime(number))
                answer++;
        }

        return answer;
    }

    public boolean isPrime(long n)
    {
        if(n <= 1)
            return false;
        else
        {
            for(int i=2 ; i <= (long)Math.sqrt(n); i++)
            {
                if(n % i == 0)
                    return false;
            }

            return true;
        }
    }
}