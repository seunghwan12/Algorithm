/*
프로그래머스 level2, 2021 카카오 블라인드 채용

코스요리의 개수(n)이 주어질 때, 손님들이 가장 많이 주문한 n개의 요리 조합을
구하는 문제이다.

(1) 각 손님이 해당요리를 주문했는지 확인하는 2차원 배열을 구한다.
행은 손님의 수이고, 열은 요리의 개수(27)이다.
(2) 2명 이상의 손님이 주문한 요리들을 찾고, 그 요리들 중에서 n개의 조합을 구한다.
(3) 요리 조합을 몇명이 시켰는지 확인한다. 손님의 수을 길이로 하는 배열을 선언하고
1로 채워넣는다. (1)의 customerMenu배열을 활용하여 요리조합의 모든 요리를 손님이 시켰는지
확인한다. (중복된 반복문)조합의 각 요리를 각 손님이 주문하얐는지 확인하여 하나라도 주문하지 않았으면
0을 대입하였다.(And논리 구현)
(4) (3)에서 얻은 배열에서 1의 개수를 계산하여 현재 요리조합의 주문수를 구한다.
이 주문수가 이전까지 찾은 요리조합의 최대주문수와 동일하면 리스트에 넣고,
최대주문수보다 크면 기존 리스트에 있던 요리 조합을 모두 제거하고 새로운 요리조합을 넣는다.

풀이는 맞았지만, 효율성의 측면에서 매우 부족하다. 특히 테스트케이스 13,14,15의 경우 약 2000ms,
4000ms, 2000ms의 시간이 걸려 비효율적이다. 모범풀이를 확인해보니 각 손님이 주문한 요리의 조합을
구하고 이를 Map<String, Integer>에 넣어 최대 주문수를 계산하였다. 또한 조합을 계산하는 과정에서
모범풀이에 비래 시간복잡도가 많이 증가하였다.
 */

package test.MenuRenewal;

import java.util.*;

class Solution {
    boolean[][] customerMenu;
    int[] numOfOrder = new int[27];
    ArrayList<int[]> combList = new ArrayList<>();
    ArrayList<String> strList = new ArrayList<>();
    ArrayList<String> answerList = new ArrayList<>();
    StringBuilder builder = new StringBuilder();

    int numOfCustomer;
    int dishNum;
    long max;

    public String[] solution(String[] orders, int[] course) {

        numOfCustomer = orders.length;

        // 2차원 배열 완성
        createMatrix(orders);

        // 코스 요리의 수에 따라 요리의 조합을 만들고
        // 가장 많이 주문한 요리 조합을 구하는 블럭
        for(int num: course)
        {
            combList.clear(); strList.clear(); // 초기화
            findCombination(num);
            max = 2;

            // 각 조합이 가장 많이 주문한 요리조합인지 확인하고
            // strList에 넣는다
            for(int[] arr: combList)
                checkCourse(arr);

            // StrList에서 answer리스트로 옮긴다.
            for(String str: strList)
                answerList.add(str);
        }

        // answer배열을 만들고 정렬한다.
        String[] answer = answerList.toArray(new String[1]);
        Arrays.sort(answer, (x,y) -> {
            return x.compareTo(y);
        });
        return answer;
    }

    public void createMatrix(String[] orders)
    {
        customerMenu = new boolean[orders.length][27];

        for(int i=0; i< orders.length; i++)
        {
            for(int j=0; j<orders[i].length();j++)
            {
                int num = orders[i].charAt(j) - 'A';
                customerMenu[i][num] = true;
                numOfOrder[num]++;
            }
        }
    }

    public void findCombination(int num)
    {
        // 2번 이상 주문한 음식의 리스트
        ArrayList<Integer> twiceOrderedDishes = new ArrayList<>();
        for(int i=0; i< 27; i++)
        {
            if(numOfOrder[i] >= 2)
                twiceOrderedDishes.add(i);
        }

        // 2번 이상 주문한 음식들에서 num개의 조합을 만드는 블럭
        Integer[] arr = twiceOrderedDishes
                .toArray(new Integer[1]);
        boolean[] visited = new boolean[arr.length];
        dishNum = num;
        combination(arr, visited, 0, arr.length, num);
    }

    public void combination(Integer arr[], boolean[] visited,
                            int start, int n, int r)
    {
        if (r == 0)
        {
            int[] temp = new int[dishNum];
            int j=0;
            for (int i = 0; i < n; i++)
            {
                if (visited[i]) {
                    temp[j] = arr[i];
                    j++;
                }
            }
            combList.add(temp);
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }

    public void checkCourse(int arr[])
    {
        // 같은 음식조합을 주문한 손님의 수를 구하는 블럭
        int[] eaten = new int[numOfCustomer];

        Arrays.fill(eaten, 1);
        Arrays.stream(arr).forEach(x ->{
            for(int i=0; i< numOfCustomer; i++)
                if(!customerMenu[i][x]) eaten[i] = 0;
        });

        long numOfSameDishes = Arrays.stream(eaten)
                .filter(x -> x==1)
                .count();
        if(numOfSameDishes == max)
        {
            StringBuilder builder = new StringBuilder();
            Arrays.stream(arr).forEach(x -> {
                builder.append((char)(x+'A'));
            });
            strList.add(builder.toString());
        }

        else if(numOfSameDishes > max)
        {
            max = numOfSameDishes;
            strList.clear();
            StringBuilder builder = new StringBuilder();
            Arrays.stream(arr).forEach(x -> {
                builder.append((char)(x+'A'));
            });
            strList.add(builder.toString());
        }
    }
}
