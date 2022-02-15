package test.RadixGame;

class Solution {
    public String solution(int n, int t, int m, int p){
        StringBuilder sb = new StringBuilder();
        StringBuilder numbers = new StringBuilder();
        int len = p+m*(t-1);

        for(int i=0; sb.length() <= len; i++)
            sb.append(Integer.toString(i, n));

        for(int cnt=0; cnt < t; cnt++)
            numbers.append(sb.charAt(p-1+cnt*m));

        // System.out.printf("total: %s\n", sb);
        // System.out.printf("tube: %s\n", numbers);

        return numbers.toString().toUpperCase();
    }
}
