import re

import re

# 프로그래머스, level1, 신규 아이디 추천

def FirstSolution(new_id):
    # 1단계
    first = new_id.lower()
    # print("first: " +first)

    # 2단계
    second = ""
    for idx in range(len(first)):
        ch = first[idx]
        if ch.islower() or ch.isnumeric() or ch == '-' or ch == '_' or ch == '.':
            second += ch
    # print("second: " +second)

    # 3단계
    third = re.sub("\.+", ".", second)
    # print("third: " +third)

    # 4단계
    fourth = third
    if third[0] == '.':
        fourth = third[1:]
        # print("맨앞 . 제거")
    if len(fourth) > 0 and fourth[len(fourth) - 1] == '.':
        fourth = fourth[:-1]
        # print("맨뒤 . 제거")

    # print("fourth: " +fourth)

    # 5단계
    fifth = "a" if len(fourth) == 0 else fourth
    # print("fifth: " +fifth)

    # 6단계
    sixth = fifth
    if len(fifth) >= 16:
        sixth = fifth[:15]

        if sixth[len(sixth) - 1] == '.':
            sixth = sixth[:-1]
    # print("sixth: " +sixth)

    # 7단계
    ch = sixth[len(sixth) - 1]
    seventh = sixth
    if len(sixth) <= 2:
        for i in range(3 - len(sixth)):
            seventh += ch
    # print("seventh: " +seventh)

    return seventh

def SecondSolution(new_id):

    answer = new_id.lower()
    answer = re.sub("[^a-z0-9\-\_\.]", "", answer)
    answer = re.sub("\.+", ".", answer)
    answer = re.sub("^[.]|[.]$", "", answer)
    if len(answer) == 0:
        answer = "a"
    if len(answer) >= 16:
        answer = answer[:14] if answer[14] == '.' else answer[:15]
    answer += "" if len(answer) > 2 else "".join([answer[-1] for i in range(3 - len(answer))])
    return answer

if __name__ == '__main__':
    str = SecondSolution('...!@BaT#*..y.abcdefghijklm')
    print()
    print("result:" + str)
