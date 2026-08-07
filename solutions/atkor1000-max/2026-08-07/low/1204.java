import java.util.Scanner;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	public static void main(String args[]) throws Exception
	{

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int tc = sc.nextInt();
			HashMap<Integer, Integer> scores = new HashMap<>();
            for (int i = 0; i < 1000; i++) {
                int score = sc.nextInt();
            	if (!scores.containsKey(score)) scores.put(score, 1);
                else {
                    int t = scores.get(score);
                    scores.replace(score, t, t + 1);
                }
            }
			int mode = -1;
            int m_count = -1;
            for (Map.Entry<Integer, Integer> entry : scores.entrySet()) {
    			Integer key = entry.getKey();
    			Integer value = entry.getValue();
                
                if (value > m_count) {
                    mode = key;
                    m_count = value;
                    //System.out.println(key + &quot; &quot; + value);
                }
                else if (value == m_count) {
                	if (key > mode) mode = key;
                }
			}
			System.out.println("#" + test_case + " " + mode);
		}
	}
}