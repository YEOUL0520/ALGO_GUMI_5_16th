/*
25985. 숫자열의 최대 곱 (D2)
https://swexpertacademy.com/main/code/userProblem/userProblemDetail.do?contestProbId=AZvmEUAqG6LHBIQE&categoryId=AZvmEUAqG6LHBIQE&categoryType=CODE
*/

import java.util.Scanner;

class Solution {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();

    for (int t = 1; t <= T; t++) {
      int N = sc.nextInt();
      int M = sc.nextInt();

      int[] A = new int[N];
      for (int n = 0; n < N; n++) {
        A[n] = sc.nextInt();
      }

      int[] B = new int[M + 2 * (N - 1)];
      for (int m = N - 1; m < M + N - 1; m++) {
        B[m] = sc.nextInt();
      }

      int max = Integer.MIN_VALUE;

      for (int s = 0; s < M + N - 1; s++) {
        int result = 0;
        
        for (int i = 0; i < N; i++) {
          result += A[i] * B[i + s];
        }

        if (max < result) {
          max = result;
        }
      }

      System.out.println("#" + t + " " + max);
    }
  }
}
