/*
프로그래머스 level2, 튜플
 */

package test.Tuple;

import java.util.Arrays;
import java.util.HashSet;

class solution {
    public int[] solution(String s) {
        HashSet<String> set = new HashSet<>();
        s = s.substring(2, s.length()-2);
        String[] elements = s.split("\\},\\{");
        int[] answer = new int[elements.length];
        int order = 0;

        Arrays.sort(elements, (s1, s2) ->{
            return s1.length() - s2.length();
        });

        for(int i=0; i< elements.length; i++)
        {
            if(elements[i].equals(""))
                continue;
            String[] temp = elements[i].split(",");
            for(int j=0; j<temp.length; j++)
            {
                if(set.contains(temp[j]))
                    continue;
                else
                {
                    set.add(temp[j]);
                    answer[order++] = Integer.valueOf(temp[j]);
                }
            }
        }

        return answer;
    }
}