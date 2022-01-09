package test.GymCloth;

public class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int clothes[] = new int[n];
        Arrays.fill(clothes, 1);

        // 여벌옷 가져온 학생
        for(int spare: reserve)
            clothes[spare-1]++;

        // 체육복을 도난당한 학생
        for(int temp: lost)
            clothes[temp-1]--;

        // 체육복이 2벌인 학생이 이웃하는 학생 중 한명에게 체육복을 빌려주는 과정
        for(int i=0; i< n; i++)
        {
            if(clothes[i] == 2)
            {
                if(i != 0 && clothes[i-1] == 0)
                    clothes[i-1]++;
                else if(i != n-1 && clothes[i+1] == 0)
                    clothes[i+1]++;
            }
        }

        int answer = (int)Arrays.stream(clothes)
                .filter((int x) -> x >= 1).count();
        return answer;
    }
}
