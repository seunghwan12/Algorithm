package test.JoyStick;



/*
프로그래머스 level2, 조이스틱

조이스틱(위, 아래, 좌, 우)를 조작하여 원하는 알파벳 이름을 완성하는 문제이다.
완성해야 되는 이름의 개수만큼의 'A'로 구성된 문자열이 주어지고 왼쪽, 오른쪽으로 조이스틱을
조작해 커서를 움직일 수 있고, 위, 아래로는 알파벳을 변경할 수 있다. 첫번째에서 왼쪽으로 움직이면
마지막 위치로 이동하고, 마지막 위치에서는 그 반대로 작동한다. 위로 조작하면 다음 알파벳으로
아래로 조작하면 이전 알파벳이 된다. 특히 'A', 'Z'에서 각각 이전, 다음으로 움직이면 'Z', 'A'가
된다. 원하는 알파벳을 완성하는데 필요한 조이스틱 조작 횟수의 최솟값을 구한다.

알파벳을 변경하는 것은 항상 다음 알파벳으로만 움직이거나 이전 알파벳으로 움직이는 방법만 있으므로
두 방법의 조작 횟수를 비교한다.

다음으로 왼쪽, 오른쪽 커서를 이동하는 것은 dfs로 해결하였다. 왼쪽과 오른쪽으로 이동하는 모든 경우를
탐색하여 최소 조작횟수를 구하는 것이다. 위치를 저장하는 변수 location과 'A'가 아닌 인덱스 번호에
true를 저장한 needed배열을 만들고 moveToNext메소드에서 재귀를 구현하였다. 문제를 푸는 과정에서
객체가 copy by value인지 copy by reference인지 헷갈렸다. 정확히 말하면
객체를 참조하는 레퍼런스 변수의 copy by value이므로 객체 자체는 복사되는 것이 아니었다.
따라서 needed배열의 레퍼런스 변수만을 인자로 전달하면 needed배열이 서로 공유되는 것이다.

Solution class에서는 needed배열을 복사하여 문제를 풀었고, Solution2에서는 needed 공유하여
문제를 풀었다. 하지만 이 dfs로 문제를 푸는 것보다 훨씬 작은 시간복잡도를 갖는 풀이법이 있으므로
다시 풀 때는 그 방법을 생각하여 문제를 풀어보도록 한다.
 */

import java.util.Arrays;

class Solution {
    int location = 0;
    int min = 320;
    String str;

    public int solution(String name) {
        str = name;
        boolean[] needed
                = new boolean[name.length()];
        int targetNum = 0;
        int cnt = 0;

        // 방문이 필요한 위치와 수를 계산
        for(int i=0; i<name.length(); i++)
        {
            if(name.charAt(i) != 'A')
            {
                needed[i] = true;
                targetNum++;
            }
        }

        // 첫번째 위치 알파벳 변경
        if(needed[location])
        {
            System.out.printf("location:%d\n", location);

            cnt+= getVerticalCost();
            targetNum--;
            needed[location] = false;
        }

        moveToNext(needed, targetNum, cnt);
        System.out.println(min);
        return min;
    }

    public void moveToNext(boolean[] needed,
                           int targetNum, int cnt)
    {
        if(targetNum == 0)
            if(min > cnt) min = cnt;
        else
        {
            // 원래 위치 저장
            int temp = location;
            boolean[] copied = Arrays.copyOf(needed, needed.length);

            //왼쪽으로 이동
            int horizontalCost
                    = findLeftCost(needed);

            int verticalCost = getVerticalCost();
            needed[location] = false;
            moveToNext(needed, targetNum-1, cnt+horizontalCost+verticalCost);

            // 원래 상태로 복구
            location = temp;

            // 오른쪽으로 이동
            horizontalCost = findRightCost(copied);
            verticalCost = getVerticalCost();
            copied[location] = false;
            moveToNext(copied, targetNum-1, cnt+horizontalCost+verticalCost);
        }
    }

    public int getVerticalCost()
    {
        char target = str.charAt(location);
        int cost1 = target - 'A';
        int cost2 = 'Z' - target +1;
        return Math.min(cost1, cost2);
    }

    public int findLeftCost(boolean[] needed)
    {

        int leftCost = 0; int left = -1;
        for(int i=location-1; left == -1; i--)
        {
            if(i == -1) i = needed.length-1;
            leftCost++;
            if(needed[i]) left = i;
        }
        location = left;

        return leftCost;
    }

    public int findRightCost(boolean[] needed)
    {
        int rightCost = 0; int right = -1;

        for(int i=location+1; right == -1; i++)
        {
            if(i == needed.length) i = 0;

            rightCost++;
            if(needed[i]) right = i;
        }

        location = right;

        return rightCost;
    }

    public static void main(String[] args)
    {
        Solution test = new Solution();
        test.solution("ABAAABB");
    }

}
