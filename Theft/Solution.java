package test.Theft;

class Solution {
    public int solution(int[] money) {

        int len = money.length;
        int[] dp_1 = new int[money.length];
        int[] dp_2 = new int[money.length];

        // 0번째 집을 털지 않는 경우
        dp_1[0] = 0; dp_1[1] = money[1];
        for(int idx = 2; idx < len; idx++) {
            dp_1[idx] = Math.max(dp_1[idx-2] +money[idx], dp_1[idx-1]);
        }

        // 마지막집을 털지 않는 경우
        dp_2[0] = money[0]; dp_2[1] = Math.max(money[0], money[1]);
        for(int idx = 2; idx < len; idx++) {
            if(idx == len -1)
                dp_2[idx] = dp_2[idx-1];
            else
                dp_2[idx] = Math.max(dp_2[idx-2] + money[idx], dp_2[idx-1]);
        }
        return Math.max(dp_1[len-1], dp_2[len-1]);
    }
}
