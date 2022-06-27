// 포로그래머스, level1, 월간 코드 챌린지, 없는 숫자 더라기
// url: https://programmers.co.kr/learn/courses/30/lessons/86051?language=java

/*
내 풀이는 입력받은 numbers에 있는 숫자들을 Found 배열에 true로 저장하는 것이다.
그리고 다시 Found에서 false인 인덱스를 찾아 answer에 더하는 것이다.

하지만 모범풀이는 1부터 9까지의 합인 45에서 numbers에 있는 숫자들을 빼는 것이다.
이 경우 numbers에 없는 숫자들의 합을 구할 수 있다.
 */
package NumberAddition;

class Solution {
    public int mySolution(int[] numbers) {
        int len = numbers.length;
        boolean Found[] = new boolean[len];
        int answer = 0;

        // numbers 배열에서 있는 숫자는 true로 저장
        for (int number : numbers)
            Found[number] = true;

        // Found 배열에서 false인 index만 answer에 더한다.
        for (int i = 0; i < 10; i++) {
            if (Found[i] == false)
                answer += i;
        }
        return answer;
    }
}
