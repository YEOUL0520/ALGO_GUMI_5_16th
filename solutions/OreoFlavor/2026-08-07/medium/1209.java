import java.util.Scanner;
//1209
class Solution {
  static Scanner sc = new Scanner(System.in);
  public static void main(String[] args) {
    for(int tc = 0; tc < 10; tc++) {
      int testCase = sc.nextInt();
      
      int[] row = new int[100]; //행의 합
      int[] col = new int[100]; //열의 합
      int lr = 0; // 좌상단 -> 우하단 합
      int rl = 0; // 우상단 -> 좌하단 합

      int biggest = 0; //가장 큰 수

      int input = 0; //각 입력
      for(int i = 0; i < 100; i++) {
        for(int j = 0; j < 100; j++) {
          input = sc.nextInt();
          row[i] += input;
          col[j] += input;
          if(i == j) {
            lr += input;
          }
          if((i + j) == 99) {
            rl += input;
          }
        }
      }

      for(int i = 0; i < 100; i++) {
        if(row[i] > biggest) {
          biggest = row[i];
        }
        if(col[i] > biggest) {
          biggest = col[i];
        }
      }
      if(lr > biggest) {
        biggest = lr;
      }
      if(rl > biggest) {
        biggest = rl;
      }

      System.out.println("#" + testCase + " " + biggest);
    }
  }
}
