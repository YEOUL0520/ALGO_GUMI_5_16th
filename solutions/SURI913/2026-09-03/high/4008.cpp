#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>
using namespace std;

int inputOperator[4]; // 0:+, 1:-, 2:*, 3:/
vector<int> num;
int N, maxValue, minValue;

void dfs(int index, int result) {
	if (index == N) {
		//최대 최소 체크
		maxValue = max(maxValue, result);
		minValue = min(minValue, result);
		return;
	}
	for (int op = 0; op < 4; op++) {
		//해당 연산자 없으면 넘김
		if (inputOperator[op] == 0) continue;

		inputOperator[op]--; //사용

		if(op == 0)dfs(index + 1, result + num[index]);
		else if (op == 1)dfs(index + 1, result - num[index]);
		else if (op == 2)dfs(index + 1, result * num[index]);
		else if (op == 3)dfs(index + 1, result / num[index]);

		inputOperator[op]++; //되돌림: 백트래킹
		
	}
}

int main()
{
	int T;
	cin >> T;
	for (int test_case = 1; test_case <= T; test_case++) {
		N;
		cin >> N;
		for (int i = 0; i < 4; i++) cin >> inputOperator[i];
		num.resize(N + 1);
		for (int i = 0; i < N; i++)cin >> num[i]; //숫자 입력
        minValue = INT_MAX;
        maxValue = INT_MIN;
		dfs(1, num[0]);

		cout << '#' << test_case << ' ' << maxValue - minValue << endl;


	}
}