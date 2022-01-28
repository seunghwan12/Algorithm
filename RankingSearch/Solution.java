package test.RankingSearch;
import java.lang.reflect.Array;
import java.util.*;

class Solution
{
    public static void main(String[] args)
    {
        Solution test = new Solution();
        String[] infoCollection = {"java backend junior pizza 150",
                "python frontend senior chicken 210",
                "python frontend senior chicken 150",
                "cpp backend senior pizza 260",
                "java backend junior chicken 80",
                "python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100",
                "python and frontend and senior and chicken 200",
                "cpp and - and senior and pizza 250",
                "- and backend and senior and - 150",
                "- and - and - and chicken 100",
                "- and - and - and - 150"};
        test.solution(infoCollection, query);
    }
    HashMap<String, ArrayList<Integer>> map
            = new HashMap<>();
    public int[] solution(String[] infoCollection,
                          String[] query)
    {
        int answer[] =new int[query.length];

        // 정보를 가능한 String조합으로 변경하여 저장
        for(String temp: infoCollection)
        {
            String[] condition = temp.split("\\s");
            int score = Integer.parseInt(condition[condition.length-1]);
            dfs("", condition, score, 0);
        }

        for(List<Integer> list: map.values())
            list.sort(Comparator.naturalOrder());


        for(int i=0; i<query.length; i++)
        {
            int cnt = 0;
            String temp = query[i].replaceAll(" and ", "");
            String arr[] = temp.split("\\s");
            System.out.println(temp);


            int requiredScore = Integer.parseInt(arr[1]);

            if(map.containsKey(arr[0]))
            {
                ArrayList<Integer> valueList = map.get(arr[0]);
                int low = 0;
                int high = valueList.size() - 1;
                int mid = 0;
                while(low <= high)
                {
                    mid = (low+high)/2;
                    if(valueList.get(mid) < requiredScore)
                        low = mid+1;
                    else
                        high = mid-1;
                }
                answer[i] = valueList.size() - low;
            }
        }

//        for(int temp: answer)
//            System.out.println(temp);

        return answer;
    }
    public void dfs(String str, String[] condition, int score, int idx)
    {
        if(idx == 4)
        {
            ArrayList<Integer> valueList= map.getOrDefault(str, new ArrayList<Integer>());
            valueList.add(score);
            map.put(str, valueList);
        }
        else {
            dfs(str + "-", condition, score, idx + 1);
            dfs(str + condition[idx], condition, score, idx + 1);
        }
    }
}



