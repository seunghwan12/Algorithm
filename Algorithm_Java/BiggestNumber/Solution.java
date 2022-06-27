package test.BiggestNumber;

import java.util.Arrays;

class Solution {
    public String solution(int[] numbers) {
        Integer[] numbers2 = new Integer[numbers.length];
        StringBuilder sb = new StringBuilder();
        boolean isZero = true;

        // Integer배열로 복사 && 모두 0인지 검사
        for(int i=0; i<numbers.length; i++)
        {
            numbers2[i] = numbers[i];
            if(numbers[i] != 0) isZero = false;
        }

        // 모든 원소가 0이면 종료
        if(isZero) return "0";

        // 정렬
        Arrays.sort(numbers2, (i1, i2) ->{
            return compare(i1, i2);
        });

        // 테스트
        // for(int temp: numbers2)
        //     System.out.println(temp);

        // 출력
        for(int i=0 ; i<numbers2.length ; i++)
            sb.append(numbers2[i]);
        return sb.toString();
    }

    public int compare(int num1, int num2)
    {
        int len1 = getLen(num1); int len2 = getLen(num2);
        int result1 = num1*(int)Math.pow(10, len2) + num2;
        int result2 = num2*(int)Math.pow(10, len1) + num1;

        if(result1 > result2) return -1;
        else if(result1 < result2) return 1;
        else return 0;
    }

    public int getLen(int number)
    {
        if(number == 0) return 1;
        else return (int)Math.log10(number) + 1;
    }
}
