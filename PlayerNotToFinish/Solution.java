/*
프로그래머스, level1, 해시, 완주하지 못한 선수
url: https://programmers.co.kr/learn/courses/30/lessons/42576

참가자 중 완주하지 못한 한 명을 찾는 문제이다.
처음에는 HashSet를 사용하여 완주자들의 Set을 만들고 참가자들과 비교하여
Set에 저장된 완주자들을 제거했다. 여기서 Set에 없는 참가자가 바로 완주하지 못한
사람이라도 생각했다.

하지만 이 방법은 틀렸다. 그 이유는 참가자 중 동명이인이 있기 때문이다. 다시 말해 Set은
중복을 허용하지 않기 때문에 동명이인이라는 예외를 처리할 수 없는 것이다. 따라서 모범답안을
확인해보니 HashMap를 사용하여 key에는 이름을, value에는 동명이인의 수를 저장하였다.
따라서 완주자들을 저장한 map에서 참가자의 이름에 해당하는 value를 얻을 때, 0이나 null이 되는
경우가 완주하지 못한 사람이 되는 것이다.

 */

package test.PlayerNotToFinish;

import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> map = new HashMap<>();
        String answer="";
        for(String comp: completion)
            map.put(comp, map.getOrDefault(comp, 0) + 1);

        for(String part: participant)
        {
            Integer count = map.get(part);
            if( count == null || count == 0 )
            {
                answer = part;
                break;
            }
            else
                map.put(part, count-1);
        }

        return answer;
    }
}
