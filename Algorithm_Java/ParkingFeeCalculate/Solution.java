/*
프로그래머스 level2, 주차요금 계산

요금표와 밉/출차기록이 주어질 때, 주차요금을 계산하는 문제이다.

알고리즘
    1. 입출차기록을 바탕으로 차량번호와 주차시간을 매핑한 Map을 만든다.
    2. Map의 Value들로 배열을 만든다.
    3. 주차시간 배열과 요금표를 사용하여 주차요금을 계산한다.

자료구조: SortedMap, HashMap
    반환할 주차요금 배열이 차량번호 순으로 정렬되어야 하므로 Key값에 따라 정렬되는 SortedMap를
    사용한다. 하지만 TreeMap은 lookup의 시간복잡도가 O(logN)이므로 입출차기록을 모두 TreeMap에
    저장하지 않는다. HashMap는 lookup의 시간복잡도가 O(1)이므로 입차기록을 여기에 저장하고
    주차시간은 TreeMap에 저장하여 시간복잡도를 낮춘다.

 */

package test.ParkingFeeCalculate;

import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map;

class Solution {

    public static void main(String[] args)
    {
        Solution test = new Solution();
        int[] fees = {180, 5000, 10, 600};
        String[] records = {"05:34 5961 IN"
                , "06:00 0000 IN"
                , "06:34 0000 OUT"
                , "07:59 5961 OUT"
                , "07:59 0148 IN"
                , "18:59 0000 IN"
                , "19:09 0148 OUT"
                , "22:59 5961 IN"
                , "23:00 5961 OUT"};
        test.solution(fees, records);
    }
    SortedMap<String, Integer> timeMap = new TreeMap<>();
    private final String in = "IN";
    private final String out = "OUT";
    private final int lastHour = 23;
    private final int lastMin = 59;

    public int[] solution(int[] fees, String[] records)  {
        Integer[] parkingTime = null;
        int[] answer = null;

        // 차량번호와 주차시간을 매핑
        makeTimeMap(records);
//        printTimeMap();

        // 차량번호 순서에 맞춘 주차시간배열 생성
        parkingTime = timeMap.values().toArray(new Integer[0]);
        answer = new int[parkingTime.length];
        for (int i = 0; i < parkingTime.length; i++) {
            answer[i] = parkingTime[i];
        }

        // 요금정책과 주차시간에 따라 주차요금 계산
        calculateParkingFee(fees, answer);

        //print
//        for(int fee: answer)
//            System.out.println(fee);
        return answer;
    }

    public void printTimeMap()
    {
        for(Map.Entry<String,Integer> entry: timeMap.entrySet())
            System.out.printf("carNum: %s, parkingTime:%d\n", entry.getKey(), entry.getValue());
    }

    public void makeTimeMap(String[] records)
    {
        HashMap<String, String> inMap = new HashMap<>();

        for(String record: records)
        {
            String[] temp = record.split(" ");

            // 입차
            if(temp[2].equals(in))
                inMap.put(temp[1], temp[0]);

            // 출차
            else
            {
                // 입차시간 계산
                String inTime = inMap.get(temp[1]);

                // 주차시간 계산(출차시간-입차시간)
                int time = calculateParkingTime(inTime, temp[0]);

                // timeMap에 넣고 inMap에서 제거
                timeMap.put(temp[1], timeMap.getOrDefault(temp[1],0)+time);
                inMap.remove(temp[1]);

                // print
//                System.out.printf("출차, inTime:%s, outTime:%s\n", inTime, temp[0]);
//                System.out.printf("출차, carNum:%s, time:%d\n", temp[1], time);
            }
        }

        // 아직 출차하지 못한 차량은 "23:59"에 출차
        for(Map.Entry<String,String> entry: inMap.entrySet())
        {
            String inTime = entry.getValue();
            String carNum = entry.getKey();
            int time = calculateParkingTime(inTime);

            timeMap.put(carNum, timeMap.getOrDefault(carNum,0)+time);
            //print
//            System.out.printf("23:59 출차, inTime:%s, outTime:%s\n", entry.getValue(), "23:59");
//            System.out.printf("23:59 출차, carNum:%s, time:%d\n", carNum, time);

        }
    }

    public void calculateParkingFee(int[] fees, int[] parkingTime)
    {
        int basicTime = fees[0]; // min
        int basicFee = fees[1]; // won
        int unitTime = fees[2]; // min
        int unitFee = fees[3]; // won

        for(int i =0; i<parkingTime.length; i++)
        {
            int time = parkingTime[i];
            if(time <= basicTime) parkingTime[i] = basicFee;
            else
            {
                // 기본요금 계산
                parkingTime[i] = basicFee;
                time -= basicTime;

                // 단위요금 계산
                int unitNum = (int)Math.ceil((double)time / unitTime);
                parkingTime[i] += unitNum * unitFee;
            }
        }
    }

    public int calculateParkingTime(String inTime)
    {
        int hour = lastHour - Integer.parseInt(inTime.substring(0,2));
        int min = lastMin - Integer.parseInt(inTime.substring(3,5));

        return (hour*60 + min);
    }
    public int calculateParkingTime(String inTime, String outTime)
    {
        int hour = Integer.parseInt(outTime.substring(0,2)) - Integer.parseInt(inTime.substring(0,2));
        int min = Integer.parseInt(outTime.substring(3,5)) - Integer.parseInt(inTime.substring(3,5));

        return (hour*60 + min);
    }
}
