package test.Printer;

import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Task target = null;
        Queue<Task> queue = new LinkedList<>();
        int cnt = 0; int max = 1;

        // Task 객체 만들어 큐에 삽입
        for(int i=0; i<priorities.length; i++)
        {
            if(i == location)
            {
                target = new Task(priorities[i]);
                queue.add(target);
            }
            else
                queue.add(new Task(priorities[i]));
        }

        // 우선순위의 최대값을 찾는다.
        max = getMaxPriority(queue);

        // queue의 head에 target task가 있고 우선순위가 가장 높다면
        // 반복문을 종료한다.
        while(!(queue.element().equals(target)
                && target.getPriority() >= max))
        {
            Task temp = queue.remove();

            if(temp.getPriority() < max)
                queue.add(temp);
            else
            {
                cnt++;
                max = getMaxPriority(queue);
            }
        }
        cnt++;
        return cnt;
    }

    public int getMaxPriority(Queue<Task> queue)
    {
        int max = 1;
        for(Task now: queue)
        {
            if(max < now.getPriority())
                max = now.getPriority();
        }

        return max;
    }
}

class Task{
    static int number = 0;
    final int priority;
    final int location;

    public Task(int priority)
    {
        this.priority = priority;
        location = number++;
    }

    public int getPriority()
    {
        return priority;
    }

    public int getLocation()
    {
        return location;
    }
}
