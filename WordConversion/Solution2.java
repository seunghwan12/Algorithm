package test.WordConversion;

import java.util.LinkedList;
import java.util.Queue;

public class Solution2 {
    Queue<String> queue = new LinkedList<>();
    int count = 0;

    public int solution(String begin, String target, String[] words) {
        if (!targetExists(target, words)) return 0;

        // 문자열을 bfs방식으로 탐색할 때 이미 확인한 노드(문자열)은 다시 방문하지 않도록
        // visited배열을 공유한다.
        boolean[] visited = new boolean[words.length];

        queue.add(begin);
        while (queue.size() != 0) {
            count++;
            int size = queue.size();

            // 큐에 존재하는 모든 노드(문자열)을 탐색하여 큐에 있는 노드들이 동일한 단계에 위치하도록 한다.
            for (int idx = 0; idx < size; idx++) {
                String cur = queue.remove();

                // words에 있는 문자열 중 현재 문자열과 알파벳이 한개가 다르고 방문하지 않은 문자열인 경우에
                // target이면 종료하고, target이 아니면 queue에 넣는다.
                for (int word = 0; word < words.length; word++) {
                    if (checkOneCharDifference(cur, words[word]) && !visited[word]) {
                        if(target.equals(words[word])) {
                            break;
                        }
                        visited[word] = true;
                        queue.add(words[word]);
                    }
                }
            }
        }

        return count;
    }

    // 주어진 words 배열에 target가 존재하는지 확인하는 메소드
    private boolean targetExists(String target, String[] words) {
        boolean exists = false;
        for (String word : words) {
            if (target.equals(word)) {
                exists = true;
            }
        }

        return exists;
    }

    // 주어진 두 문자열이 한 개의 알파벳만 다른지 확인하는 메소드
    private boolean checkOneCharDifference(String str1, String str2) {
        int count = 0;
        for (int idx = 0; idx < str1.length(); idx++) {
            if (str1.charAt(idx) != str2.charAt(idx)) {
                count++;
            }
        }

        return (count == 1);
    }
}
