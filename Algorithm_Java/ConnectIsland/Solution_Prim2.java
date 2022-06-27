package test.ConnectIsland;

/*
Solution_Prim1의 문제를 해결하기 위해 minDist배열을 최솟값을 저장하기 위한 자료구조로 사용한다.
minDist는 현재까지 선택되지 않은 섬과 현재까지 선택된 섬의 다리 중 최소 비용의 다리를 저장하는 배열이다.
모든 선택되지 않은 섬은 minDist를 갖고, 새롭게 선택된 섬의 다리 중에서 선택되지 않은 섬과 연결된 다리들을
찾아 선택되지 않은 섬의 minDist와 비교하여 최솟값을 minDist에 저장하는 것이다. 선택된 섬들은 더 이상
minDist값을 업데이트하지 않는다.
 */

import java.util.Arrays;

public class Solution_Prim2 {
    int[][] bridges;// 다리 건설 비용
    boolean[] selected;// 현재까지 연결된 섬를 저장
    int[] minDist;

    public int solution(int n, int[][] costs) {
        minDist = new int[n];
        selected = new boolean[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[0] = 0;
        int answer = 0;

        // 다리 건설 비용을 2차원 배열로 저장
        convertTo2DArray(n, costs);

        for (int idx = 0; idx < n ; idx++) {
            // 각 섬의 dist 중에서 최소 dist를 가진 섬을 구한다.
            int newSelectedIsland = find_UnSelected_Island_OfMinBridge();

            // 최소 dist를 가진 섬을 선택된 섬의 집합에 넣고 dist배열을 업데이트한다.
            selected[newSelectedIsland] = true;
            answer += minDist[newSelectedIsland];

            // 새롭게 선택된 섬과 다리로 연결된 선택되지 않은 섬들의 minDist를 업데이트한다.
            updateDist(newSelectedIsland);
        }

        return answer;
    }

    private int find_UnSelected_Island_OfMinBridge() {
        int min = Integer.MAX_VALUE;
        int minIsland = -1;

        for (int idx = 0; idx < minDist.length; idx++) {
            if (!selected[idx] && minDist[idx] < min) {
                minIsland = idx;
                min = minDist[idx];
            }
        }

        return minIsland;
    }

    private void updateDist(int newSelected) {
        for (int unSelected = 0; unSelected < minDist.length; unSelected++) {

            // 선택되지 않은 섬 중에서 새롭게 선택된 섬과 연결된 다리를 찾아 기존의 최솟값과 비교한다.
            if(!selected[unSelected] && bridges[newSelected][unSelected] != -1)
                minDist[unSelected]
                        = Math.min(minDist[unSelected], bridges[newSelected][unSelected]);
        }
    }

    private void convertTo2DArray(int n, int[][] costs)
    {
        bridges = new int[n][n];

        for (int[] bridge : bridges)
            Arrays.fill(bridge, -1);

        for (int[] cost : costs) {
            bridges[cost[0]][cost[1]] = cost[2];
            bridges[cost[1]][cost[0]] = cost[2];
        }
    }

    public static void main(String[] args) {
        Solution_Prim2 test = new Solution_Prim2();
        int n = 4;
        int[][] costs = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
        System.out.println("costs = " + test.solution(n, costs));
    }
}
