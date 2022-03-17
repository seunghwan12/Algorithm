package test.SpeedCamera;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, Comparator.comparingInt(arr -> arr[0]));
        List<int[]> list = new ArrayList<>(Arrays.asList(routes));
        int count = 0;

        while (list.size() > 0) {
            int idx = 0;
            int minOutPoint = list.get(idx)[1];

            for (idx = 1; idx < list.size(); idx++) {
                if (list.get(idx)[0] > minOutPoint) break;
                else minOutPoint = Math.min(list.get(idx)[1], minOutPoint);
            }
            list.subList(0, idx).clear();
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        Solution test = new Solution();
        int[][] routes = {{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}};
        System.out.println("number of routes = " + test.solution(routes));
    }
}
