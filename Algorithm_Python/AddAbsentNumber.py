def solution(numbers):
    answer = 0
    for idx in range(10):
        if idx not in numbers:
            answer += idx
    return answer