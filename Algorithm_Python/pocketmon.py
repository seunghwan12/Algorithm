# 중복된 종류로 구성된 포켓몬 n개에서 n/2개의 포켓몬을 선택
# 최대한 다양한 종류의 포켓몬을 선택 -> 종류의 수를 max

# hash 자료구조를 선택하여 포켓몬 종류의 수를 파악한다.
# 포켓몬의 종류의 개수 = num_of_keys
# num_of_keys가 n/2개 이상이면, answer = n/2
# num_of_keys가 n/2개 이하이면, answer = num_of_keys

def solution(nums):
    dict = {}
    for pocketmon in nums:
        dict[pocketmon] = dict.get(pocketmon, 0) + 1

    num_of_keys = len(dict.keys())

    answer = min(num_of_keys, len(nums)/2)

    return answer