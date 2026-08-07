import java.util.Scanner;

class Solution {
  
  static Scanner sc = new Scanner(System.in);
  public static void main(String[] args) {
    int testCase = sc.nextInt();
    
    for(int tc = 1; tc <= testCase; tc++){
      int testNum;
      int[] num = new int[101];
      int biggest = 0;
      int result = 0;

      testNum = sc.nextInt();
      for(int i = 0; i < 1000; i++){
        num[sc.nextInt()]++;
      }

      for(int i = 0; i < num.length; i++){
        if(biggest <= num[i]){
          biggest = num[i];
          result = i;
        }
      }

      System.out.println("#" + testNum + " " + result);
    }
  }
}
