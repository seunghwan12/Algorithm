package test.PoorUser;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
    Set<String> tempSet = new HashSet<>();
    Set<String> setOfResult = new HashSet<>();
    boolean[] visited;

    public int solution(String[] user_id, String[] banned_id) {
        visited = new boolean[user_id.length];
        dfs(user_id, banned_id, 0);
        return setOfResult.size();
    }

    private void dfs(String[] user_id, String[] banned_id, int order) {
        if(banned_id.length == order) {
            setOfResult.add(tempSet.stream().sorted().collect(Collectors.joining()));
        } else {
            for (int idx = 0; idx < user_id.length; idx++) {
                if (equals(user_id[idx], banned_id[order]) && !visited[idx]) {
                    tempSet.add(user_id[idx]);
                    visited[idx] = true;
                    dfs(user_id, banned_id, order+1);
                    tempSet.remove(user_id[idx]);
                    visited[idx] = false;
                }
            }
        }
    }

    private boolean equals(String userId, String bannedUserId) {
        if (userId.length() != bannedUserId.length()) {
            return false;
        } else {
            for (int idx = 0; idx < userId.length(); idx++) {
                if(userId.charAt(idx) != bannedUserId.charAt(idx) && bannedUserId.charAt(idx) !='*')
                    return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Solution test = new Solution();
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"*rodo", "*rodo", "******"};

        int solution = test.solution(user_id, banned_id);
        System.out.println("solution = " + solution);
    }
}
