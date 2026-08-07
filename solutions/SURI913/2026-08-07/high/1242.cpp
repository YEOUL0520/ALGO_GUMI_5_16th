#include <iostream>
#include <cstring>
#include <bitset> 
#include <algorithm>
#include <set>
#include <array>

using namespace std;

int code[10][4] = {
	{3, 2, 1, 1}, // 0
	{2, 2, 2, 1}, // 1
	{2, 1, 2, 2}, // 2
	{1, 4, 1, 1}, // 3
	{1, 1, 3, 2}, // 4
	{1, 2, 3, 1}, // 5
	{1, 1, 1, 4}, // 6
	{1, 3, 1, 2}, // 7
	{1, 2, 1, 3}, // 8
	{3, 1, 1, 2}  // 9
};

int CheckCode(int c1, int c2, int c3) {

	for (int n = 0; n < 10; n++) {
		if (code[n][1] == c1 &&
			code[n][2] == c2 &&
			code[n][3] == c3) {

			return n;
		}
	}

	return -1;
}

int ASCIIToHex(int ascii) {

	//아스키 숫자변환
	if ('0' <= ascii && ascii <= '9') return ascii - '0';
	return ascii - 'A' + 10;
	//+10은 16진수에서 A가 숫자 10부터 시작하기 때문

}

int CountBinaryToCode( const string& binary, int& index, char checkVal) {
	int cnt = 0;
	while (index >= 0 && binary[index] == checkVal) {
		cnt++;
		index--;
	}

	return cnt;
}

//앞에 [0][n] 의 숫자는 0일꺼라 안세어봐도 됨
int main() {
	int T;
	cin >> T;

	for (int test_case = 1; test_case <= T; test_case++) {
		int N, M;
		int result = 0;
		string input;
		set<array<int, 8>> usedCodes; //같은 코드면 계산 X 반복

		cin >> N>>M; //암호문 길이

		for (int row = 0; row < N; row++) {
			string binary, input;

			cin >> input; 
			binary.reserve(M * 4); //재할당 방지

			for (const auto& ascii : input) {
				int hex = ASCIIToHex(ascii);

				//값을 2진수로 변환해줌
				binary += bitset<4>(hex).to_string();
			}
			//M*4가 전체 비트맵?길이
			//맨뒷자리 항상 1이니까 뒤에서부터 1 찾아서 비율 찾을 것
			int rightToLeft = M * 4 - 1;

			while (rightToLeft >= 0)
			{
				// 암호 밖의 0 무시
				CountBinaryToCode(binary, rightToLeft, '0');

				if (rightToLeft < 0) break; //-1 암호 없음

				array<int, 8> number{};

				for (int i =  7; i >= 0; i--) {
					//숫자도 뒤에서 부터 저장
					int c3 = CountBinaryToCode(binary, rightToLeft, '1');
					int c2 = CountBinaryToCode(binary, rightToLeft, '0');
					int c1 = CountBinaryToCode(binary, rightToLeft, '1');

					int ratio = min({ c1, c2, c3 }); //가장 작은값이 1로 처리 될거니까

					c1 /= ratio;
					c2 /= ratio;
					c3 /= ratio;

					//이제 검증?
					number[i] = CheckCode(c1, c2, c3);
					CountBinaryToCode(binary, rightToLeft, '0'); //다음 number를 위해 0제거
				}
				// number찾기 완 이제 찾아둔 code번호인지 확인
				if (usedCodes.find(number) != usedCodes.end()) {
					continue;
				}

				usedCodes.insert(number);

				//이제 값이 10의 배수인지 찾자
				//홀수자리 다 더해서 *3 +짝수자리 더하기
				//뭐시기 코드 합 =>10배수
				//솔직히 8자리 고정일텐데 굳이 포문돌릴필요있을까? 없는게 더 직관적이지 않음?
				int check = (number[0] + number[2] + number[4] + number[6]) * 3
					+ number[1] + number[3] + number[5] + number[7];

				if (check % 10 == 0) {
					// 정상적인 암호코드 더한값 출력
					for (const auto& num : number) {
						result += num;
					}
				}
			}
			
		}

		cout << '#' << test_case << ' ' << result << endl;
	}
	return 0;
}