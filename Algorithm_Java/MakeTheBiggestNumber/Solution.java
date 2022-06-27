package test.MakeTheBiggestNumber;

class Solution {
    public static void main(String[] args)
    {
        Solution test = new Solution();
        test.solution("1924", 2);
    }
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int len = number.length() - k;

        for (int j = 0, index = -1; j < len; j++) {
            char max = '0' - 1;

            for (int i = index+1; i <= k+j; i++) {
                if (number.charAt(i) > max) {
                    index = i;
                    max = number.charAt(i);
                }
            }

            sb.append(max);
        }
        System.out.println(sb);
        return sb.toString();
    }
}
