package test.RankingSearch;
import java.util.*;

class Solution2
{
    public static void main(String[] args)
    {
        Solution2 test = new Solution2();
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
    HashMap<Info, ArrayList<Integer>> map
            = new HashMap<>();
    public int[] solution(String[] infoCollection,
                          String[] query)
    {
        int answer[] =new int[query.length];

        // 정보를 info객체로 변환하여
        for(String temp: infoCollection)
        {
            String[] condition = temp.split("\\s");
            Info tempInfo = new Info(condition);
            int score = Integer.parseInt(condition[condition.length-1]);

            ArrayList<Integer> valueList = map.getOrDefault(tempInfo,
                    new ArrayList<Integer>());
            valueList.add(score);
            map.put(tempInfo, valueList);
        }

        for(List<Integer> list: map.values())
            list.sort(Comparator.naturalOrder());

        Set<Map.Entry<Info, ArrayList<Integer>>> set= map.entrySet();

        for(int i=0; i<query.length; i++)
        {
            int cnt = 0;
            String[] requireCondition = query[i].split("\\s*(and)*\\s");
            int requiredScore = Integer.parseInt(requireCondition[requireCondition.length-1]);
            System.out.printf("requiredScore: %d\n", requiredScore);
            for(Map.Entry<Info, ArrayList<Integer>> entry: set)
            {
                if(isConditionMatched(requireCondition, entry.getKey()))
                {
                  System.out.println(entry.getKey().toString());
                    ArrayList<Integer> valueList = entry.getValue();
                    for(int temp: valueList) System.out.println(temp);
                    int start = 0; int end = valueList.size()-1;
                    int mid = 0;

                    while(start <= end)
                    {
                        mid =(start+end)/2;
                        if(valueList.get(mid) < requiredScore) {
                            start = mid+1;
                        }else {
                            end = mid-1;
                        }
                    }

                    cnt += valueList.size()-start;
                    System.out.printf("cnt:%d\n", cnt);

                }
            }
            System.out.println();

            answer[i] = cnt;
        }

        for(int temp: answer)
            System.out.println(temp);

        return answer;
    }

    public boolean isConditionMatched(String[] requireCondition, Info info)
    {
        if(!info.language.equals(requireCondition[0]) && !requireCondition[0].equals("-"))
            return false;
        if(!info.group.equals(requireCondition[1]) && !requireCondition[1].equals("-"))
            return false;
        if(!info.career.equals(requireCondition[2]) && !requireCondition[2].equals("-"))
            return false;
        if(!info.food.equals(requireCondition[3]) && !requireCondition[3].equals("-"))
            return false;

        return true;
    }

}

class Applicant{
    int number;
    int score;

    public Applicant(int number, int score)
    {
        this.number = number;
        this.score = score;
    }
}

class Info
{
    String language;
    String group;
    String career;
    String food;

    public Info(String[] info)
    {
        language = info[0];
        group = info[1];
        career = info[2];
        food = info[3];
    }

    public boolean equals(Info other)
    {
        if(this.language.equals(other.language)
                && this.group.equals(other.group)
                && this.career.equals(other.group)
                && this.food.equals(other.food))
            return true;
        else
            return false;
    }

    public String toString()
    {
        return "language: "+language+", group: "+group+", career: "+career+
                ", food: "+food;
    }
}
