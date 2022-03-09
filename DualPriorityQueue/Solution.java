package test.DualPriorityQueue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

    public int[] solution(String[] operations) {
        for (String op : operations) {
            char ch = op.charAt(0);
            Integer num = Integer.valueOf(op.substring(2));

            if (ch == 'I') {
                minHeap.add(num);
                maxHeap.add(num);
                System.out.println("insert num: " + num);
            } else if (minHeap.isEmpty() && maxHeap.isEmpty()) {
                System.out.println("heap is empty");
            } else if (ch == 'D' && num.equals(1)) {
                // 최댓값 삭제

                Integer target;
                minHeap.remove((target = maxHeap.remove()));
                System.out.println("target = " + target);
            } else if (ch == 'D' && num.equals(-1)) {
                // 최솟값 삭제
                Integer target;
                maxHeap.remove((target = minHeap.remove()));
                System.out.println("target = " + target);
            }
        }
        if(minHeap.isEmpty() && maxHeap.isEmpty())
            return new int[]{0, 0};
        else
            return new int[]{maxHeap.remove(), minHeap.remove()};
    }

    public static void main(String[] args) {
        Solution test = new Solution();
        String[] operations = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
        int[] arr = test.solution(operations);
        for (int num : arr) {
            System.out.println("num = " + num);
        }
    }
}
