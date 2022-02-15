package test.RightParenthesis;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        int cnt = 0;

        for(int i=0; i<s.length(); i++)
        {
            if(s.charAt(i)=='(')
                cnt++;
            else
                cnt--;
            if(cnt < 0)
            {
                answer = false;
                break;
            }
        }

        if(cnt != 0)
            answer = false;

//        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args)
    {
        Solution test = new Solution();
        test.solution("()()");
    }
}
