package test.DiskController;

import java.util.*;

public class Solution {
    PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
    final int maxTime = 500000;

    public int solution(int[][] jobs) {
        int numberOfJob = 0;
        int totalTimeOfRequestToEnd = 0;
        int currentSecond = 0;

        Arrays.sort(jobs, Comparator.comparingInt(o -> o[0]));

        for(int step=0; step < jobs.length ||  priorityQueue.size() != 0;) {
            System.out.println("currentSecond = " + currentSecond);
            System.out.println("step = " + step);

            for(int idx = step ; idx < jobs.length; idx++) {
                if(jobs[idx][0] <= currentSecond) {
                    priorityQueue.add(jobs[idx]);
                    step = idx+1;
                }
                else
                    break;
            }

            if(priorityQueue.size() == 0) {
                currentSecond++;
            } else {
                int[] currentJob = priorityQueue.remove();
                int waitingTime = currentSecond - currentJob[0];
                totalTimeOfRequestToEnd += waitingTime + currentJob[1];
                currentSecond += currentJob[1];
                numberOfJob++;
                System.out.println("takingTime = " + currentJob[1]);
                System.out.println("totalTimeOfRequestToEnd = " + totalTimeOfRequestToEnd);
                System.out.println();
            }
        }

        int answer = totalTimeOfRequestToEnd / numberOfJob;
        return answer;
    }

    public static void main(String[] args) {
        Solution test = new Solution();
        int[][] jobs = {{24, 10}, {28, 39}, {43, 20}, {37, 5}, {47, 22}, {20, 47}, {15, 2}, {15, 34}, {35, 43}, {26, 1}};
        int perTime = test.solution(jobs);
        System.out.println("perTime = " + perTime);
    }
}

