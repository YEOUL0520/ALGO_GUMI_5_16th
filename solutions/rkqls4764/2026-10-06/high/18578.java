/*
18578. Gravity_실습 (D8)
https://swexpertacademy.com/main/code/userProblem/userProblemDetail.do?contestProbId=AYodeWvqwdIDFARi&categoryId=AYodeWvqwdIDFARi&categoryType=CODE
*/

import java.io.*;
import java.util.*;

class Solution {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st;
    int T = Integer.parseInt(br.readLine());

    for (int t = 1; t <= T; t++) {
      int length = Integer.parseInt(br.readLine());
      int[] height = new int[length];

      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < length; i++) {
        height[i] = Integer.parseInt(st.nextToken());
      }

      int answer = Integer.MIN_VALUE;
      int distance = 0;
      int max = 0;

      for (int i = 0; i < length; i++) {
        if (max < height[i]) {
          answer = Math.max(answer, distance);
          distance = 0;
          max = height[i];
        } else if (max > height[i]) {
          distance++;
        }
      }

      answer = Math.max(answer, distance);

      bw.write("#" + t + " " + answer + "\n");
    }

    bw.flush();
  }
}
