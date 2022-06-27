package test.TruckCrossingBridge;

import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {

        Queue<Integer> bridge = new LinkedList<>();
        int currentWeight = 0;
        int time = 0;

        for(int cnt=0; cnt<truck_weights.length; )
        {
            time++;
            if(bridge.size() == bridge_length)
            {
                int truck = bridge.remove();
                if(truck != -1)
                    currentWeight -= truck_weights[truck];
                // System.out.printf("removed: %d ", truck);
            }

            int idx = (currentWeight+truck_weights[cnt] <= weight) ? cnt : -1;

            if(idx != -1)
            {
                currentWeight += truck_weights[idx];
                cnt++;
            }
            bridge.add(idx);
            // System.out.printf("time: %d, idx: %d, curwieght: %d\n", time, idx, currentWeight);
        }

        return time+bridge_length;
    }
}
