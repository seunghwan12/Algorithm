package test.repersentationByN;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    final int maxSize = 8;
    List<ArrayList<Integer>> representation= new ArrayList<>();

    public int solution(int N, int number) {

        for (int idx = 0; idx <= maxSize; idx++)
            representation.add(new ArrayList<>());

        // N을 한 번 사용
        representation.get(1).add(N);
        if (isLeastNumberOfUse(1, number)) {
            return 1;
        }

        // N을 두 번 사용
        representation.get(2).add(10*N+N);
        doFourArithmeticOperations(N, N, 2);
        if (isLeastNumberOfUse(2, number)) {
            return 2;
        }

        // N을 3번부터 8번 사용하는 경우까지 찾기
        for (int numberOfUse = 3; numberOfUse <= maxSize; numberOfUse++) {
            for (int idx = 1; idx < numberOfUse; idx++) {
                // idx번 사용하는 경우와 (numberOfUse - idx)번 사용하는 경우를 조합
                makeCases(idx, numberOfUse - idx);
                representation.get(numberOfUse).add(getNumberConsistingOfN(numberOfUse, N));
            }
            if (isLeastNumberOfUse(numberOfUse, number)) {
                return numberOfUse;
            }
        }

        return -1;
    }

    private int getNumberConsistingOfN(int numberOfUse, int N) {
        int result = 0;
        for (int idx = 0; idx < numberOfUse; idx++) {
            int pow = (int)Math.pow(10, idx);
            result += N * pow;
        }

        return result;
    }

    private void doFourArithmeticOperations(int a, int b, int listNumber) {

        ArrayList<Integer> list = representation.get(listNumber);
//        System.out.println("a = " + a+ " b = " + b);
//        System.out.println("a+b = " + (a + b));
//        System.out.println("a-b = " + (a - b));
//        System.out.println("a-b = " + (a * b));


        list.add(a + b); list.add(a * b);
        list.add(a - b); list.add(b - a);
        if(b != 0) {
            list.add(a / b);
//            System.out.println("a / b = " + (a / b));
        }
        if(a != 0) {
            list.add(b / a);
//            System.out.println("b / a = " + (b / a));
        }
    }

    private void makeCases(int firstNumber, int secondNumber)
    {
        ArrayList<Integer> firsts = representation.get(firstNumber);
        ArrayList<Integer> seconds = representation.get(secondNumber);

        for (Integer first : firsts) {
            for (Integer second : seconds) {
                doFourArithmeticOperations(first, second, firstNumber + secondNumber);
            }
        }
    }

    private boolean isLeastNumberOfUse(int numberOfUse, int number) {
        ArrayList<Integer> list = representation.get(numberOfUse);

        for (Integer elem : list) {
            if (elem == number) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int N = 5; int number = 12;
        Solution test = new Solution();
        int leastNumberOfUse = test.solution(N, number);
        System.out.println("leastNumberOfUse = " + leastNumberOfUse);
    }
}
