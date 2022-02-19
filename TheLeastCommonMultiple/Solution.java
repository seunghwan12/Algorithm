/*
프로그래머스 level2, N개의 최소 공배수

n개 수의 최소공배수를 구하는 방법은 다음과 같다.

1. 배열의 각 원소들을 소인수분해한다.
2. 2개 이상의 수에 공통으로 들어가는 소인수는 지수가 가장 높은 것을 선택하여 곱하고
1개의 수에만 있는 소인수는 바로 곱하면 n개의 수의 최소공배수를 구할 수 있다.

알고리즘

1. 배열의 각 원소를 소인수분해하여 (소수, 지수)의 형태로 Mapping한다.
   (만약 이미 Map에 key(소수)가 있다면 더 큰 지수로 업데이트한다.)
2. Mapping된 모든 소수^(지수)를 곱하여 최소공배수를 구한다.
 */

package test.TheLeastCommonMultiple;

import java.util.HashMap;
import java.util.Map;

class Solution {
    HashMap<Integer, Integer> map = new HashMap<>();

    public int solution(int[] arr) {
        int answer = 1;

        // 각 원소의 인수를 (소인수, 지수)형태로 mapping한다.
        for(int elem: arr)
            findPrimeFactors(elem);

        // map에 저장된 인수들을 모두 곱하여 최소공배수를 만든다.
        for(Map.Entry<Integer, Integer> entry:map.entrySet())
            answer *= (int)Math.pow(entry.getKey(),entry.getValue());

        return answer;
    }

    public void findPrimeFactors(int number)
    {
        while(number != 1)
        {
            for(int divisor=2; divisor<=number; divisor++)
            {
                if(number % divisor == 0)
                {
                    int exponent = findExponent(number, divisor);
                    number /= (int)Math.pow(divisor, exponent);
//                    System.out.printf("divisor:%d, exponent:%d\n", divisor, exponent);
                    if(map.containsKey(divisor))
                    {
                        if(map.get(divisor) < exponent)
                            map.put(divisor, exponent);
                    }
                    else
                        map.put(divisor, exponent);
                    break;
                }
            }
        }
    }

    public int findExponent(int number, int divisor)
    {
        int exponent = 0;
        int division = divisor;
        while(number % division == 0)
        {
            exponent++;
            division *= divisor;
        }

        return exponent;
    }

    public static void main(String[] args){
        Solution test = new Solution();
        int[] arr ={2,6,8,14};
        test.solution(arr);
    }
}
