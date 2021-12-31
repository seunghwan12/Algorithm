package test.FailureRate;

/*
N번째 스테이지에 도달한 플레이어수 = N에 멈춰있는 플레이어수 + N을 클리어한 플레이어수
N을 클리어한 플레이어수 = N+1번째 스테이지에 도달한 플레이어수

이번 문제는 각 플레이어의 현재 스테이지를 입력받고 각 스테이지의 실패율을 계산하여 실패율에 따른 스테이지의 순서를 내림차순으로
반환하는 문제이다. 문제를 분류하면 스테이지별 실패율을 계산하는 부분과 실패율에 따라 스테이지의 순서를 내림차순으로 정리하는 부분으로 나눌 수 있다.
먼저 실패율을 계산할 때, N번째 스테이지에 도달한 플레이어수는 N+1번째 스테이지에 도달한 플레이어수에서 N에 위치한 플레이어수를 더함으로써 구할 수 있다.
즉, 재귀적으로 N번째 스테이지에 도달한 플레이어수를 계산할 수 있는 것이다. 이후 나눗셈으로 실패율을 쉽게 계산할 수 있다.실패율에 따라 스테이지의 순서를
정렬하는 부분은 TreeSet을 자료구조로 사용하여 구하였다. TreeSet은 SortedSet이므로 입력받은 실패율을 정렬해준다는 장점이 있다
앞서 구한 실패율을 Set에 넣고 descendingIterator()로 내림차순으로 정렬하여 Iter로 가져론다. Iter의 각 elem과 각 스페이지의 실패율 비교하여
같으면 answer배열에 순서대로 인덱스를 넣는다. 이때 i=1부터 n까지 반복하므로 안정성을 유지된다.

다른 사람의 풀이(모범답안)를 보니 실패율에 따라 스테이지 순서를 정렬하는 부분이 달랐다. 이 분은 각 스테이지의 번호와 실패율을 인스턴스 변수로 갖는 클래스를 만들었고,
이 클래스는 Comparable 인터페이스를 구현한다. 인스턴스를 만들어 리스트에 저장하고 collection.sort()를 사용하여 정렬하고 answer에 각 번호를 입력한다.
내풀이와 모범답안을 비교하면 우선 모범답안는 내 풀이보다 메모리 복잡도가 작다. 내 풀이는 TreeSet과 Iterator, 2개의 자료구조를 사용하지만, 모범답안은 collection만을 사용한다.
또한 과정이 보다 직관적이다. 모범답안는 list에 넣고 sort하면 정렬이 끝난다. 하지만 나의 풀이는 set에 넣고 iterator를 가져와서 직접 for문을 돌려
정렬한다.
*/
import java.util.*;

class Solution {
    public int[] solution(int N, int[] lastStages) {
        int[] answer = new int[N];
        int[] numberOfAtStage = new int[N+2];
        int numberOfReached;
        double[] percent = new double[N+1];
        TreeSet<Double> set = new TreeSet<Double>();

        for(int stage:lastStages)
            numberOfAtStage[stage]++;

        numberOfReached = numberOfAtStage[N+1];
        for(int i=N; i>=1; i--)
        {
            numberOfReached += numberOfAtStage[i];
            percent[i] = (double)(numberOfAtStage[i])/numberOfReached;
            set.add(percent[i]);
        }
        Iterator<Double> iter = set.descendingIterator();

        int j=0;

        for(int i=0; i<N; i++)
            answer[i] = i+1;

        while(iter.hasNext())
        {
            double target = iter.next();
            for(int i=1; i<=N;i++)
            {
                if(target == percent[i])
                {
                    answer[j] = i;
                    j++;
                }
            }
        }
        return answer;
    }

}