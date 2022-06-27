package test.StockPrice;

import java.util.Stack;

class Solution {
    public int[] solution(int[] prices) {

        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[prices.length];
        int time = 1;

        while(true)
        {
            int currentPrice = prices[time-1];

            while(!stack.empty() && prices[stack.peek()-1] > currentPrice)
            {
                int past = stack.pop();
                answer[past-1] = time - past;
            }

            stack.push(time);

            if(time == prices.length)
                break;
            else
                time++;
        }

        while(!stack.empty())
        {
            int past = stack.pop();
            answer[past-1] = time - past;
        }
        return answer;
    }
}