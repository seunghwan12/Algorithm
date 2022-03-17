package test.SpeedCamera;

import java.util.Arrays;
import java.util.Comparator;

public class Solution3 {
    public int solution(int[][] routes) {
        int count = 0;
        int minOutPoint = Integer.MIN_VALUE;

        // 진출시점을 기준으로 정렬
        Arrays.sort(routes, Comparator.comparingInt(arr -> arr[1]));

        for (int[] route : routes) {
            if (minOutPoint < route[0]) {
                count++;
                minOutPoint = route[1];
            }
        }

        return count++;
    }
}
