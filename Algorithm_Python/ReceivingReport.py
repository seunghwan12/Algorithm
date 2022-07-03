def solution(id_list, report, k):
    answer = [0] * len(id_list)
    reports = {x: 0 for x in id_list}
    strList = []

    # set으로 만들어 신고자, 불량이용자의 조합이 중복되는 경우를 방지한다.
    for str in set(report):
        strList.append(str.split())

    # 불량이용자의 신고횟수를 계산한다.
    for r in strList:
        reports[r[1]] += 1

    # 처리결과메일을 받은 횟수를 계산한다.
    for r in strList:
        if reports[r[1]] >= k:
            answer[id_list.index(r[0])] += 1

    return answer

if __name__ == '__main__':
    result = solution(["con", "ryan"], ["ryan con", "ryan con", "ryan con", "ryan con"], 3);
    print()
    print("result")
    for arg in result:
        print(arg)