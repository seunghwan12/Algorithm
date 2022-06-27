package test.Cache;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    Queue<String> cache = new LinkedList<>();

    public int solution(int cacheSize,
                        String[] cities)
    {
        int time = 0;

        for(String city: cities)
        {
            city = city.toLowerCase();

            // cache의 크기가 0인 경우
            if(cacheSize == 0)
                time += 5;

            // cache hit
            else if(cache.contains(city))
            {
                time++;
                cache.remove(city);
                cache.add(city);
            }

            // cache miss
            else
            {
                time += 5;

                if(cache.size() < cacheSize)
                    cache.add(city);
                else
                {
                    cache.remove();
                    cache.add(city);
                }
            }
        }

        return time;
    }
}
