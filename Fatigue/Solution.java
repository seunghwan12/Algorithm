package test.Fatigue;

class Solution {
    int[][] dungeonsInfo = null;
    int max = 0;
    public int solution(int k, int[][] dungeons) {
        dungeonsInfo = dungeons;
        boolean[] visited = new boolean[dungeons.length];
        int answer = -1;

        dfs(visited, k, 0);
        System.out.println(max);

        return max;
    }

    public void dfs(boolean[] visited, int k, int cnt)
    {

        // System.out.printf("k : %d\n", k);
        for(int i=0; i< visited.length; i++)
        {
            if(!visited[i] && k >= dungeonsInfo[i][0])
            {
                //             System.out.printf("cnt: %d, visited: %d k: %d ", cnt, i, k);
                // System.out.println();

                visited[i] = true;
                dfs(visited,
                        k-dungeonsInfo[i][1], cnt+1);
                visited[i] = false;
            }
        }

        if(cnt > max)
            max = cnt;
    }

    public static void main(String[] args)
    {
        Solution test = new Solution();
        int[][] dungeons = {{80, 20}, {50, 40}, {30, 10}};
        test.solution(80, dungeons);
    }
}
