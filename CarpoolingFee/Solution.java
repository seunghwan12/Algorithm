package test.CarpoolingFee;

import java.util.Arrays;

public class Solution {
    public long solution(int n, int s, int a, int b, int[][] fares) {
        long min = Long.MAX_VALUE;

        // 인접행렬 생성
        long[][] costs = new long[n+1][n+1];
        for (int idx = 1; idx <= n; idx++) {
            Arrays.fill(costs[idx], 4000_000_000L);
            costs[idx][idx] = 0;
        }
        for (int[] fare : fares) {
            costs[fare[0]][fare[1]] = fare[2];
            costs[fare[1]][fare[0]] = fare[2];
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.printf(" %d ", costs[i][j]);
            }
            System.out.println();
        }

        // s에서의 다른 지점까지의 최소거리를 찾는 dist배열 생성
        long[] dist = dijkstra(n, s, costs);

        for (long len : dist) {
            System.out.printf(" %d ", len);
        }
        System.out.println();

        for (int mid = 1; mid <= n; mid++) {
//            if (mid == s || mid == a || mid == b) {
//                continue;
//            } else {
                long[] distFromMid = dijkstra(n, mid, costs);
                min = Math.min(min, dist[mid] + distFromMid[a] + distFromMid[b]);
                System.out.println("mid = " + mid + " cur = " + (dist[mid] + distFromMid[a] + distFromMid[b]));
//            }
        }
        return min;
    }

    private long[] dijkstra(int n, int start, long[][] costs) {
        long[] dist = new long[n+1];
        boolean[] visited = new boolean[n+1];
        int cnt = 0;

        // 초기화
        for (int idx = 1; idx <= n; idx++) {
            dist[idx] = costs[start][idx];
        }
        visited[start] = true; cnt = 1;

        // 최소거리 구하기
        while (cnt < n) {
            int minNode = 0;
            long len = Long.MAX_VALUE;

            for (int idx = 1; idx <= n; idx++) {
                if (!visited[idx] && dist[idx] < len) {
                    minNode = idx;
                    len = dist[idx];
                }
            }
            if (minNode == 0) {
                throw new IllegalStateException("최소거리의 노드가 없습니다");
            }
            visited[minNode] = true;

            for (int idx = 1; idx <= n; idx++) {
                if (!visited[idx]) {
                    dist[idx] = Math.min(dist[idx], dist[minNode] + costs[minNode][idx]);
                }
            }
            cnt++;
        }

        return dist;
    }

    public static void main(String[] args) {
        int n = 7;
        int s = 3;
        int a = 4;
        int b = 1;

        int[][] fares = {{5, 7, 9},
                {4, 6, 4},
                {3, 6, 1},
                {3, 2, 3},
                {2, 1, 6}};
        Solution test = new Solution();
        long answer = test.solution(n, s, a, b, fares);
        System.out.println("answer = " + answer);
    }
}
