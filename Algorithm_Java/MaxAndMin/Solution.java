/*
프로그래머스 level2, 최댓값과 최솟값

공백으로 숫자가 구분된 문자열에서 최솟값과 최댓값을 구하는 문제이다.

문자열에서 숫자들이 공백으로 구분되어 있으므로 String 클래스의 split메소드를 사용한다.
숫자를 비교해야하므로 문자열로 표현된 숫자들을 int형을 바꾸어야 한다. 따라서 Integer클래스의
parseInt메소드를 사용한다.

처음 문제를 풀 때는 split로 나누어진 문자열들을 int 배열로 변환하고 sorting하여 최대, 최소값을
구하였다. 하지만 이 방법은 최대값과 최소값만을 구하는 것이 아니라 다른 원소들의 순서도 구하므로
O(nlogn)의 시간복잡도를 가진다. 이 방법 대신에 Sequential Search(순차탐색)의 방식으로 사용하면
최대값과 최소값만 구할 수 있고, O(n)의 시간복잡도를 가지므로 더 효울적이다.

 */

package test.MaxAndMin;

public class Solution {
    public String solution(String s) {

        String[] numbers = s.split(" ");
        int n;
        int max = Integer.parseInt(numbers[0]);
        int min = max;

        for(int i=1; i< numbers.length; i++)
        {
            n = Integer.parseInt(numbers[i]);
            if(n > max) max = n;
            if(n < min) min = n;
        }

        return min+" "+min;
    }
}