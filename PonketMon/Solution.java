/*
프로그래머스 levl1, 폰켓몬

종류가 중복된 포켓몬 중에서 가장 많은 종류의 포켓몬을 가져가는 문제
가장 많은 종류를 가져가기 위해서는 포켓몬의 개수보다는 몇 종류가 주어졌는지(M)를 알아야 한다.
따라서 중복을 허용하지 않는 set에 집어 넣어 주어진 포켓몬들이 몇가지 종류를 가지는 지 찾는다.
그리고 가져갈 수 있는 포켓몬 수(N/2)와 비교한다. 만약 M이 N/2보다 크면
N/2 종류의 포켓몬을 가져갈 수 있고, 그렇지 않으면 M만큼 가져갈 수 있다.
 */

package test.PonketMon;

import java.util.*;

class Solution {
    public int solution(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for(int temp: nums)
            set.add(temp);

        int lenOfSet = set.size();
        int lenOfNums = nums.length;
        if(lenOfSet >= lenOfNums/2)
            return lenOfNums/2;
        else
            return lenOfSet;
    }
}