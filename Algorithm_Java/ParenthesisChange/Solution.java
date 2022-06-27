/*
프로그래머스 level2, 괄호 변환

여는 괄호와 닫는 괄호로만 이루어졌고 둘의 개수가 동일한 문자열이 주어질 때,
주어진 규칙에 따라 여는 괄호와 닫는 괄호의 짝을 맞춘 문자열을 반환하는 문제이다.

두 가지 괄호의 개수가 동일하면 균형잡인 괄호이고, 여기에 괄호의 짝이 맞으면
올바른 괄호 문자열이라고 한다. 균형잡힌 문자열이 주어질 때, 다음 규칙에 따라 변환한다.
1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다.
3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
  3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.
  4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
  4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
  4-3. ')'를 다시 붙입니다.
  4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
  4-5. 생성된 문자열을 반환합니다.

 위 규칙을 보면, 2의 균형잡힌 문자열 두 개로 분리하는 메소드, 3,4의 문자열 변환을 재귀적으로
 구현하는 메소드, 4의 문자열의 첫번째와 마지막문자를 제거하고 나머지 문자들의 방향을 뒤집는
 메소드, 마지막으로 올바른 괄호 문자열인지 확인하는 메소드가 필요하다.

 균형 잡힌 문자열 2개로 분리하는 메소드를 구현하는데 있어서 stack를 사용하였고, 문자열을
 순차탐색하면서 해당 인덱스의 문자가 여는 괄호이면 스택에 저장하고, 닫는 괄호이면 스택에서
 여는 괄호를 pop하는 방식으로 분리할 수 없는 균형잡힌 문자열을 구하였다.

 하지만 이 부분은 스택이 아니라 여는 괄호의 개수를 저장하는 int형 변수로 대체할 수 있었다.
 스택에 저장된 값은 항상 여는 괄호이기 때문에 어느 값이 저장되는지는 중요하지 않다.중요한 것은
 여는 괄호의 개수였다.

 */

package test.ParenthesisChange;

import java.util.Stack;

class Solution {

    // '('를 저장하는 용도
    Stack<Character> stack = new Stack<>();

    public String solution(String p) {
        if(p.length() == 0)
            return "";

        String answer = makeRightString(p);
        return answer;
    }

    public String makeRightString(String s)
    {
        String[] division = divide(s);
        if(isRightString(division[0]))
        {
            if(division[1].equals(""))
                return division[0];
            else
                return division[0]+makeRightString(division[1]);
        }
        else
        {
            String result = "("+makeRightString(division[1])+")"
                    + deleteAndReverse(division[0]);
            return result;
        }
    }

    public String[] divide(String s)
    {
        String[] division = new String[2];
        int stop = 0;
        int[] num = new int[2];

        for(int i=0; i<s.length();i++)
        {
            if(s.charAt(i) == '(')
                num[0]++;
            else
                num[1]++;

            if(num[0] == num[1])
            {
                stop = i+1;
                break;
            }
        }
        division[0] = s.substring(0,stop);
        division[1] = s.substring(stop);

        return division;
    }

    public boolean isRightString(String s)
    {
        for(int i=0; i<s.length(); i++)
        {
            if(s.charAt(i) == '(')
                stack.push('(');
            else
            {
                if(stack.empty())
                    return false;
                else
                    stack.pop();
            }
        }

        return true;
    }

    public String deleteAndReverse(String s)
    {
        char[] reducedString = new char[s.length()-2];
        for(int i=1; i<s.length()-1; i++)
        {
            if(s.charAt(i)=='(')
                reducedString[i-1] = ')';
            else
                reducedString[i-1] = '(';
        }
        return new String(reducedString);
    }
}