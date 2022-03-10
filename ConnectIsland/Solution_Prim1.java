package test.ConnectIsland;

import java.util.Arrays;

/*
MST를 찾는 알고리즘 중에서 프림의 알고리즘을 사용한다. 루프가 3번 중복되어 O(N^3)의 시간복잡도를 갖는다.
그 이유는 선택된 섬과 선택되지 않는 섬를 연결하는 다리 중에서 최소 비용의 다리를 구할 때,
계산이 중복된다는 것이다. 왜냐하면 최소 비용의 다리를 구하면 선택된 섬의 집합과 선택되지 않은 섬의
집합이 변경된다. 따라서 최소 비용 다리를 찾아야할 범위도 변경되어 이미 값을 확인한 다리여도 최소값을
구하기 위해서는 다시 확인해야 한다.

 */

class Solution_Prim1 {
    int[][] bridges;// 다리 건설 비용
    boolean[] selected;// 현재까지 연결된 섬를 저장

    public int solution(int n, int[][] costs) {
        selected = new boolean[n];
        selected[0] = true;
        int answer = 0;

        // 다리 건설 비용을 2차원 배열로 저장
        convert2DArray(n, costs);

        // 선택된 섬의 집합과 선택되지 않은 섬의 집합 사이의 최소 비용 다리를 구한다.
        for (int idx = 0; idx < n - 1; idx++) {
            answer += findLeastCost();
        }

        return answer;
    }

    void convert2DArray(int n, int[][] costs)
    {
        bridges = new int[n][n];

        for (int[] bridge : bridges) {
            Arrays.fill(bridge, -1);
        }

        for (int[] cost : costs) {
            bridges[cost[0]][cost[1]] = cost[2];
            bridges[cost[1]][cost[0]] = cost[2];
        }
    }

    private int findLeastCost() {
        int min = Integer.MAX_VALUE;
        int startCity = -1, endCity = -1;
        int len = bridges.length;

        for (int foundCity = 0; foundCity < len; foundCity++) {
            if (!selected[foundCity]) continue;

            for (int notFoundCity = 0; notFoundCity < len; notFoundCity++) {
                if(selected[notFoundCity]) continue;

                int value = bridges[foundCity][notFoundCity];

                // 해당 다리가 연결되어 있고 현재까지의 최소값보다 작은 조건
                if(value != -1 && value < min){
                    startCity = foundCity;
                    endCity = notFoundCity;
                    min = bridges[startCity][endCity];
                }
            }
        }
        selected[endCity] = true;

        return min;
    }

    public static void main(String[] args) {
        Solution_Prim1 test = new Solution_Prim1();
        int n = 4;
        int[][] costs = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
        System.out.println("costs = " + test.solution(n, costs));
    }
}