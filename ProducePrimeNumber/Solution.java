/*
프로그래머스 level1, 소수만들기
uel: https://programmers.co.kr/learn/courses/30/lessons/12977

주어진 숫자 중에서 3개의 숫자를 더했을 경우 소수가 되는 경우의 수를 구하는 문제이다.
문제를 조합과 소수판별으로 분리할 수 있다. 먼저 n개 중에서 3개를 뽑는 조합문제는
중복되는 숫자 조합이 없도록 중첩된 for문을 작성하였다. 소수인지를 판별하는 문제는
소수의 정의를 고려하여 1부터 n/2까지 for문을 돌려 나누어지는 경우에는 소수가 아닌 것을
확인하였다.

모범답안은 소수판별하는 문제에는 더 좋은 시간복잡도를 보였다. 내 풀이는 O(n)인 반면,
모범답안은 O(sqrt(n))의 복잡도였다. "에라토스테네스의 접근"을 활용하여 2부터 sqrt(n)까지만
검사한 것이다. 왜냐하면 나눗셈에서 나누는 수와 몫 중 하나는 제곱근보다 작을 수 밖에 없기 때문이다.


 */

package test.ProducePrimeNumber;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int len = nums.length;
        for(int i=0; i< len-2; i++)
        {
            for(int j=i+1; j<len-1; j++)
            {
                for(int k=j+1; k<len; k++)
                {
                    if(checkPrimeNumber(nums[i]+nums[j]+nums[k]))
                        answer ++;
                }
            }
        }

        return answer;
    }

    public boolean checkPrimeNumber(int number)
    {
        for(int i=2; i<=(int)Math.sqrt(number); i++)
        {
            if(number % i == 0)
                return false;
        }

        return true;
    }
}
