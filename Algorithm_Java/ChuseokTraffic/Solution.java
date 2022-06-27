package test.ChuseokTraffic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.nnnnnnnnn");
    private final String padding ="000000";
    private final long scale = 1000_000L;
    private Queue<Log> queue = new PriorityQueue<>();

    public int solution(String[] lines) {
        Log[] logs = parseLines(lines);
        Arrays.sort(logs, Comparator.comparing((Log o) -> o.startTime));
        LocalDateTime[] checkTimes = findCheckingTimes(logs);
        int order = 0;
        int max = 0;

        for (LocalDateTime checkTime : checkTimes) {
            // 현재 시간에서 입력될 로그들 입력
            for (; order < logs.length && logs[order].startTime.compareTo(checkTime) <= 0; order++) {
                queue.add(logs[order]);
            }
            // 1초전부터 현재시간의 로그들만 남도록 한다.
            while (queue.peek() != null
                    && queue.peek().endTime.compareTo(checkTime.minusSeconds(1L)) <= 0) {
                queue.remove();
            }
            max = Math.max(queue.size(), max);
        }
        return max;
    }

    private LocalDateTime[] findCheckingTimes(Log[] logs) {
        LocalDateTime[] checkTimes = new LocalDateTime[logs.length * 2];
        for (int idx = 0; idx < logs.length; idx++) {
            checkTimes[2 * idx] = logs[idx].startTime;
            checkTimes[2 * idx + 1] = logs[idx].endTime;
        }

        Arrays.sort(checkTimes);

        return checkTimes;
    }

    private Log[] parseLines(String[] lines) {
        Log[] logs = new Log[lines.length];
        LocalDateTime endTime;
        LocalDateTime startTime;

        long processingSec;
        long processingNano = -scale;

        for (int idx = 0; idx < lines.length; idx++) {
            endTime = LocalDateTime.parse(lines[idx].substring(0, 23) + padding, formatter);
            processingSec = Long.parseLong(lines[idx].substring(24, 25));
            if (lines[idx].charAt(25) == '.') {
                processingNano = Long.parseLong(lines[idx].substring(26, lines[idx].length() - 1)) * scale - scale;
            }
            startTime = endTime.minusSeconds(processingSec).minusNanos(processingNano);
            logs[idx] = new Log(startTime, endTime);
        }
        return logs;
    }

    class Log implements Comparable<Log> {
        LocalDateTime startTime;
        LocalDateTime endTime;

        @Override
        public int compareTo(Log o) {
            return endTime.compareTo(o.endTime);
        }

        public Log(LocalDateTime startTime, LocalDateTime endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    public static void main(String[] args) {
        String[] lines = {"2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s", "2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s", "2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s", "2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s"};
        Solution test = new Solution();
        test.solution(lines);
    }
}
