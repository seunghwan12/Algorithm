package test.Network;

public class Solution {
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        int count = 0;

        for (int computer = 0; computer < n; computer++) {
            if(!visited[computer]) {
                visited[computer] = true;
                dfs(computer, visited, computers);
                count++;
            }
        }
        return count;
    }

    private void dfs(int computer, boolean[] visited, int[][] computers) {
        for (int idx = 0; idx < visited.length; idx++) {
            if (!visited[idx] && computers[computer][idx] == 1) {
                visited[idx] = true;
                dfs(idx, visited, computers);
            }
        }
    }
}
