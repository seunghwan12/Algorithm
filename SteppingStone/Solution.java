package test.SteppingStone;

import java.util.Arrays;

public class Solution {
    public int solution(int distance, int[] rocks, int num) {
        Arrays.sort(rocks);

        return binarySearch(distance, rocks, num);
    }

    private int binarySearch(int distance, int[] rocks, int num) {
        int start = 0, end = 2*distance, mid = 0;
        int answer = 0;
        while (start <= end) {
            System.out.println("start = " + start + "end = " + end + "mid = " + mid);
            mid = (start+end) / 2;
            // 바위 간의 거리의 최소값이 mid보다 큰 경우에 해당값을 저장하는 변수
            int min = Integer.MAX_VALUE;
            int removeRockNum = 0;
            int prev = 0;

            for (int rock : rocks) {
                if (rock - prev < mid) {
                    removeRockNum++;
                } else {
                    min = Math.min(min, rock - prev);
                    prev = rock;
                }
            }

            // 바위 간의 거리의 최소값이 mid가 아니라 mid보다 작은 prev부터 도착지점까지의 거리가 된다.
            // 따라서 start ~ mid-1를 선택하여 더 작은 최솟값을 찾도록 한다.
            if (distance - prev < mid) {
                end = mid -1;
                continue;
            }
            // 바위 간의 거리의 최소값이 mid보다 크거나 같도록 하기 위해 num개 이하로 바위을 제거하면 된다.
            // 즉, num개의 바위를 제거하였을 때, 바위 간의 거리의 최소값이 mid보다 같거나 큰 경우이다.
            // mid+1 ~ end를 선택하여 더 큰 최솟값을 찾도록 한다.
            if (removeRockNum <= num) {
                answer = Math.max(min, mid);
                start = mid+1;
            }
            // 바위 간의 거리의 최소값이 mid보다 크거나 같도록 하기 위해 num개 초과로 바위를 제거해야 한다.
            // 즉, num개의 바위를 제거하였을 때, 바위 간의 거리의 최소값이 mid보다 작다.
            // start ~ mid-1를 선택하여 더 작은 최솟값을 찾도록 한다.
            else {
                end = mid - 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int dist = 25;
        int[] rocks = {2, 14, 11, 21, 17};
        int num = 2;

        Solution test = new Solution();
        test.solution(dist, rocks, num);
    }
}
