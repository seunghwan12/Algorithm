package test.JoyStick;

// dfs로 다시 풀기

// 문제는 dfs에서 각 분기에서 저장된 상태가 다른 분기에서도 이어지는 문제

// 즉 객체가 복사된다고 생각했지만 사실은 레퍼런스변수로 객체에 접근하기 때문에
// 객체의 상태가 유지되는 것!

// 변수 location을 지역변수가 아니라 인스턴스변수로 만들어서
// 메소드가 공유할 수 있도록 만듬
// 따라서 dfs의 재귀함수가 각자 가지고 있어야 할 location이 공유되므로
// 값이 변경되는 문제가 발생함. 따라서 인스턴스 변수가 아니라 지역변수로 변경한다.
// dfs를 제대로 구현하지 못하게 됨.

class Solution2 {
    int min = 320;
    String str;

    public int solution(String name) {
        str = name;
        int location = 0;
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
            cnt+= getVerticalCost(location);
            targetNum--;
            needed[location] = false;
        }

        moveToNext(location, needed, targetNum, cnt);
        System.out.println(min);
        return min;
    }

    public void moveToNext(int location, boolean[] needed,
                           int targetNum, int cnt)
    {
        if(targetNum == 0)
        {
            if(min > cnt) min = cnt;
        }
        else
        {
            //왼쪽으로 이동
            int left = findLeftLocation(location, needed);
            int horizontalCost = (left < location)
                    ? location- left
                    : location + (needed.length-left);
            int verticalCost = getVerticalCost(left);
            needed[left] = false;
            moveToNext(left, needed, targetNum-1, cnt+horizontalCost+verticalCost);

            // 원래 상태로 복구
            needed[left] = true;

            // 오른쪽으로 이동
            int right = findRightLocation(location, needed);
            horizontalCost = location < right
                    ? right - location
                    : needed.length-location + right;
            verticalCost = getVerticalCost(right);
            needed[right] = false;
            moveToNext(right, needed, targetNum-1, cnt+horizontalCost+verticalCost);

            // 원래 상태로 복구
            needed[right] = true;

        }
    }

    public int getVerticalCost(int location)
    {
        char target = str.charAt(location);
        int cost1 = target - 'A';
        int cost2 = 'Z' - target +1;
        return Math.min(cost1, cost2);
    }

    public int findLeftLocation(int location, boolean[] needed)
    {

        int left = -1;
        for(int i=location-1; left == -1; i--)
        {
            if(i == -1) i = needed.length-1;
            if(needed[i]) left = i;
        }

        return left;
    }

    public int findRightLocation(int location, boolean[] needed)
    {
        int right = -1;
        for(int i=location+1; right == -1; i++)
        {
            if(i == needed.length) i = 0;
            if(needed[i]) right = i;
        }

        return right;
    }

    public static void main(String[] args)
    {
        Solution2 test = new Solution2();
        test.solution("ABAAABB");
    }

}

