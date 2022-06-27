package test.Compression;

import java.util.*;

class Solution {
    ArrayList<Integer> output = new ArrayList<>();
    HashMap<String, Integer> dict = new HashMap<>();

    public int[] solution(String msg) {

        int cnt = 0;
        int[] answer = null;
        // 길이가 1인 단어를 포함하도록 사전을 초기화
        for(int ch=65;ch<=90;ch++)
        {
            dict.put(Character.toString((char)ch), cnt);
            cnt++;
        }

        // 압축과정
        int start = 0; int end = 0;
        String now = null;
        for(int i=1; i<=msg.length(); )
        {
            end = i;
            now = msg.substring(start, end);
//            System.out.printf("start: %d, end:%d, str:%s\n", start, end, now);
            if(dict.containsKey(now)) i++;
            else{
                dict.put(now, cnt);
                cnt++;

                output.add(dict.get(msg.substring(start, end-1)));
                start = end-1;
            }
        }
        output.add(dict.get(now));

        // ArrayList to Array
        answer = new int[output.size()];
        for(int i=0; i<output.size(); i++)
            answer[i] = output.get(i);

        return answer;
    }

    public static void main(String[] args)
    {
        Solution test = new Solution();
        String msg = "KAKAO";
        test.solution(msg);
    }
}
