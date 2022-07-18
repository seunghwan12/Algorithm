def solution(board, moves):
    basket = []
    answer = 0
    # show(board)
    for num in moves:
        doll = pick(num, board)
        # print(doll, basket[-1] if basket else "빈 바구니")
        if doll != 0:
            if len(basket) == 0 or basket[-1] != doll:
                basket.append(doll)
            else:
                answer += 2
                basket.pop()

    return answer


# 정사각 격자에서 크레인으로 인형을 뽑는 함수
def pick(num, board):
    answer = 0
    ht = len(board)

    for pos in range(ht):
        if board[pos][num - 1] != 0:
            answer = board[pos][num - 1]
            board[pos][num - 1] = 0
            break

    return answer


def show(board):
    size = len(board)
    for i in range(size):
        for j in range(size):
            print(board[i][j], end=" ")
        print()