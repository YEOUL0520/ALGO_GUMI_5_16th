#include<iostream>
#include<algorithm>
using namespace std;
#define MAX_CHECK 101
#define MAX 1000

int main() {
	int T;
	cin >> T;
	for (int test_case =1; test_case <= T; test_case++) {
		int tc;
		cin >> tc;
		int check[MAX_CHECK] = { 0, };
		for (int i = 0; i < MAX; i++) {
			int input;
			cin >> input;
			check[input]++;
		}

		int result = 0;
		for (int i = MAX_CHECK-1; i >= 0; i--) {
			if (check[i] > check[result]) {
				result = i;
			}
		}

		cout << "#" << tc << " " << result << "\n";
	}

	return 0;
}