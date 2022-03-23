package test.Immigration;

import java.util.Arrays;

public class Solution {
    public long solution(int person, int[] times) {
        Arrays.sort(times);
        long start = 1;
        long end = (long) times[times.length - 1] * person;
        long mid = 0; long count = 0; long answer = end;

        while (start <= end) {
            mid = (start + end)/2;
            count = 0;

            for (int time : times) {
                count += mid / time;
            }

            /*
                end+1은 항상 person <= count를 만족한다. 그리고 이분 탐색이 진행되면서
                start와 end 사이의 간격이 줄어들수록 end+1 값은 작아진다.
                마침내 while문을 탈출하는 순간에 start는 person <= count를
                만족하는 가장 작은 수가 된다.
            */
            if (count < person) {
                start = mid + 1;
            } else { // person <= count
                answer = Math.min(answer, mid);
                end = mid -1;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution test = new Solution();
        int n = 6;
        int[] times = {7,10};
        test.solution(n, times);
    }
}
