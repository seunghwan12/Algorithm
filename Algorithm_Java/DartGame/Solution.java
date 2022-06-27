package test.DartGame;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
다트게임으로 얻은 "점수|보너스|[옵션]"으로 이루어진 문자열 3세트를 입력받아 보너스와 옵션으로 점수를 변환하고
변환된 점수들의 합을 반환하는 문제이다.

문제를 풀기 위해서 문자열에서 점수, 보너스 그리고 옵션을 꺼내야 했다. 따라서 정규식과 Matcher, Pattern클래스를
사용하여 각 요소들을 꺼내었다. 그리고 꺼낸 요소에 주어진 규칛을 적용해 점수를 계산하였다.
하지만 다른 사람의 답안을 확인해보니 문자열의 각 요소를 검사하여 점수, 보너스, 옵션을 확인하고
점수를 계산하여 Stack에 넣음으로써 문제를 해결하였다. 이 과정에서 1번의 for문만을 사용하였고 특별한 함수를 호출하지도 않는다.
반면에 내가 사용한 방법은 3번의 for문을 돌리고 라이브러리 함수들을 for문 내부에서 호출한 것이다.
라이브러리 함수의 소스 코드를 확인한 것은 아니지만, 내 풀이보다 더 시간복잡도가 클 것이다. 그리고 for문 사용횟수도 차이가 난다.

*/

class Solution {
    public int modifiedSolution(String dartResult)
    {
        Stack<Integer> stack = new Stack<>();
        int score=0;
        int sum = 0;

        for(int i=0; i<dartResult.length(); i++)
        {
            char elem = dartResult.charAt(i);
            if(Character.isDigit(elem))
            {
                if(dartResult.charAt(i+1) == '0')
                {
                    score = 10;
                    i++;
                }
                else
                    score = elem - '0';
            }
            else if(elem == 'S' || elem == 'D' || elem == 'T')
            {
                switch (elem)
                {
                    case 'D':
                        score *= score;
                        break;
                    case 'T':
                        score*= score * score;
                        break;
                }
                stack.push(score);
            }
            else
            {
                if(!stack.empty())
                    stack.pop();
                switch (elem)
                {
                    case '*':
                        score *= 2;
                        if(!stack.empty())
                        {
                            int prev = stack.pop();
                            prev *= 2;
                            stack.push(prev);
                        }
                        stack.push(score);
                        break;
                    case '#':
                        stack.push(score*(-1));
                        break;
                }
            }
        }
        while(!stack.empty())
            sum += stack.pop();

        return sum;
    }

    public int solution(String dartResult) {
        String scoreRegex = "\\d+";
        String bonusRegex = "[SDT]";
        String optionRegex = "[SDT][*#]?";

        Matcher scoreMatcher = Pattern.compile(scoreRegex).matcher(dartResult);
        Matcher bonusMatcher = Pattern.compile(bonusRegex).matcher(dartResult);
        Matcher optionMatcher = Pattern.compile(optionRegex).matcher(dartResult);

        int[] score = new int[3];
        char[] bonus = new char[3];
        char[] option = new char[3];
        int[] totalScore = new int[3];

        for (int i=0;scoreMatcher.find();i++)
            score[i] = Integer.parseInt(scoreMatcher.group());

        for (int i=0;bonusMatcher.find();i++)
            bonus[i] = bonusMatcher.group().charAt(0);

        for (int i=0; optionMatcher.find();i++) {
            String str = optionMatcher.group();
            option[i] = str.length() == 2 ? str.charAt(1) : '0';
        }

        for(int i=0; i< 3; i++)
        {
            switch (bonus[i])
            {
                case 'S':
                    totalScore[i] = score[i];
                    break;

                case 'D':
                    totalScore[i] = score[i] * score[i];
                    break;

                case 'T':
                    totalScore[i] = score[i] * score[i] * score[i];
                    break;
            }

            switch(option[i])
            {
                case '*':

                    if(i==0)
                        totalScore[i] *= 2;
                    else
                    {
                        totalScore[i-1] *= 2;
                        totalScore[i] *= 2;
                    }
                    break;

                case '#':
                    totalScore[i] *= -1;
                    break;
            }
        }

        int answer = 0;
        for(int ts: totalScore)
            answer += ts;

        return answer;
    }


}