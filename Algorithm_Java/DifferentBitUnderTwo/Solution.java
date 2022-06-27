/*
프로그래머스 level2, 2개 이하로 다른 비트

양의 정수 x에 대한 함수 f(x)는 x보다 크고 x와 비트가 1~2개 다른 수들 중에서 제일 작은 수이다.
예를 들어, f(2) = 3이다. 2=10(2), 3 = 11(2)
배열의 각 원소를 변수로 입력할 때, 얻을 수 있는 함숫값들을 반환하는 문제이다.

알고리즘

(1)변수 x의 가장 낮은 위치의 0을 찾는다.
(2)해당 위치의 0을 1로 바꾸고 그 다음 비트를 1로 바꾸면 된다.

 */

package test.DifferentBitUnderTwo;

class Solution {

    public static void main(String[] args){
        Solution test = new Solution();
        long[] numbers = {2,7};
        test.solution(numbers);
    }
    public long[] solution(long[] numbers) {

        long[] answer = new long[numbers.length];

        for(int i=0; i<numbers.length; i++)
        {
            answer[i] = getNum(numbers[i]);
            System.out.printf("numbers:%s, answer:%s\n"
                    ,Long.toBinaryString(numbers[i])
                    ,Long.toBinaryString(answer[i]));
             System.out.println();
        }

        return answer;
    }

    public long getNum(long number)
    {
        int lowestZeroBit = 0;
        long temp = number;

        while(number % 2 != 0)
        {
            number = number >> 1L;
            lowestZeroBit++;
        }

        number = temp;
        long bitmask = (1L << lowestZeroBit) - 1L;
        long right = (bitmask & number);
        long left = number - right;
        long answer = left+ (1L << lowestZeroBit) + (right >> 1);

        return answer;
    }
}