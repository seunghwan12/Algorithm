/*
프로그래머스 level2, 배달
url: https://programmers.co.kr/learn/courses/30/lessons/12978

문제풀이
    1. roads배열에 저장된 도로의 정보를 행을 출발도시, 열을 도착도시로 하고
       원소를 이동시간으로 저장하는 cost배열에 저장한다.

    2. 다이제스트라 알고리즘을 사용하여 1번 도시로부터 다른 도시듫까지의 최단거리를
       구한다

       - 최단거리를 찾지 못한 도시들 중에서 가장 가까운 도시를 찾는다.
         그리고 이 도시까지의 최단거리와 도로 정보를 바탕으로 최단거리를 찾지 못한
         도시까지의 거리를 업데이트한다. 이 과정을 모든 도시의 최단거리를 찾을 때까지
         반복하는 것이 다이제스트라 알고리즘이다.
 */

package test.Delivery;

import java.util.Arrays;

class Solution {
    private final int MAX_LENGTH = 500000;
    private int number = 0;
    private int[][] cost = null;
    private boolean[] visited = null;
    private int[] distance = null;

    public int solution(int N, int[][] roads, int K) {

        // 초기화
        int answer = 0;
        number = N;
        visited = new boolean[N+1];
        distance = new int[N+1];

        // 도로의 비용을 cost배열에 저장
        makeCostArray(roads);

        // 다이제스트라 알고리즘
        dijkstra();

        // for(int i=0; i<N+1; i++)
        // {
        //     for(int j=0; j<N+1; j++)
        //     {
        //         System.out.printf("%d ", cost[i][j]);
        //     }
        //     System.out.println();
        // }
        // answer = (int)Arrays.stream(distance).filter((int elem) ->{return (elem <= K) ? true : false;}).count() - 1;

        for(int i=1; i<= number; i++)
        {
            if(distance[i] <= K)
                answer++;
        }

        return answer;
    }

    public void makeCostArray(int[][] roads)
    {
        cost = new int[number+1][number+1];

        for(int i=0; i<number+1; i++)
            Arrays.fill(cost[i], MAX_LENGTH);
        for(int[] road: roads)
        {
            if(cost[road[0]][road[1]] > road[2])
            {
                cost[road[0]][road[1]] = road[2];
                cost[road[1]][road[0]] = road[2];
            }
        }
    }

    public void dijkstra()
    {

        // 초기화
        for(int i=1; i<= number; i++)
            distance[i] = cost[1][i];
        distance[1] = 0; visited[1] = true;

        // 현재 상태에서 가장 가까운 도시를 찾는 과정을
        // N-1번 반복한다.
        for(int cnt=2; cnt<= number; cnt++)
        {
            // for(int i=0; i<= number; i++)
            // {
            //     System.out.printf("distance[%d]: %d\n", i, distance[i]);
            // }

            int minCost = Integer.MAX_VALUE;
            int minCostCity = 0;

            // 체크되지 않은 도시 중 가장 가까운 도시를 선택
            for(int i=1;i<= number; i++)
            {
                if(!visited[i] &&
                        minCost > distance[i])
                {
                    minCost = distance[i];
                    minCostCity = i;
                }
            }

            // 가장 가까운 도시를 체크한다.
            visited[minCostCity] = true;

            // 가장 가까운 도시까지의 경로를 바탕으로
            // distance배열을 업데이트한다.
            for(int i=1 ;i<= number; i++)
            {
                if(!visited[i])
                {
                    if(distance[minCostCity]+cost[minCostCity][i]<distance[i])
                        distance[i] = distance[minCostCity]+cost[minCostCity][i];
                }
            }
        }
    }
}