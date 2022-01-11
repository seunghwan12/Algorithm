/*
프로그래머스 level2, 문자열 압축

문자열에서 같은 값이 연속해서 나타나는 것을 반복되는 값과 개수로 표현하는 알고리즘을 구현하여
압축된 문자열 중 가장 짧은 문자열의 길이를 구하는 문제이다. 문자열에서 값은 값을 찾을 때는 제일 앞에서부터
정해진 길이만큼을 자르고 각 길이의 값들이 일치하는지를 확인한다.

(1)처음 풀 때는 for문을 사용하여 각 단위에서의 압축된 문자열을 구하였다. StartsWith메소드를 사용하여 이전위치의 문자열이 현재 위치에서 반복되는지 확인하고
true면 num++하고 false면 현재 위치에서 단위만큼 떨어진 곳 혹은 마지막 위치를 현재 위치로 정하고 반복한다.

(2) 다른 사람의 풀이 중 가장 좋아요를 많이 받은 풀이를 보니 재귀함수를 사용하여 나도 적용해봤다. 하지만 단위와 전체길이가 딱 떨어지지 않는 경우에서
꼬여 불필요한 코드를 집어 넣었고 결국 복잡한 코드를 작성하였다.( 사실 이 부분은 이전 위치에서 현재위치까지 문자열이 현재위치에서 반복되지 않을 때,
새로운 현재 위치를 현재 위치에서 단위만큼 떨어진 곳과 마지막 위치 중 작은 위치로 정하면 된다. 하지만 if문으로 이 경우를 따로 해결하려고 하니 코드가
복잡해졌다.)

(3) 코드의 실행시간을 줄일 방법을 고민해보니 압축된 문자열을 나타내는 StringBuilder 인스턴스에 계속 문자열을 추가하는 것보다
압축된 문자열의 길이를 나타내는 변수 sum에 반복되는 문자열과 반복되는 횟수의 길이를 더하는 것이 더 효율적이라고 생각하였다. 따라서 이를
구현하니 시간이 줄어들었다.

(4) 다시 코드의 실행시간을 줄일 방법을 고민해보니 재귀함수보다는 반복문이 더 효윯적이라고 생각하여 반복문으로 다시 구현하였다. 이때는 그동안 작성했던 코드보다
논리흐름이 더 명확한 코드를 찾아 적용하였다. 이때 생각해보니 (1)의 방식이 (2),(3)의 방식보다 효율적이었는데, 재귀함수를 사용하려고 하다보니 오히려 정답에서
멀어졌음을 깨달았다.

 */

package test.StringCompression;

class Solution {
    int sum =0;
    String repeatString, tempString, mainString;
    int strLen;

    public int solution(String s) {

        int minLen = 1000;
        strLen = s.length();
        mainString = s;

        for(int unit =1; unit <= Math.max(1, strLen/2) ; unit++)
        {
            // 초기화
            sum =0; repeatString = mainString.substring(0, unit);

            // 압축된 문자열의 길이 구하기
            getLengthOfCompressedString(unit, unit, 1);

            // 최소 문자열의 길이 구하기
            if(sum < minLen) minLen = sum;
        }
        return minLen;
    }

    public void getLengthOfCompressedString(int cur, int unit, int repeatNum)
    {
        while(cur < strLen)
        {
            if(mainString.startsWith(repeatString, cur))
            {
                repeatNum++;
                cur += unit;
            }
            else
            {
                // 반복되는 값의 길이와 개수를 길이로 환산
                sum += repeatString.length();
                if(repeatNum > 1) sum += ((int)Math.log10(repeatNum) + 1);

                // 초기화
                int next = Math.min(cur+unit, strLen);
                repeatString = mainString.substring(cur, next);
                repeatNum = 1;
                cur = next;
            }
        }
        sum += repeatString.length();
        if(repeatNum > 1) sum += ((int)Math.log10(repeatNum) + 1);

    }
}