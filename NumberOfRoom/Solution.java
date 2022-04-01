package test.NumberOfRoom;

import java.util.*;

public class Solution {
    private Point prev;
    private Point cur;
    private int[] drow = {-1, -1, 0, 1, 1, 1, 0, -1};
    private int[] dcol = {0, 1, 1, 1, 0, -1, -1, -1};
    Map<Point, List<Point>> adjacencyList = new HashMap<>();

    public int solution(int[] arrows) {
        int cnt = 0;
        cur = new Point(0, 0);
        adjacencyList.put(cur, new ArrayList<>());

        for (int dir : arrows) {
            // 1 by 1 사각형의 두 대각선이 만나는 엣지케이스를 해결하기 위해 스케일을 2배 증가시킨다.
            for (int idx = 0; idx < 2; idx++) {
                prev = cur;
                cur = getNextPosition(dir);

                // 도착정점을 이미 방문한 경우
                if (adjacencyList.containsKey(cur)) {
                    // 출발정점과 도착정점 사이의 간선은 방문하지 않은 경우
                    if (!adjacencyList.get(cur).contains(prev)) {
                        adjacencyList.get(cur).add(prev);
                        cnt++;
                    }
                }
                // 도착지점을 방문한 적이 없는 경우
                else adjacencyList.put(cur, makeList(prev));

                // 출발정점은 이전경로의 도착정점이므로 이미 방문했다.
                // 따라서 map에 키가 이미 존재하므로 바로 인접정점을 추가해준다.
                if(!adjacencyList.get(prev).contains(cur))
                    adjacencyList.get(prev).add(cur);
            }
        }
        return cnt;
    }

    private ArrayList<Point> makeList(Point point) {
        ArrayList<Point> list = new ArrayList<>();
        list.add(point);
        return list;
    }

    private Point getNextPosition(int dir) {
        return new Point(cur.row + drow[dir], cur.col + dcol[dir]);
    }

    class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == this) return true;
            if ((obj instanceof Point)) {
                Point point = (Point) obj;
                if (this.row == point.row && this.col == point.col) {
                    return true;
                }
            }

            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    public static void main(String[] args) {
        int[] arrows = {6, 6, 6, 4, 4, 4, 2, 2, 2, 0, 0, 0, 1, 6, 5, 5, 3, 6, 0};
        Solution test = new Solution();
        int solution = test.solution(arrows);
        System.out.println("solution = " + solution);
    }
}


