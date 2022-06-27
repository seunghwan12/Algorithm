/*
programmers level2, 교점에 별 만들기
url:https://programmers.co.kr/learn/courses/30/lessons/87377#

Ax+By+c의 형태로 주어진 직선들의 교점 중 x,y좌표가 모두 정수인 점만을 표현하는 문제이다.
교점은 *로 표현하고 교점이 아닌 점은 .으로 표현하여 모든 별을 포함하는 최소의 사각형을 만든다.

문제를 푸는 알고리즘은 다음과 같다.

1. 직선 두 개의 교점을 찾고
2. 교점을 Set에 저장한다.
3. x,y좌표 각각의 최대, 최소를 구하고
4. 교점을 *로, 나머지를 .으로 표현하는 최소사각형을 만든다.

 */

package test.StarIntersection;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

class Solution {
    Set<Point> set = new HashSet<>();
    int x_max = Integer.MIN_VALUE;
    int x_min = Integer.MAX_VALUE;
    int y_max = Integer.MIN_VALUE;
    int y_min = Integer.MAX_VALUE;

    public String[] solution(int[][] line) {

        // 교점 찾기
        for(int i=0; i<line.length; i++)
        {
            for(int j=i+1; j<line.length; j++)
                findIntersection(line[i], line[j]);
        }

        // 교점을 표현할 범위 찾기
        findRange();

        //String[]으로 교점 그리기
        String[] answer = representIntersection();

        // System.out.printf("x, max: %d, min:%d\n",
        //                   x_max, x_min);
        // System.out.printf("y, max: %d, min:%d\n",
        //                   y_max, y_min);

        // for(Point point: set)
        // {
        //     System.out.printf("x: %d, y:%d\n"
        //                      ,point.x, point.y);
        // }

        // for(String str: answer)
        //     System.out.println(str);

        return answer;
    }

    public void findIntersection(int[] line1,
                                 int[] line2)
    {
        long denominator
                = (long)line1[0]*line2[1] - (long)line1[1]*line2[0];
        long x_numerator
                = (long)line1[1]*line2[2] - (long)line1[2]*line2[1];
        long y_numerator
                = (long)line1[2]*line2[0] - (long)line1[0]*line2[2];

        // 평행
        if(denominator == 0)
            return;

            // x,y가 정수가 아님
        else if(x_numerator % denominator != 0
                || y_numerator % denominator != 0)
            return;

        else
        {
            int x = (int)(x_numerator / denominator);
            int y = (int)(y_numerator / denominator);
            set.add(new Point(x,y));
        }
    }

    public void findRange()
    {
        for(Point p:set)
        {
            int x = p.x; int y = p.y;

            if(x>x_max) x_max = x;
            if(x<x_min) x_min = x;
            if(y>y_max) y_max = y;
            if(y<y_min) y_min = y;
        }
    }

    public String[] representIntersection()
    {
        int row = y_max - y_min + 1;
        int col = x_max - x_min + 1;
        char[][] plane = new char[row][col];
        String[] answer = new String[row];

        for(char[] arr: plane)
            Arrays.fill(arr, '.');

        for(Point point: set)
        {
            plane[y_max- point.y][point.x-x_min]
                    = '*';
        }

        for(int i=0; i<row; i++)
            answer[i] = new String(plane[i]);

        return answer;
    }
}

