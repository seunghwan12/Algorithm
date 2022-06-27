/*
프로그래머스 level2, 후보키
(url: https://programmers.co.kr/learn/courses/30/lessons/42890)

주어진 데이터베이스에서 후보키의 개수를 찾는 문제이다.
후보키는 릴레이션의 튜플을 식별할 수 있는 속성 또는 속성의 집합이며 유일성과 최소성을 만족한다.

이번 문제를 풀기 위해 다음의 알고리즘을 구성하였다

1. 각 차례마다 가능한 조합을 찾아 집합에 삽입한다.
   - 칼럼의 개수 n개에서 k개를 뽑는다.
   - 모든 차례는 k=1인 차례부터 k=n인 차례를 의미한다.

2. 이 집합에서 최소성을 만족하지 않는 조합을 제외한다.

   - 비트마스크와 비트연산자(&)를 사용하여 각 차례에서 얻은 조합의 부분집합에 지금까지 밝혀진 후보키가
     포함되는지를 확인한다.

3. 이 집합에서 유일성을 만족하는 조합만을 후보키 집합에 저장한다.

   - 각 튜플에서 조합의 각 원소에 해당되는 칼럼을 모두 더해 문자열로 만들어 Set에 저장한다.
   - 만약 Set의 크기가 릴레이션의 row와 동일하다면 유일성을 만족한다.

PS. 비트마스크을 통해 손쉽게 최소성을 확인할 수 있었다. 하지만 비트마스크 대신에 boolean배열의
ArrayList를 자료구조로 활용할 수도 있을 것 같다.

 */

package test.SubKey;

import java.util.*;

class Solution {
    Set<Integer> combination = null;
    Set<Integer> subKeys = new HashSet<>();
    Set<int[]> set = new HashSet<>();
    boolean[] selected;
    public int solution(String[][] relation) {
        int col = relation[0].length;
        int row = relation.length;

        for(int i=0; i<col; i++)
        {
            combination = new HashSet<>();
            selected = new boolean[col];
            comb(relation[0].length, i+1, 0);
            checkMinimality();
            checkUniqueness(relation, row, col);
        }

        int answer = subKeys.size();
//        System.out.println("subkeys");
//        for(int temp: subKeys)
//            System.out.println(Integer.toBinaryString(temp));
//        System.out.printf("answer :%d\n", answer);
        return answer;
    }

    public void comb(int n, int k,int loc)
    {
        if(k == 0)
        {
            int comb = 0;
            for(int i=0; i<selected.length; i++)
            {
                if(selected[i]) comb = comb | (1 <<i);
            }
//            System.out.println(Integer.toBinaryString(comb));
            combination.add(comb);
        }
        for(int idx=loc; idx<selected.length; idx++)
        {
            selected[idx] = true;
            comb(n, k-1, idx+1);
            selected[idx] = false;
        }
    }

    // 최소성을 만족시키지 않는 조합을 제거한다.
    public void checkMinimality()
    {
        Set<Integer> tempStore = new HashSet<>();
        for(int temp: combination)
        {
            boolean isSubkey = true;
            for(int subKey: subKeys)
            {
                if((temp & subKey) == subKey)
                {
                    isSubkey = false;
                    break;
                }
            }
            if(!isSubkey)
                tempStore.add(temp);
        }

        for(int temp: tempStore)
            combination.remove(temp);
    }

    // 유일성을 만족시키지 않는 조합을 제거한다.
    public void checkUniqueness(String[][] relation, int row, int col)
    {
        // temp의 각 비트에 해당하는 칼럼의 원소들을 모두 합한 문자열로 만들고
        // 반복문이 종료된 후에 set의 크기가 row이면 유일성을 만족하고
        // 그렇지 않으면 유일성을 만족하지 않는다.
        Set<String> uniqueStrings = null;
        boolean[] selected = null;
        for(int temp: combination)
        {
            uniqueStrings = new HashSet<>();
            selected = new boolean[col];

            for(int i=0; i<col; i++)
            {
                if(((temp>>i) & 1) == 1)
                    selected[i] = true;
            }

            for(int i=0; i<row; i++)
            {
                StringBuilder sb = new StringBuilder();
                for(int j=0; j<col;j++)
                {
                    if(((temp>>i) & 1) == 1)
                        sb.append(relation[i][j]);
                }
                uniqueStrings.add(sb.toString());
            }

//            for(String str:uniqueStrings)
//                System.out.println(str);

            // 유일성을 만족하는 경우
            if(uniqueStrings.size() == row) {
                subKeys.add(temp);
//                System.out.println(Integer.toBinaryString(temp));
            }
        }
    }

    public static void main(String[] args)
    {
        Solution test = new Solution();
        String[][] relation = {{"100","ryan","music","2"},
                {"200","apeach","math","2"},
                {"300","tube","computer","3"},
                {"400","con","computer","4"},
                {"500","muzi","music","3"},
                {"600","apeach","music","2"}};
        test.solution(relation);
    }
}