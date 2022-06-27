/*
프로그래머스 level2, 오픈 채팅방

자바에서는 특수문자를 표현할 때, 역슬래쉬를 사용한다. 따라서 역슬래쉬 자체를 표현하기 위해서는
역슬래위 2개를 연속해서 사용해야 한다. 따라서 화이트스페이스를 의미하는 정규식 /s를 표현할 때에도
//s로 표현해야 하는 것이다.

오픈 채팅방을 출입하고, 닉네임을 변경한 명령의 로그가 주어진다. 그러면 닉네임을 변경하였을 때,
기존의 입출입 메시지에도 새로운 닉네임을 반영한 메시지 기록을 구하는 문제이다. 즉, 가장 최신의
닉네임을 모든 메시지기록에 반영하는 것이다. 입력되는 명령들은 'enter', 'leave', 'change'
세 가지로 나뉘고 enter과 change만이 닉네임을 변경할 수 있다. 또한 모든 유저는 고유한 아이디를
가지고 아이디에 닉네임이 매핑되어 마음대로 바꿀 수 있다.

따라서 자료구조로는 Map를 사용하여 아이디와 닉네임의 매핑을 저장하고 enter, change가 발생하면
변경하였다. 또한 출입기록을 구해야 하므로 출력에는 change를 제외하였다.
구체적으로 출입, 닉네임 변경로그를 받아 공백으로 파싱하여 첫단어가 enter. change인 경우메만
두번째, 세번째 단어(아이디, 닉네임)을 찾아 매핑하였다. 동시에 출입기록을 저장할 answer배열의
크기를 구하기 위해 enter, leave로 시작하는 명령의 개수를 계산하였다.

다음으로 저장된 매핑을 사용하여 변경된 닉네임을 이전 메시지기록에도 반영하여
enter, leave명령들을 메시지기록으로 변환하여 저장하였다.

 */

package test.OpenChattingRoom;

import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        HashMap<String, String> map = new HashMap<>();
        int num = 0;

        for(String instr: record)
        {
            String[] part = instr.split("\\s+");
            if(part[0].equals("Enter") || part[0].equals("Leave"))
                num++; // 메시지 횟수를 카운트
            if(part.length == 3)
                map.put(part[1], part[2]); // 아이디와 닉네밍을 매핑
        }

        String[] answer = new String[num];
        int i=0;

        for(String output: record)
        {
            if(output.startsWith("Change"))
                continue;
            String[] part = output.split("\\s+");
            String nickname = map.get(part[1]);
            switch (part[0]){
                case "Enter":
                    answer[i] = nickname+"님이 들어왔습니다.";
                    i++;
                    break;
                case "Leave":
                    answer[i] = nickname+"님이 나갔습니다.";
                    i++;
                    break;
            }
        }

        return answer;
    }
}
