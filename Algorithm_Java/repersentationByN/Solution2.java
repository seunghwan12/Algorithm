package test.repersentationByN;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution2 {

    final int maxSize = 8;
    List<HashSet<Integer>> setList= new ArrayList<>();

    public int solution(int N, int number) {

        int duplicate_N = 0;
        for (int idx = 0; idx <= maxSize; idx++) {
            duplicate_N = duplicate_N*10 + N;
            setList.add(new HashSet<>());
            setList.get(idx).add(duplicate_N);
        }

        // N을 한 번 사용
        if (number == N) return 1;

        // N을 2번부터 8번 사용하는 경우까지 찾기
        for (int numberOfUse = 2; numberOfUse <= maxSize; numberOfUse++) {

            for (int idx = 1; idx < numberOfUse; idx++) {
                // idx번 사용하는 경우와 (numberOfUse - idx)번 사용하는 경우를 조합
                makeCases(idx, numberOfUse - idx);
            }

            if (setList.get(numberOfUse).contains(N)) {
                return numberOfUse;
            }
        }

        return -1;
    }

    private void doFourArithmeticOperations(int a, int b, int listNumber) {
        Set<Integer> set = setList.get(listNumber);

        set.add(a + b); set.add(a * b);
        set.add(a - b); set.add(b - a);
        if(b != 0) set.add(a / b);
        if(a != 0) set.add(b / a);
    }

    private void makeCases(int firstNumber, int secondNumber)
    {
        HashSet<Integer> firstes = setList.get(firstNumber);
        HashSet<Integer> seconds = setList.get(secondNumber);

        for (Integer first : firstes) {
            for (Integer second : seconds) {
                doFourArithmeticOperations(first, second, firstNumber + secondNumber);
            }
        }
    }

    public static void main(String[] args) {
        int N = 5; int number = 12;
        Solution2 test = new Solution2();
        int leastNumberOfUse = test.solution(N, number);
        System.out.println("leastNumberOfUse = " + leastNumberOfUse);
    }
}
