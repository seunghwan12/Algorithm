package test.SortStringMyself;

import java.util.*;

/*
문자열 배열을 입력받아 원하는 위치의 character를 비교하여 문자열의 순서를 결정하는
문제이다. Array.sort() 메소드에 람다식을 인자로 넣어 구현했다.
한 가지 배운 점은 람다식에서는 외부에서 정의된 변수는 사용할 수 있다는 것이다.
여기에는 한 가지 조건이 붙는데 지역 변수의 경우에는 final이거나 effectively final(재할당이 일어나지 않은 변수)여야 한다는 것이다.
그리고 지역 변수는 람다식 내부에서도 final로 취급되어 변경할 수 없다.
 */

class Solution {
    public String[] solution(String[] strings, int n) {

        Arrays.sort(strings, (String o1, String o2) ->
        {
            char c1 = o1.charAt(n);
            char c2 = o2.charAt(n);
            if(c1 < c2)
                return -1;
            else if(c1 == c2)
                return o1.compareTo(o2);
            else
                return 1;
        });

        return strings;
    }
}
