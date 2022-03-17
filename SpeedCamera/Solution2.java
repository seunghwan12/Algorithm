package test.SpeedCamera;

import java.util.Arrays;
import java.util.Comparator;

public class Solution2 {
    public int solution(int[][] routes) {
        Arrays.sort(routes, Comparator.comparingInt(arr -> arr[0]));
        int count = 0;
        int minOutPoint = Integer.MAX_VALUE;

        for (int[] route : routes) {
            if(route[0] > minOutPoint) {
                minOutPoint = Integer.MAX_VALUE;
            } else {
                if(minOutPoint == Integer.MAX_VALUE) count++;
                minOutPoint = Math.min(minOutPoint, route[1]);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Solution2 test = new Solution2();
        int[][] routes = {{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}};
        System.out.println("number of routes = " + test.solution(routes));
    }
}
