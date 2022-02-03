package test.HIndex;

import java.util.Arrays;

class Solution {
    public int solution(int[] citations) {
        int len = citations.length;
        int answer = 0;
        int max = 0;

        // 정렬
        Arrays.sort(citations);

        for(int i= citations.length-1; i>=0 ; i--)
        {
            int min = Math.min(citations[i], len-i);
            if(min > max) max = min;
        }

        return max;
    }
}
