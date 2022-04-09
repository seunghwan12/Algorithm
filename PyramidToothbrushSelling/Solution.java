package test.PyramidToothbrushSelling;

/*
재귀적으로 자신의 부모노드에 자신의 이익금을 분배하는 문제이다. 자신의 이익금 중 90%를 자신이 갖고
자신의 부모노드를 찾아 부모노드에게 이익금 10%를 주는 과정을 재귀적으로 반복하는 것이다.

enroll, referral 배열은 트리를 표현한다. enroll은 조직원의 번호와 이름을 매핑한다. referral은
조직원의 번호와 그 조직원의 추천인의 이름을 매핑한다. 따라서 referral에서 추천인 이름을 찾고
다시 enroll에서 추천인의 번호를 찾는 과정을 반복하면 루트노드에 도착할 수 있다.

enroll에서 조직원의 번호를 찾는 과정은 O(N)의 시간복잡도를 갖는다. 따라서 HashMap를 사용하여
시간복잡도를 O(1)으로 줄인다.

*/

import java.util.HashMap;
import java.util.Map;

public class Solution {
    Map<String, Integer> nameToNumberMapping = new HashMap<>();
    int[] answer;
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        answer = new int[enroll.length];
        for (int idx = 0; idx < enroll.length; idx++) {
            nameToNumberMapping.put(enroll[idx], idx);
        }

        for (int idx = 0; idx < seller.length; idx++) {
            distribute(enroll, referral, seller[idx], amount[idx]*100);
        }
        for (int mamberProfit : answer) {
            System.out.println("mamberProfit = " + mamberProfit);
        }
        return answer;
    }

    public void distribute(String enroll[], String[] referral, String member, int profit) {
        if(profit == 0 || member.equals("-")) return;

        else {
//            System.out.println("profit = " + profit);
//            System.out.println(member +" : " + profit * 9 / 10);
            int number = nameToNumberMapping.get(member);
            String recommender = referral[number];

            answer[number] += profit - profit / 10;
            distribute(enroll, referral, recommender, profit / 10);
        }
    }

    public static void main(String[] args) {
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};

        Solution test = new Solution();
        test.solution(enroll, referral, seller, amount);
    }
}
