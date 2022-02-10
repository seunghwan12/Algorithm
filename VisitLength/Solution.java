/*
프로그래머스 level2, 방문 길이
url:https://programmers.co.kr/learn/courses/30/lessons/49994

좌표평면 (0,0)에서 u(up), d(down), l(left), r(right)로 이루어진 문자열을
입력받아 이동한 길에서 중복되지 않은 길의 개수를 구하는 문제이다. 길은 길이가 1이다.
(0,0) -> (0,1)같은 경우이다.

중복되지 않은 길은 찾기 위해 Set을 자료구조로 선택하였고 Point 객체 start와 end를
맴버변수로 갖는 Path클래스를 선언하였다. 문자열의 각 원소를 반복하면서
이전 위치(Point객체 prev)와 현재 위치(Point 객체 now)를 변수로 하는 Path 객체를
만들었다. 중복을 방지하기 위해 prev와 now에 바꿔서 저장하고 총 개수를 2로 나누었다.

하지만 Set에 Path객체를 저장했고, equals메소드로 오버라이딩했는데도 중복을 허용하는 문제가
생겼다. 이유는 Set의 구현체로 HashSet를 사용했는데, 정작 hashcode()를 오버라이딩하지 않아
값이 동일한 두 객체가 다른 해시값을 가지기 때문이었다.

 */

package test.VisitLength;

import java.util.*;
import java.awt.Point;

class Solution {

    public static void main(String[] args)
    {
        Solution test = new Solution();
        String dirs = "ULURRDLLU";
        test.solution(dirs);
    }

    Set<Path> set = new HashSet<>();

    public int solution(String dirs) {
        Point prev = new Point(0,0);
        Point now = new Point(0,1);

        Point prev1 = new Point(0,0);
        Point now1 = new Point(0,1);
        for(int i=0; i<dirs.length(); i++)
        {
            now = getNextPosition(prev, dirs.charAt(i));
            if(prev != now)
            {
                Path path1 = new Path(prev, now);
                Path path2 = new Path(now, prev);
                set.add(path1);
                set.add(path2);
//                System.out.printf("path1.hashcode() :%d\n", path1.hashCode());
//                System.out.printf("path2.hashcode() :%d\n", path2.hashCode());
//                System.out.printf("path3.hashcode() :%d\n", path3.hashCode());
                prev =now;
            }
        }

//        for(Path path:set)
//        {
//            System.out.printf("start.x: %d, start.y:%d -> end.x:%d, end.y:%d\n", path.start.x,path.start.y,path.end.x,path.end.y);
//        }

        int answer = set.size()/2;
        System.out.println(answer);
        return answer;
    }

    public Point getNextPosition(Point prev,char dir )
    {
        int x_diff = 0; int y_diff = 0;

        switch (dir)
        {
            case 'U':
                y_diff = 1;
                break;
            case 'D':
                y_diff = -1;
                break;
            case 'L':
                x_diff = -1;
                break;
            case 'R':
                x_diff = 1;
                break;
        }

        if(prev.x+x_diff>=-5 && prev.x+x_diff <= 5 &&
                prev.y+y_diff>=-5 && prev.y+y_diff <= 5)
            return new Point(prev.x+x_diff, prev.y+y_diff);
        else
            return prev;

    }
}

class Path
{
    Point start;
    Point end;
    public Path(Point start, Point end)
    {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object obj)
    {
//        System.out.println("equals");
        if(obj instanceof Path)
        {
            Path path = (Path)obj;
            return (start.equals(path.start)
                    && end.equals(path.end));
        }
        else
            return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
