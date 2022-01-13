/*
프로그래머스, level2, 기능개발

기능의 개발 진도와 속도가 다르게 주어질 때, 같은 날에 배포하는 기능의 수를 반환하는 문제이다.
모든 기능들은 순서가 있어서 뒤에 있는 기능개발이 이미 완료되어도 앞의 기능개발이 완료되지 않으면
뒤에 있는 기능은 배포되지 않는다. 따라서 앞에 있는 기능이 개발완료되면 두 개의 기능이 한번에 배포되는
것이다.

같은 날에 배포되는 기능의 수를 저장하는 Queue와 해당일과 이전일 완료된 기능의 순서를 나타내는
cur, prev 변수를 사용한다. 모든 기능이 배포될 때까지 계속 개발을 진행하고, 현재까지 완료된
개발작업으로 cur을 움직인다. 만약 해당일에 배포되는 기능이 있다면, 즉 cur의 이동이 있으면
cur-prev를 queue에 넣는다.

모든 작업이 완료된 후에 queue에 저장된 같은 날 배포된 기능의 수들을 배열로 변환하여
반환한다.
 */

package test.FunctionDevelop;

import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds)
    {
        Queue<Integer> queue = new LinkedList<>();
        int cur = -1; int prev = -1;
        while(cur != progresses.length-1)
        {
            for(int i=prev+1; i< progresses.length; i++)
                progresses[i] += speeds[i];

            for(int i=prev+1; i< progresses.length; i++)
            {
                if(progresses[i] >= 100) cur = i;
                else break;
            }
            if(cur - prev != 0)
                queue.add(cur - prev);

            prev = cur;
        }

        int len = queue.size();
        int[] answer= new int[len];
        for(int i=0; i< len; i++)
            answer[i] = queue.remove();
        return answer;
    }
}
