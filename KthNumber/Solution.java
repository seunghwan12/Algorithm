package test.KthNumber;

import java.util.Arrays;

class Solution {
    public static void main(String args[])
    {
        Solution test = new Solution();
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        test.solution(array,commands);
    }
    public int[] solution(int[] array, int[][] commands) {
        int[] temp;
        int len = commands.length;
        int[] answer = new int[len];

        for(int i=0;i<len;i++)
        {
            temp = Arrays.copyOfRange(array, commands[i][0]-1, commands[i][1]);
            Arrays.sort(temp);
            answer[i] = temp[commands[i][2]-1];
        }

        return answer;
    }
}