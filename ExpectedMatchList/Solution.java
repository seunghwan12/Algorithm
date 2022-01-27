package test.ExpectedMatchList;

class Solution
{
    public int solution(int n, int a, int b)
    {
        int cnt = 1;
        int big = Math.max(a,b);
        int small = Math.min(a,b);

        while(isNotMatched(small, big))
        {
            cnt++;
            small = getNextNumber(small);
            big = getNextNumber(big);
        }
        return cnt;
    }

    public int getNextNumber(int number)
    {
        if(number % 2==0) return number/2;
        else return (number+1)/2;
    }

    public boolean isNotMatched(int small, int big)
    {
        if(big-small == 1 && big %2 == 0)
            return false;
        else
            return true;
    }
}
