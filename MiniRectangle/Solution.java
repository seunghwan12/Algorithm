/*
프로그래머스 level1, 최소직사각형

여러 명함(직사각형) 중에서 모든 명함(직사각형)을 담을 수 있는 지갑을 구하는 문제이다.

우선 문제에서는 가로와 세로로 명함의 크기가 주어졌지만, 문제의 조건 중 명함을 눕힐 수 있다는 조건이 있기
때문에 가로와 세로로 구분하지 않고, 긴 변과 짧은 변으로 구분한다.
이제 각 직사각형으로부터 가장 큰 긴 변과 가장 큰 짧은 변을 구한다. 이 두 변을 이용하여 직사각형을 만들면
모든 직사각형보다 가로와 세로의 길이가 큰 직사각형이 된다.

개념적인 접근은 맞았지만, 구현하는 방식이 장황했다. 우선 긴 변과 작은 변을 필드로 갖는 rect라는 클래스를 만들고
ArrayList<rect>를 자료구조로 사용하였다. 리스트에 각 직사각형 객체를 만들어 넣었다. 그리고 람다식을 활용하여
앞에서 언급한 것처럼 가장 큰 긴 변과 가장 짧은 긴 변을 구하였다.
다른 사람의 풀이는 이런 번거로운 방식보다는 for문만으로 상술한 내용을 구현하였다. 내 풀이가 장황해진 것은 정확한 아이디어를
갖지 않고 푼 것이 원인이라고 생각한다. 정확한 청사진을 갖고 구현하지 않다보니 배가 산으로 간 것 같다. 또한 아이디어를 적지 않고
머리로 기억하니 문제를 풀다보면 처음 생각했던 아이디어를 까먹게 된다.
다음부터는 아이디어를 명확하게 생각하고 이를 적어두어 헤매지 않도록 하자.

 */

package test.MiniRectangle;

import java.util.*;

class Solution {

    public int modifiedSolution(int[][] sizes) {
        int walletLong = 1; // 긴 변
        int walletShort = 1; // 짧은 변
        int max, min;
        for(int[] rect: sizes)
        {
            max = Math.max(rect[0], rect[1]);
            min = Math.min(rect[0], rect[1]);
            walletLong = walletLong < max ? max : walletLong;
            walletShort = walletShort < min ? min : walletShort;
        }

        return walletLong*walletShort;
    }

    public int solution(int[][] sizes) {
        ArrayList<rect> cards = new ArrayList<rect>();
        for(int[] temp: sizes)
        {
            cards.add(new rect(temp[0], temp[1]));
        }
        rect maxBig = Collections.max(cards, (rect o1, rect o2) ->{
            if(o1.big < o2.big) return -1;
            else if(o1.big == o2.big) return 0;
            else return 1;
        });
        rect maxSmall = Collections.max(cards, (rect o1, rect o2) ->{
            if(o1.small < o2.small) return -1;
            else if(o1.small == o2.small) return 0;
            else return 1;
        });

        int answer = maxBig.big*maxSmall.small;
        return answer;
    }


}

class rect{
    int big;
    int small;

    public rect(int wid, int ht)
    {
        if(wid >= ht) { big = wid; small = ht;}
        else { big = ht ; small = wid ;}
    }
}
