/*
프로그래머스 level2, 소수 찾기

주어진 문자열에서의 숫자들로 만들수 있는 소수의 개수를 찾는 문제이다.

소수의 개수를 찾기 위해서는 두 가지 메소드가 필요하다. 소수인지 확인하는 메소드와 순열을 구현하는
메소드이다. 하지만 나는 조합과 순열를 모두 구현하였다. 예를 들면, 3개 중에서 2개를 뽑고(조합),
2개 중에서 2개를 나열하는 것(순열)이다. 즉, 불필요하게 순열을 구현하였다.

사실 처음에 dfs와 재귀를 활용하는 부분에서 헷갈려서 이렇게 불필요한 방법으로 문제를 풀었다.
다시 조합 없이 순열만으로 문제를 풀 수 있었다.

*/

package test.FindPrimeNumber;

import java.util.HashSet;
import java.util.Arrays;

class Solution {
    char[] nums;
    HashSet<Integer> set = new HashSet<>();

    public int solution(String numbers) {
        nums = numbers.toCharArray();

        int[] visited
                = new int[numbers.length()];
        Arrays.fill(visited, -1);

        for(int i=1; i<=numbers.length(); i++)
            getCombination(visited, i, 0, 0);

        // for(Integer temp: set)
        //     System.out.println(temp);
        return set.size();
    }



    public boolean isPrime(int number)
    {
        if(number ==1 || number ==0) return false;
        for(int i=2; i<=Math.sqrt(number);i++)
        {
            if(number%i ==0)
                return false;
        }

        return true;
    }

    public void getCombination(int[] visited, int n,
                               int k, int order)
    {
        if(n == k)
        {
            String str = "";
            char[] arr = new char[n];
            boolean[] selected = new boolean[n];

            for(int i=0; i<visited.length; i++)
            {
                if(visited[i] != -1)
                    arr[visited[i]] = nums[i];
            }

            getNumbers(arr, selected, str, n, 0);

        }
        else if(order == visited.length) ;
        else
        {
            visited[order] = k;
            getCombination(visited, n, k+1, order+1);
            visited[order] = -1;
            getCombination(visited, n, k, order+1);
        }
    }

    public void getNumbers(char[] arr,
                           boolean[] selected,
                           String str,
                           int n, int k)
    {
        if(n == k)
        {
            int result = Integer.parseInt(str);
            if(isPrime(result)) set.add(result);

            return;
        }

        for(int i=0; i<n; i++)
        {
            if(selected[i]) continue;
            selected[i] = true;
            getNumbers(arr, selected, str+arr[i],
                    n, k+1);
            selected[i] = false;
        }
    }


}

class Solution2{

    char[] nums;
    HashSet<Integer> set = new HashSet<>();

    public int solution2(String numbers)
    {
        nums = numbers.toCharArray();

        boolean[] selected = new boolean[numbers.length()];

        for(int i=1; i<=numbers.length(); i++)
            permutation(selected, "", i, 0);

        return set.size();
    }
    public boolean isPrime(int number)
    {
        if(number ==1 || number ==0) return false;
        for(int i=2; i<=Math.sqrt(number);i++)
        {
            if(number%i ==0)
                return false;
        }

        return true;
    }
    public void permutation(boolean[] selected, String str, int n, int k)
    {
        if(n == k)
        {
            int result = Integer.parseInt(str);
            if(isPrime(result)) set.add(result);

            return;
        }

        for(int i=0; i<selected.length; i++)
        {
            if(selected[i]) continue;
            selected[i] = true;
            permutation(selected, str+nums[i],
                    n, k+1);
            selected[i] = false;
        }
    }
}
