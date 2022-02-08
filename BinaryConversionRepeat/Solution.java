package test.BinaryConversionRepeat;

class Solution {
    public int[] solution(String str) {
        int totalNumber = 0;
        int cnt = 0;
        int[] answer = new int[2];

        while(!str.equals("1"))
        {
            // 반복횟수 증가
            answer[0]++;

            // 문자열의 길이 - 1의 개수 = 0의 개수
            answer[1] += str.length();
            str = str.replaceAll("0+", "");
            answer[1] -= str.length();

            // 문자열의 길이를 이진문자열로 변환하여 저장
            str = Integer.toBinaryString(str.length());

        }

        return answer;
    }
}
