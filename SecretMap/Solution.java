/*
프로그래머스 level1, 비밀지도

입력받은 배열을 비트연산하고 이진수로 변환하고 마지막으로 주어진 규칙에 따라 변환하는 문제이다.
비트연산, 이진수 변환, 0을 공백으로 1을 #으로 변환하는 부분으로 나뉜다.

위 세가지 과정을 구현하였다. 하지만 라이브러리함수를 활용하지 못한 점은 아쉽다.
아래의 otheeSolution메소드를 보면 먼저 toBinaryString메소드를 사용하여
간결하게 이진수변환을 해결한다. 반면에 나는 추가로 메소드를 만들어서 이를 해결하였다.
그리고 0과 1을 변환하는 부분에서는 otherSolution의 방법이 간결하기는 하지만,
String을 계속 생성하는 방식이라 시간이 더 오래 걸릴 것이다. 반면에 내 풀이는
StringBuilder 클래스를 활용하여 String를 만들지 않고 변형하는 방식이므로
시간이 덜 걸릴 것이다. 하지만 코드가 간결하지는 않다.
 */


package test.SecretMap;

class Solution {

    public String[] otherSolution(int n, int[] arr1, int[] arr2) {
        String[] result = new String[n];
        for (int i = 0; i < n; i++) {
            result[i] = Integer.toBinaryString(arr1[i] | arr2[i]);
        }

        for (int i = 0; i < n; i++) {
            result[i] = String.format("%" + n + "s", result[i]);
            result[i] = result[i].replaceAll("1", "#");
            result[i] = result[i].replaceAll("0", " ");
        }

        return result;
    }

    public String[] solution(int n, int[] arr1, int[] arr2) {

        String[] answer = new String[n];

        for(int i=0; i< n; i++)
        {
            int result = arr1[i] | arr2[i];
            StringBuilder temp = decimalToBinary(result, n);
            answer[i] = decryption(temp, n);
        }
        return answer;
    }

    private StringBuilder decimalToBinary(int num, int n)
    {
        StringBuilder result = new StringBuilder();

        for(int i=0; i< n; i++)
        {
            if(num != 0){
                result.insert(0, num % 2);
                num /= 2;
            }
            else
                result.insert(0, 0);
        }
        return result;
    }

    private String decryption(StringBuilder builder, int n)
    {
        for(int i=0; i< n; i++)
        {
            if(builder.charAt(i) == '1')
                builder.setCharAt(i, '#');
            else
                builder.setCharAt(i, ' ');
        }
        return builder.toString();
    }

}