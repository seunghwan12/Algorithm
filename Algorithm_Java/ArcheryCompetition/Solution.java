package test.ArcheryCompetition;

class Solution {

    int[] info;
    int[] bestResult = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int max = -55;

    public int[] solution(int n, int[] info) {
        this.info = info;

        int[] ryan = new int[11];
        int[] lose = {-1};

        // 완전탐색
        dfs(0, n, ryan);

        if(max > 0)
            return bestResult;
        else
            return lose;
    }

    public void dfs(int step, int remain, int[] result)
    {
        if(remain == 0 || step == result.length) {

            // 점수계산
            int diff = calculateDiff(info, result);

            // 가장 큰 점수 차이인지 확인
            if((diff == max && isPrior(result))) {
                bestResult = result.clone();
            } else if(diff > max){
                max = diff;
                bestResult = result.clone();
            }

            return;
        }

        if (step == result.length - 1) {
            result[step] = remain;
            dfs(step+1, 0, result);
            result[step] = 0;
        } else {
            for(int i=0; i<= remain; i++)
            {
                result[step] = i;
                dfs(step+1, remain-i, result);
                result[step] = 0;
            }
        }
    }

    public int calculateDiff(int[] appeach, int[] ryan)
    {
        int diff = 0;
        for(int i=0; i<appeach.length; i++) {
            if (appeach[i] != 0 || ryan[i] != 0)
                diff += (appeach[i] >= ryan[i]) ? i - 10 : 10 - i;
        }
        return diff;
    }

    public boolean isPrior(int[] result)
    {
        for(int i= result.length-1; i>= 0; i--)
        {
            if(result[i] > bestResult[i])
                return true;
            else if(result[i] < bestResult[i])
                return false;
        }

        return false;
    }

    public static void main(String[] args) {
        Solution test = new Solution();
        int[] info = {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
        int n = 5;
        int[] result = test.solution(n, info);

        for(int i=0; i<result.length; i++)
            System.out.printf("%d ", result[i]);
        System.out.println();
    }
}
