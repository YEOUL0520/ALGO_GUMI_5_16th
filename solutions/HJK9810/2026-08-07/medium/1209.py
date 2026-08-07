import sys
from pathlib import Path
input_path = Path(__file__).with_name("input.txt")
sys.stdin = open(input_path, "r", encoding="utf-8")

DEFAULT_SIZE = 100

def algorithm():
    boards = [list(map(int, input().split())) for _ in range(DEFAULT_SIZE)]

    row_sums = [sum(row) for row in boards]
    col_sums = [sum(col) for col in zip(*boards)]

    cross_sum = sum([boards[idx][idx] for idx in range(DEFAULT_SIZE)])
    reverse_cross_sum = sum([boards[DEFAULT_SIZE - idx - 1][DEFAULT_SIZE - idx - 1] for idx in range(DEFAULT_SIZE)])

    row_max = max(row_sums)
    col_max = max(col_sums)
    cross_max = max(cross_sum, reverse_cross_sum)

    return max(max(row_max, col_max), cross_max)

# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for _ in range(10):
    test_case = int(input())
    print(f"#{test_case} {algorithm()}")
