def solution(n, lost, reserve):
    student = [0 if idx == 0 else 1 for idx in range(n + 1)]
    
    lost.sort()
    reserve.sort()

    # lost and no reserve: 0
    # lost and reserve, no lost and no reserve: 1
    # no lost and reserve: 2
    for each_lost in lost:
        student[each_lost] = 0
    for each_reserve in reserve:
        student[each_reserve] += 1

    # 여벌 체육복을 가져오고 도난당하지 않은 학생만 체육복을 빌려주도록
    # reserve에서 학생번호를 선택하고 학생이 가진 체육복이 2개인 경우에만
    # block 내부 로직 실행
    for each_reserve in reserve:
        if student[each_reserve] == 2:
            if each_reserve - 1 >= 1 and student[each_reserve - 1] == 0:
                student[each_reserve - 1] = 1
                student[each_reserve] -= 1
            elif each_reserve + 1 <= n and student[each_reserve + 1] == 0:
                student[each_reserve + 1] = 1
                student[each_reserve] -= 1

    answer = len(list(filter(lambda number: number >= 1, student)))
    return answer