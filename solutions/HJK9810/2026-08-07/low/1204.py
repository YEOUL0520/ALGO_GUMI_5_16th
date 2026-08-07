DEFAULT_VALUE = 100

def algorithm():
    scores = list(map(int, input().split()))
    counts = [0] * (DEFAULT_VALUE + 1)
    max_score, max_count = scores[0], 1

    for score in scores:
        counts[score] += 1
        score_count = counts[score]
        if max_count < score_count:
            max_score = score
            max_count = score_count
        elif max_count == score_count and max_score < score:
            max_score = score

    return max_score

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for _ in range(T):
    test_case = int(input())
    print(f"#{test_case} {algorithm()}")
