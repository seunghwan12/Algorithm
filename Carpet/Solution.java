package test.Carpet;

import java.util.ArrayList;

class Solution {
    ArrayList<int[]> list = new ArrayList<>();

    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        divideYellow(yellow);

        for(int[] temp: list)
        {
            int borderNum = 2*temp[0]+2*temp[1]+4;
            // System.out.println(borderNum);
            if(borderNum == brown)
            {
                answer[0] = temp[1]+2;
                answer[1] = temp[0]+2;
            }
        }
        return answer;
    }

    public void divideYellow(int yellow)
    {
        for(int i=1; i<= (int)Math.sqrt(yellow); i++)
        {
            if(yellow % i == 0)
            {
                int[] factors = new int[2];
                factors[0] = i;
                factors[1] = yellow / i;
                // System.out.printf("0: %d, 1: %d\n", i, yellow/i);
                list.add(factors);
            }
        }
    }
}
