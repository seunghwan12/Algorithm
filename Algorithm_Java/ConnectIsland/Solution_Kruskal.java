package test.ConnectIsland;

import java.util.Arrays;
import java.util.Comparator;

public class Solution_Kruskal {
    int[] arr;

    public int solution(int n, int[][] costs) {
        int answer = 0;
        int count = 0;
        double[][] dou;
        Arrays.sort(costs, Comparator.comparingInt(arr -> arr[2]));
        makeEachDisjoinSet(n);
        for (int idx = 0; idx < costs.length && count < n - 1; idx++) {
            if (find(costs[idx][0]) != find(costs[idx][1])) {
                count++;
                answer += costs[idx][2];
                union(costs[idx][0], costs[idx][1]);
            }
        }

        return answer;
    }

    private void union(int index1, int index2) {
        index1 = find(index1);
        index2 = find(index2);

        if (index1 < index2) {
            arr[index2] = index1;
        } else {
            arr[index1] = index2;
        }
    }

    private int find(int index) {
        while(arr[index] != index)
            index = arr[index];

        return index;
    }

    private void makeEachDisjoinSet(int n) {
        arr = new int[n];
        for (int idx = 0; idx < n; idx++)
            arr[idx] = idx;
    }
}
