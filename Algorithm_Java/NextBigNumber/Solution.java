package test.NextBigNumber;

class Solution {
    public int solution(int n) {
        int temp = n+1;
        int oneNum = Integer.bitCount(n);

        while(oneNum != Integer.bitCount(temp)) temp++;

        return temp;
    }
}
