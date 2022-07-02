package test;
/*
프로그래머스 level2, 멀리뛰기
 */
public class TheLongJump {
    public long solution(int n) {
        long answer = fib(n);
        return answer;
    }

    // 피보나치 수열
    private long fib(int num) {
        if(num <= 2) return num;

        long[] fib = new long[num+1];
        fib[1] = 1; fib[2] = 1;
        for (int idx = 3; idx <= num; idx++) {
            fib[idx] = (fib[idx - 1] + fib[idx - 2]) % 1234567L;
        }

        return fib[num];
    }

    public static void main(String[] args) {
        TheLongJump theLongJump = new TheLongJump();
        long solution = theLongJump.solution(1);
        System.out.println("solution = " + solution);
    }
}
