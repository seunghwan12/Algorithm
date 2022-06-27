package test.TriangularSnail;

class Solution {
    public int[] solution(int n) {
        int cnt = 1;
        int[][] triangle = new int[n][];
        int[] answer = new int[n*(n+1)/2];

        for(int i=0; i<n; i++)
            triangle[i] = new int[i+1];

        int number = (int)Math.ceil((double)n / 3);

        for(int i =0; i<number; i++)
        {
            if(n - 3*i == 1) {
                triangle[2 * i][i] = cnt;
            }
            else
            {
                for(int j=2*i; j<n-i; j++)
                {
                    triangle[j][i] = cnt;
                    cnt++;
                }

                for(int k = i+1;
                    k < triangle[n-1-i].length-i; k++)
                {
                    triangle[n-1-i][k] = cnt;
                    cnt++;
                }

                for(int l = n-2-i; l > 2*i; l--)
                {
                    triangle[l][triangle[l].length-1-i]
                            = cnt;
                    cnt++;
                }
            }
        }
        cnt = n*(n+1)/2 -1;
        print(triangle);
        System.out.printf("n: %d, cnt: %d\n", n, cnt);
        for(int i= n-1; i>=0; i--)
        {
            for(int j=triangle[i].length-1; j>=0; j--) {
                answer[cnt] = triangle[i][j];
                cnt--;
            }
        }
        for(int elem: answer)
            System.out.printf("%d ", elem);
        return answer;
    }

    public void print(int[][] triangle)
    {
        for(int i=0; i<triangle.length; i++)
        {
            for(int j=0; j<triangle[i].length; j++)
                System.out.printf("%d ", triangle[i][j]);
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        Solution test = new Solution();
        test.solution(5);
    }
}
