package test.TheFarthestNode;

import java.util.*;

public class Solution {
    private List<List<Integer>> adjacencyList = new ArrayList<>();
    Queue<Integer> queue = new LinkedList<>();

    public int solution(int n, int[][] edges) {
        int count = 0;
        int size = 0;
        boolean[] discovered = new boolean[n+1];

        makeAdjacencyList(n, edges);
        queue.add(1);
        discovered[1] = true;

        while (queue.size() > 0) {
            size = queue.size();
            count++;

            for (int idx = 0; idx < size; idx++) {
                int node = queue.remove();

                for (int adjacentNode : adjacencyList.get(node)) {
                    if (!discovered[adjacentNode]) {
//                        System.out.println(node + " to "+ adjacentNode);
                        queue.add(adjacentNode);
                        discovered[adjacentNode] = true;
                    }
                }
            }
//            System.out.println("count = " + count);
//            System.out.println();
        }

        return size;
    }

    private void makeAdjacencyList(int n, int[][] edges) {
        for (int idx = 0; idx <= n; idx++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }
    }

    public static void main(String[] args) {
        Solution test = new Solution();
        int num = 6;
        int[][] edges = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        System.out.println("answer = " + test.solution(num, edges));
    }
}
