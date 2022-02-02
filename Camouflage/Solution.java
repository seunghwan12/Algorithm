package test.Camouflage;

import java.util.*;

class Solution {
    HashMap<String, Integer> map = new HashMap<>();
    String[] type = null;
    long answer = 1;

    public long solution(String[][] clothes) {

        // 종류에 따른 옷의 개수를 매핑
        for(String[] cloth: clothes)
            map.put(cloth[1], map.getOrDefault(cloth[1], 0)+1);

        // 옷을 안 입는 경우부터 모든 종류의 옷을 입는 경우까지 계산한다.
        for(Map.Entry<String, Integer> entry: map.entrySet())
            answer *= entry.getValue()+1;

        // 옷을 안 입는 경우를 제외한다.
        return answer-1;
    }
}
