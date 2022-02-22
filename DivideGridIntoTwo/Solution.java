/*
프로그래머스 level2, 전력망을 둘로 나누기
 */

package test.DivideGridIntoTwo;

import java.util.*;

class Solution {

    int min = 100;
    Tower[] towers = null;
    int number;
    public int solution(int n, int[][] wires) {

        number = n;
        towers = new Tower[n+1];
        for(int i=1; i<=n; i++)
            towers[i] = new Tower(i);

        for(int[] wire: wires)
        {
            towers[wire[0]].addTower(wire[1]);
            towers[wire[1]].addTower(wire[0]);
        }

        for(int[] wire: wires)
        {
            findMin(wire);
        }

        return min;
    }

    public void findMin(int[] disconnected)
    {
        boolean[] found = new boolean[number+1];

        findNumber(1, disconnected, found);
        int one = 0;
        for(boolean bool: found)
        {
            if(bool)
                one++;
        }
        int other = found.length-one-1;
        int diff = (one - other) > 0 ? one-other : other - one;

        if(diff < min)
            min = diff;

    }

    public void findNumber(int towerNum, int[] disconnected, boolean[] found)
    {
        int size = towers[towerNum].connected.size();
        found[towerNum] = true;
        for(int i=0; i<size; i++)
        {
            int next
                    = towers[towerNum].connected.get(i);

            if(!(towerNum == disconnected[0] && next == disconnected[1]) && !(towerNum == disconnected[1] && next == disconnected[0]) && !found[next])
                findNumber(next, disconnected, found);
        }
    }
}

class Tower{
    int number;
    ArrayList<Integer> connected = new ArrayList<>();

    public Tower(int number)
    {
        this.number = number;
    }

    public void addTower(int num)
    {
        connected.add(num);
    }
}

