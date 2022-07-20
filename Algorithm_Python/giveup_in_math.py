def solution(answers):
    answer_of_students = [[], [], []]
    number_of_right_answer = []
    answer = []

    # 각 학생의 정답지 계산
    second_dict = {1: 1, 3: 3, 5: 4, 7: 5}
    third_dict = {0: 3, 1: 1, 2: 2, 3: 4, 4: 5}
    for idx in range(len(answers)):
        answer_of_students[0].append((idx % 5) + 1)
        answer_of_students[1].append(2 if idx % 2 == 0 else second_dict[idx % 8])
        answer_of_students[2].append(third_dict[(int)((idx % 10) / 2)])

    # 각 학생의 맞힌 횟수 구하기
    for each_answer in answer_of_students:
        num = 0
        for idx in range(len(each_answer)):
            if each_answer[idx] == answers[idx]:
                num += 1
        number_of_right_answer.append(num)

    # print(number_of_right_answer)

    # 세 학생 중에서 가장 높은 점수를 구하고
    # 가장 많이 맞힌 사람을 찾아 배열에 담기
    max_value = max(number_of_right_answer)
    for idx in range(len(number_of_right_answer)):
        if number_of_right_answer[idx] == max_value:
            answer.append(idx + 1)
    return answer

if __name__ == '__main__':
    answers = [1,2,3,4,5]
    solution(answers)