
def solution(tickets):
    num = len(tickets)
    path = []
    stack = []

    # 출발지 - [도착지1, 도착지2, ....] 딕셔너리로 인접리스트 자료구조 생성
    depart_to_dest = {}
    for ticket in tickets:
        if ticket[0] in depart_to_dest:
            depart_to_dest[ticket[0]].append(ticket[1])
            depart_to_dest[ticket[0]].sort(reverse=True)
        else:
            depart_to_dest[ticket[0]] = [ticket[1]]

    stack.append("ICN")

    # DFS로 주어진 항공권을 모두 사용하는 경로 찾기
    while stack:
        cur = stack[-1]
        if cur in depart_to_dest and depart_to_dest[cur]:
            stack.append(depart_to_dest[cur].pop())

        # 경로의 역순으로 path에 도시들을 저장
        else:
            path.append(stack.pop())

    # path를 reverse시켜 정상적인 경로를 만듬
    answer = path[::-1]
    return answer

if __name__ == '__main__':
    tickets = [["ICN", "A"], ["ICN", "B"], ["B", "ICN"]]
    returns = ["ICN", "B", "ICN", "A"]
    solution1 = solution(tickets)
    print(solution1)
