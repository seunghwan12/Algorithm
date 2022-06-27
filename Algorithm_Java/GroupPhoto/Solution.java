package test.GroupPhoto;

import java.util.Arrays;

class Solution {

    public static void main(String[] args)
    {
        String[] data = {"N~F=0", "R~T>2"};
        Solution test = new Solution();
        test.solution(2, data);
    }

    int number = 0;
    String[] conditions;
    final char[] indexes = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};

    public int solution(int n, String[] data) {

        conditions = data;

        // indexes배열은 order 배열에서의 인덱스와 카카로프렌즈를 순서대로 매칭한다.
        // order배열은 단체사진에서 카카오프렌즈의 위치를 저장한다.
        int[] order = new int[8];

        Arrays.fill(order, -1);

        dfs(8, order);

        return number;
    }

    public void dfs(int cnt, int[] order)
    {
        System.out.println(cnt);
        if(cnt == 0)
        {
            if(isSatisfied(order))
                number++;
            return;
        }
        for(int i=0; i<order.length; i++)
        {
            if(order[i] == -1)
            {                order[i] = cnt;
                dfs(cnt-1, order);
                order[i] = -1;
            }
        }
    }

    public boolean isSatisfied(int[] order)
    {
        for(String condition: conditions)
        {
            int first = findLocation(condition.charAt(0), order);
            int second = findLocation(condition.charAt(2), order);
            int len = (first > second) ? first -second-1 : second - first-1;

            if(condition.charAt(3) == '=')
            {
                if(len != condition.charAt(4) - '0')
                    return false;
            }
            else if(condition.charAt(3) == '>')
            {
                if(len <= condition.charAt(4) - '0')
                    return false;
            }
            else
            {
                if(len >= condition.charAt(4) - '0')
                    return false;
            }
        }

        return true;
    }

    public int findLocation(char ch, int[] order)
    {
        for(int i=0; i<order.length; i++)
        {
            if(indexes[i] == ch)
                return order[i];
        }

        return -1;
    }
}
