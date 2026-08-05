import java.util.ArrayList;
import java.util.Scanner;

class Solution {

    static Scanner sc = new Scanner(System.in);
    
    static int [][] data = new int[100][100]; //사다리
    static int loc = 0; //현재 위치한 세로 막대

    public static void main(String[] args) {
        for(int tc = 1; tc <= 10; tc++){
            int testCase;
            testCase = sc.nextInt();

            ArrayList<Integer> rail = new ArrayList<>(); //사다리의 레일 위치

            for(int i = 0; i < 99; i++){
                for(int j = 0; j < 100; j++){
                    data[i][j] = sc.nextInt();
                }
            }
            
            for(int j = 0; j < 100; j++){ //마지막 행에서 레일 위치 및 도착지 파악
                data[99][j] = sc.nextInt();
                
                if(data[99][j] != 0){
                    rail.add(j);
                }
                if(data[99][j] == 2){
                    loc = j; //도착지 레일 설정
                }
            }

            for(int i = 99; i > 0; i--){ //레일 이동
                if(checkLeft(loc, i)){
                    loc = rail.get(rail.indexOf(loc) - 1);
                    
                }else if(checkRight(loc, i)){
                    loc = rail.get(rail.indexOf(loc) + 1);
                }
            }
            System.out.println("#" + testCase + " " + loc);
        }
    }

    static boolean checkLeft(int loc, int i){
        if(loc == 0)
            return false;
        return (data[i][loc - 1] == 1);
    }

    static boolean checkRight(int loc, int i){
        if(loc == 99)
            return false;
        return (data[i][loc + 1] == 1);
    }
}