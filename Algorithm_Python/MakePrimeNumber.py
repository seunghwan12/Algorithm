# nums에서 3개의 수를 더했을 때, 소수가 되는 경우의 수를 구한다.

# 1. combination
# 2. 조합으로 구해진 3개의 수의 합이 소수가 되는지 확인

from itertools import combinations
import math

def solution(nums):
    answer = 0
    for three in list(combinations(nums, 3)):
        isPrime = True
        num = sum(three)
        for idx in range(2, int(math.sqrt(num))+1):
            if num % idx == 0:
                isPrime = False
                break
        if isPrime:
            answer += 1

    return answer