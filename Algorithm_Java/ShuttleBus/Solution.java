package test.ShuttleBus;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        LocalTime[] operatingTime = new LocalTime[n];
        LocalTime[] table = Arrays.stream(timetable).map(LocalTime::parse).toArray(LocalTime[]::new);
        final LocalTime nineAM = LocalTime.parse("09:00");
        operatingTime = IntStream.range(0, n).mapToObj((int idx) -> nineAM.plusMinutes(idx * t)).toArray(LocalTime[]::new);
        Arrays.sort(table);

        int cnt = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m && cnt < table.length; j++, cnt++) {
                if(table[cnt].compareTo(operatingTime[i]) > 0) break;
            }
        }

        LocalTime[] last = Arrays.stream(table, cnt, table.length).toArray(LocalTime[]::new);
        if(last.length < m) return operatingTime[n - 1].toString();
        else {
            LocalTime minusOne = last[m - 1].minusMinutes(1);
            return (operatingTime[n - 1].compareTo(minusOne) <= 0) ? operatingTime[n - 1].toString() : minusOne.toString();
        }
    }

    public static void main(String[] args) {
        int n = 2; int t = 10; int m = 2;
        String[] timetable = {"09:10", "09:09", "08:00"};
        Solution test = new Solution();
        String solution = test.solution(n, t, m, timetable);
        System.out.println("solution = " + solution);
    }
}
