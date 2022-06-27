package test.GroupErase;

import java.util.*;

class Solution
{
    public int solution(String s)
    {
        int answer = -1;
        StringBuilder builder = new StringBuilder(s);
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        while(!stack.empty())
        {
            int prev = stack.peek();
            if(builder.charAt(prev) == builder.charAt(prev+1))
            {
                builder.delete(prev, prev+2);
                stack.pop();
                System.out.println(prev);

                System.out.println(builder);
            }
            else if(prev != builder.length()-1)
                stack.push(prev+1);
        }
        System.out.println();

        System.out.println(builder);

        if(builder.length() == 0)
            return 1;
        else
            return 0;
    }

    public static void main(String[] args)
    {
        Solution test = new Solution();
        System.out.println(test.solution("baabaa"));
    }
}