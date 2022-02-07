package test.LifeBoat;

class Solution {
    public int solution(int[] people, int limit) {
        int start = 0; int end = people.length-1;
        int cnt = 0;
        //정렬
        Arrays.sort(people);

        // 링크드리스트
        while(start <= end)
        {
            if(people[start] + people[end] <= limit)
            {
                start++;
                end--;
            }
            else
                end--;

            cnt++;
        }

        return cnt;
    }
}
