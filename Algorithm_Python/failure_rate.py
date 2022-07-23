"""
        n번째 스테이지의 실패율
        = (n번째 스테이지의 사용자수) / (n번째 스테이지와 그 이상의 스테이지의 사용자수)
"""

def solution(N, stages):
    number_of_players_in_stage = [0] * (N + 2)
    failure_rate_in_stage = [0] * (N + 1)
    range_list = list(range(len(number_of_players_in_stage)))

    # 각 스테이지에 있는 사용자의 수 계산
    for stage in stages:
        number_of_players_in_stage[stage] += 1

    # 각 스테이지와 그 이상의 스테이지의 사용자 수 계산
    accum_of_plauers_in_stage = list(map(lambda x: sum(number_of_players_in_stage[x:]), range_list))

    # 각 스테이지의 실패율 계산 + 분모가 0인 경우는 0으로 대체
    for idx in range(1, N + 1):
        failure_rate_in_stage[idx] = number_of_players_in_stage[idx] / accum_of_plauers_in_stage[idx] if \
        accum_of_plauers_in_stage[idx] != 0 else 0

    # 실패율 리스트의 값에 따라 스테이지의 번호를 정렬
    # 파이썬 sort(), sorted() 메소드는 안정 정렬(stable)이므로 중복된 키에 대하여 순서대로 정렬한다.
    answer = range_list[1:-1]
    answer.sort(key=lambda x: failure_rate_in_stage[x], reverse=True)
    return answer