import java.util.Scanner;
import java.io.FileInputStream;

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
		T=10;
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int tc = sc.nextInt();
			
            int[] rowsums = new int[100];
            int[] colsums = new int[100];
            int diagsum = 0;
            int backdiagsum = 0;
            
            for (int i = 0; i < 100; i++) {
            	for (int j = 0; j < 100; j++) {
                	int num = sc.nextInt();
                    rowsums[i] += num;
                    colsums[j] += num;
                    if (i == j) diagsum += num;
                    if (i + j == 99) backdiagsum += num;
                }
            }
            
            int max = -1;
            if (diagsum > max) max = diagsum;
            if (backdiagsum > max) max = backdiagsum;
            for (int i = 0; i < 100; i++) {
            	if (rowsums[i] > max) max = rowsums[i];
                if (colsums[i] > max) max = colsums[i];
            }
            
            System.out.println("#" + tc + " " + max);

		}
	}
}