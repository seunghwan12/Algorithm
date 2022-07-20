import heapq

def solution(scoville, K):
    answer = 0
    heapq.heapify(scoville)

    while scoville[0] < K:
        if len(scoville) == 1:
            answer = -1
            break
        else:
            smallest = heapq.heappop(scoville)
            next_smallest = heapq.heappop(scoville)
            heapq.heappush(scoville, smallest + next_smallest*2)
            answer += 1
    return answer