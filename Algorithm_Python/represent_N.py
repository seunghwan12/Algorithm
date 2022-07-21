"""
12 = 5 + 5 + (5 / 5) + (5 / 5)는 5, 5+(5/5)+(5/5)로 분해될 수도 있고,
5+5, (5+5)+(5/5)로도 분해될 수 있다. 즉, 5가 6개 사용된 12는 5개 1번 사용된 5와 5가 5번 사용된
5+(5/5)+(5/5)로 분해될 수 있다는 것이다.

이를 다시 생각해보면, 5가 m+n번 사용되어 표현된 숫자는 5가 m번 사용된 숫자와 5가 n번 사용된 숫자로
구할 수 있다는 것이다. 따라서 5가 1번 사용되어 표현된 숫자를 구하고, 이를 통해 2번, 3번, ... 8번 사용된 숫자들을
구할 수 있다.
"""

def solution(N, number):
    answer = -1
    arr = [[]] + [[(int)(str(N) * idx)] for idx in range(1, 9)]
    if number in arr[1]:
        return 1
    for idx in range(2, 9):
        for inner_idx in range(1, idx):
            each_list = multiply(arr[inner_idx], arr[idx - inner_idx])
            arr[idx].extend(each_list)

        # 리스트 안의 중복된 원소를 제거
        arr[idx] = list(set(arr[idx]))
        if number in arr[idx]:
            answer = idx
            break
    return answer


def multiply(list1, list2):
    answer = []
    for a in list1:
        for b in list2:
            answer.append(a + b)
            answer.append(a - b)
            if b != 0:
                answer.append(a // b)
            answer.append(a * b)

    return answer