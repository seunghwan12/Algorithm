/*
프로그래머스 level2, 뉴스 클러스터링

기사의 제목을 분류하기 위해 두 제목 사이의 유사도를 구하는 알고리즘을 구현하는 문제이다.
문자열을 두 글자씩 끊어서 집합으로 저장하고 집합 간의 합집합, 교집합을 구하고
합집합의 원소 개수를 분모로, 교집합의 원소 개수를 분자로 하여 유사도를 구한다.
특히, 두 글자로 끊어진 문자열에서 소문자, 대문자는 구별하지 않고, 영어 알파벳 이외의
문자가 포함되면 문자열을 버린다.

기사 제목에서 두 글자로 끊어진 문자열들이 중복될 수 있으므로 문자열과 문자열의 개수를 저장하기 위해
자료구조로 Map을 사용한다. 문제는 크게 두 부분으로 나눌 수 있다. 기사 제목을 두 글자 문자열로 나누고
Map에 문자열과 문자열의 개수를 mapping하여 저장하는 부분, 문자열1의 Map과 문자열2의 Map으로
합집합과 교집합을 구하는 부분으로 나눌 수 있다.

첫번째 부분을 구현하기 위해 모든 문자열을 2글자로 나누고 2글자의 문자열에 알파벳 이외의 문자가
포함되었는지를 검사한다. 그리고 포함되지 않았으면 Map에 key값으로 넣는다. 이때 이미 Map에 동일한
문자열이 입력되어 있으면 value+1하고, 입력되어 있지 않으면 1을 value로 한다.

합집합을 만들기 위해 미리 문자열1의 Map을 합집합의 Map에 복사한다. 그리고 문자열2의 Map의
모든 entry를 반복하면서 합집합의 Map에 동일한 key가 있으면 두 Map의 value 중 큰 값을 value로
하고, 없으면 문자열2의 value로 하여 key,value쌍을 합집합의 Map에 저장한다.

교집합을 만들기 위해서는 문자열2의 Map의 모든 entry를 반복하면서 문자열1에도 동일한 key가 있는지
확인하고, true라면 문자열1과 문자열2의 Map의 value 중 작은 값을 value로 하여 key, value쌍을
교집합에 저장한다.

다른 사람의 풀이를 보니 Stream을 활용하였고, 중복되는 부분을 모듈화하였다. 마음이 급해서
간결하지 못한 코드를 짠 것 같다.

 */

package test.NewsClustering;

import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        HashMap<String, Integer> first = new HashMap<>();
        HashMap<String, Integer> second = new HashMap<>();
        HashMap<String, Integer> union = new HashMap<>();
        HashMap<String, Integer> intersection= new HashMap<>();
        double denominator = 0.0; double numerator = 0.0;

        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        for(int i=0; i<str1.length()-1;i++)
        {
            String key = str1.substring(i, i+2);
            inputString(key, first);
        }
        for(int i=0; i<str2.length()-1;i++)
        {
            String key = str2.substring(i, i+2);
            inputString(key, second);
        }

        union.putAll(first);
        for(Map.Entry<String, Integer> temp: second.entrySet())
        {
            String key = temp.getKey();
            Integer value = temp.getValue();

            if(union.containsKey(key))
            {
                if(union.get(key) < value)
                    union.replace(key, value);
            }
            else
                union.put(key, value);
        }

        for(Map.Entry<String, Integer> temp: second.entrySet())
        {
            String key = temp.getKey();
            if(first.containsKey(key))
            {
                intersection.put(key, Math.min(first.get(key),
                        temp.getValue()));
            }
        }

        for(Map.Entry<String, Integer> temp: union.entrySet())
            denominator += temp.getValue();

        for(Map.Entry<String, Integer> temp: intersection.entrySet())
            numerator += temp.getValue();

        return (numerator ==0 && denominator == 0)
                ? 65536
                :(int)((numerator/denominator) * 65536.0);
    }

    public void inputString(String s,HashMap<String, Integer> map)
    {
        boolean isAlphabet = true;
        for(int i=0; i<s.length(); i++)
        {
            if(!Character.isAlphabetic(s.charAt(i)))
                isAlphabet = false;
        }
        if(isAlphabet)
            map.put(s, map.getOrDefault(s, 0)+1);
    }
}
