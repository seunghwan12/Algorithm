package test.IntegerTriangle;

import java.util.Arrays;

class Solution {

    public int solution(int[][] triangle) {
        for (int step = 1; step < triangle.length; step++) {
            for(int idx = 0; idx < triangle[step].length; idx++) {
                if(idx == 0)
                    triangle[step][idx] += triangle[step-1][idx];
                else if (idx == triangle[step].length-1)
                    triangle[step][idx] += triangle[step-1][idx-1];
                else {
                    triangle[step][idx] += Math.max(triangle[step-1][idx-1], triangle[step-1][idx]);
                }
            }
        }

         for (int step = 0; step < triangle.length; step++) {
             for(int idx = 0; idx < triangle[step].length; idx++) {
                 System.out.printf("%d ", triangle[step][idx]);
             }
             System.out.println();
         }

        int answer = Arrays.stream(triangle[triangle.length-1]).max().getAsInt();
        return answer;
    }

    public static void main(String[] args) {
        Solution test = new Solution();
        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        test.solution(triangle);
    }
}
