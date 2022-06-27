package test.TwoByNTiling;


public class Solution {
    public int solution(int num) {
        int denominator = 1_000_000_007;

        int[] arr = new int[num+1];
        arr[1] = 1; arr[2] = 2;
        for (int idx = 3; idx < num; idx++) {
            arr[idx] = (arr[idx - 1] + arr[idx - 2]) % denominator;
        }

        return arr[num];
    }

    public static void main(String[] args) {
        int num = 4;
        Solution test = new Solution();
        test.solution(num);
    }
}
