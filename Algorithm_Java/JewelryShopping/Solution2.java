package test.JewelryShopping;

import java.util.*;

public class Solution2 {
    public int[] solution(String[] gems) {
        Map<String, Integer> map = new HashMap<>();
        int[] answer = new int[2];
        int min = Integer.MAX_VALUE;
        int size;
        int start = 0; int end = 0;

        // 보석의 종류 계산 및 각 보석의 최대
        Arrays.fill(answer, -1);
        for (int idx = 0; idx < gems.length; idx++) map.put(gems[idx], idx);
        size = map.size();

        // 0부터 끝까지, 정방향과 역방향으로 구간의 시작점과 종착점을 움직이면서 최소구간을 찾는다.
        // end가 끝에 있거나 최소구간이 고정된 경우에 탈출한다.
        while (end < gems.length - 1 && (answer[0] != start || answer[1] != end)) {
            Map<String, Integer> tempMap = new HashMap<>();
            end = start;

            // 시작점 start부터 모든 보석의 종류를 1개 이상은 포함하는 구간의 종착점 end를 찾는다.
            for (; end < gems.length; end++) {
                tempMap.put(gems[end], tempMap.getOrDefault(gems[end], 0) + 1);
                if (tempMap.size() == size || end == gems.length - 1) break;
            }

            tempMap.clear();
            int origStart = start;
            start = end;

            // 종착점 end부터 역으로 모든 보석의 종류를 1개 이상은 포함하는 구간의 시작점 start를 찾는다.
            for (; origStart <= start; start--) {
                tempMap.put(gems[start], tempMap.getOrDefault(gems[start], 0) + 1);
                if (tempMap.size() == size) break;
            }

            // 만약 현재 구간이 지금까지의 구간 중 가장 짧다면 업데이트
            if (end - start + 1 < min) {
                answer[0] = start;
                answer[1] = end;
                min = end - start + 1;
            }

            start++;
        }
        answer[0]++; answer[1]++;
        return answer;
    }

    public static void main(String[] args) {
        String[] gems = {"AA", "AB", "AC", "AA", "AC"};

        Solution2 test = new Solution2();
        int[] answer = test.solution(gems);
        System.out.println("answer[0] = " + answer[0] + " answer[1] = " + answer[1]);
    }
}
