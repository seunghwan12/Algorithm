/*
프로그래머스 level2, 문자열 압축

문자열에서 같은 값이 연속해서 나타나는 것을 반복되는 값과 개수로 표현하는 알고리즘을 구현하여
압축된 문자열 중 가장 짧은 문자열의 길이를 구하는 문제이다. 문자열에서 값은 값을 찾을 때는 제일 앞에서부터
정해진 길이만큼을 자르고 각 길이의 값들이 일치하는지를 확인한다.

 */

package test.StringCompression;

class Solution {
    StringBuilder compressedString = new StringBuilder();
    String repeatString, mainString;
    int strLen;

    public int solution(String s) {

        int minLen = 1000; int tempLen = 0;
        strLen = s.length(); mainString = s;

        for(int unit =1; unit <= Math.max(1, strLen/2) ; unit++)
        {
            // 초기화
            compressedString.delete(0, compressedString.capacity());
            repeatString = mainString.substring(0, unit);

            // 문자열 압축 및 길이 구하기
            getCompressedString(0, unit, 0);
            tempLen = compressedString.length();

            // System.out.println(compressedString);
            if(tempLen < minLen) minLen = tempLen;
        }
        return minLen;
    }

    public void getCompressedString(int cur, int unit, int repeatNum)
    {
        if(cur+unit >= strLen)
        {
            if(mainString.startsWith(repeatString, cur)) repeatNum++;

            compressedString.append(repeatString);
            if(repeatNum >= 2) compressedString.append(repeatNum);

            if(!mainString.startsWith(repeatString, cur))
                compressedString.append(mainString.substring(cur, strLen));
        }
        else
        {
            if(mainString.startsWith(repeatString, cur))
                getCompressedString(cur+unit, unit, ++repeatNum);
            else
            {
                // 압축된 문자열에 중복되는 문자열과 개수 추가
                compressedString.append(repeatString);
                if(repeatNum >= 2) compressedString.append(repeatNum);

                // 초기화
                repeatString = mainString.substring(cur, cur + unit);
                repeatNum = 1;

                getCompressedString(cur+unit, unit, repeatNum);
            }
        }
    }
}