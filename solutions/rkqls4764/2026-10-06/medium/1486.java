/*
1486. 장훈이의 높은 선반 (D4)
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV2b7Yf6ABcBBASw&categoryId=AV2b7Yf6ABcBBASw&categoryType=CODE&problemTitle=1486&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
*/

import java.io.*;
import java.util.*;

class Solution {
  public static int N;
  public static int B;
  public static int[] H;
  public static int min;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;
    int T = Integer.parseInt(br.readLine());

    for (int t = 1; t <= T; t++) {
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken()); // 점원 수
      B = Integer.parseInt(st.nextToken()); // 선반 높이

      H = new int[N];
      st = new StringTokenizer(br.readLine());
      for (int n = 0; n < N; n++) {
        H[n] = Integer.parseInt(st.nextToken());
      }

      min = Integer.MAX_VALUE;

      combination(0, 0);

      bw.write("#" + t + " " + (min - B) + "\n");
    }

    bw.flush();
  }

  public static void combination(int idx, int sum) {
    if (idx == N) {
      if (sum >= B) {
        min = Math.min(min, sum);
      }
      return;
    }

    if (min < sum) {
      return;
    }

    combination(idx + 1, sum + H[idx]);
    combination(idx + 1, sum);
  }
}
