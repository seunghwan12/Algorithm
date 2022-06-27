/*
프로그래머스 level2, 괄호 회전하기
url: https://programmers.co.kr/learn/courses/30/lessons/76502

알고리즘
    1. 회전된 문자열을 만든다
    2. 올바른 괄호문자열인지 검사한다.

 */

package test.ParenthesisRotation;

import java.util.ArrayList;
import java.util.Stack;

public class Solution {
    private ArrayList<String> list = new ArrayList<>();
    public int solution(String s) {
        int answer = 0;

        makeRotatedString(s);

        for(String str: list)
        {
            // System.out.println(str);
            if(isRightParenthesis(str))
                answer++;
        }

        return answer;
    }

    public void makeRotatedString(String str)
    {
        StringBuilder sb = new StringBuilder(str);

        for (int i = 0; i < str.length(); i++) {
            sb.append(sb.charAt(0));
            sb.deleteCharAt(0);
            list.add(sb.toString());
        }
//         int len = str.length();

//         list.add(str);

//         for(int i=1; i<len; i++)
//         {
//             list.add(str.substring(i, len)+str.substring(0,i));
//         }
    }

    public boolean isRightParenthesis(String str)
    {
        Stack<Character> stack = new Stack<>();

        for(int i=0; i<str.length(); i++)
        {
            char temp = str.charAt(i);
            if(temp =='(' || temp == '{'
                    || temp == '[')
                stack.push(temp);
            else if(stack.empty())
                return false;
            else
            {
                char openedParenthesis = stack.pop();

                if(temp - openedParenthesis != 1
                        && temp - openedParenthesis != 2)
                    return false;
            }
        }

        if(!stack.empty()) return false;

        return true;
    }
}
