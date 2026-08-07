import java.util.Scanner;
import java.io.FileInputStream;
import java.util.HashSet;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
   	public static String h2b (String hex) {
    	StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < hex.length(); i++) {
        	char c = hex.charAt(i);
            
            int val;
        	if ('0' <= c && c <= '9') {
            	val = c - '0';
        	} else {
            	val = c - 'A' + 10;
        	}
            
            for (int bit_digit = 3; bit_digit >= 0; bit_digit--) {
            	if ((val & (1 << bit_digit)) != 0) result.append('1');
                else result.append('0');
            }
        }
        
        return result.toString();
    }
    
    public static int eval(String s) {
        String[] code_array = new String[8];
        for (int i = 0; i < 8; i++) {
        	code_array[i] = s.substring(7 * i, 7 * i + 7);
        }
        
        int sum = 0;
        int answer = 0;
        Boolean anomaly = false;
        for (int i = 0; i < 8; i++) {
            int num = 0;
            if (code_array[i].equals("0001101")) num = 0;
            else if (code_array[i].equals("0011001")) num = 1;
            else if (code_array[i].equals("0010011")) num = 2;
            else if (code_array[i].equals("0111101")) num = 3;
            else if (code_array[i].equals("0100011")) num = 4;
            else if (code_array[i].equals("0110001")) num = 5;
            else if (code_array[i].equals("0101111")) num = 6;
            else if (code_array[i].equals("0111011")) num = 7;
            else if (code_array[i].equals("0110111")) num = 8;
            else if (code_array[i].equals("0001011")) num = 9;
            else {
                anomaly = true;
                break;
            }
            if (anomaly) return -2;
            if (i % 2 == 0) sum += 3 * num;
            else sum += num;
            answer += num;
        }
        if (sum % 10 == 0) return answer;
        else return -1;
        
    }
    
    
    
   
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
            int N = sc.nextInt();
            int M = sc.nextInt();
            
            HashSet<String> candidates = new HashSet<>();
            
            for (int i = 0; i < N; i++) { //for each row
                String signal = sc.next();
                
                String signal_bit = h2b(signal);
                //System.out.println(signal_bit.length());
                //System.out.println(signal_bit);
                
            	for (int bit = 4 * M - 1; bit >= 0; bit--) {
                    
            		if (signal_bit.charAt(bit) == '1') { //start searching!
                        //System.out.println(1);
                    	int search_len = 56;
                        int factor = 1;
                        while (bit - search_len >= 0) {
                            Boolean found = false;
                            for (int j = bit;
                                 j >= bit - search_len + 1 + factor * 2; //the first bit (a) cannot go further than the search area.
                                 j -= factor) { //since the stripes will be factor bits wide
                            	
                                int a = 1 << factor;
                                int b = 1 << factor;
                                int c = 1 << factor;
                                
								for (int l = j; l > j - factor; l--) {
                                	if (signal_bit.charAt(l) == '1') a >>= 1;
                                    else if (signal_bit.charAt(l) == '0') a <<= 1;
                                }
                                for (int l = j - factor; l > j - factor * 2; l--) {
                                	if (signal_bit.charAt(l) == '1') b >>= 1;
                                    else if (signal_bit.charAt(l) == '0') b <<= 1;
                                }
                                for (int l = j - factor * 2; l > j - factor * 3; l--) {
                                	if (signal_bit.charAt(l) == '1') c >>= 1;
                                    else if (signal_bit.charAt(l) == '0') c <<= 1;
                                }
                                
                                if (!((a == 1 << (factor * 2) || a == 1) &&
                                      (b == 1 << (factor * 2) || b == 1) &&
                                      (c == 1 << (factor * 2) || c == 1))) break;
                                
                                if ((a == 1 << (factor * 2) && b == 1 && c == 1 << (factor * 2) || 
                                     (a == 1 && b == 1 << (factor * 2) && c == 1))) {
                                //this assumes that there cannot be a sequence that when numbers are
                                //connected, does not provide a factor-wide strip.
                                //And there can't be.
                                	//search is over! fill found_signal
                                    int fsp = 55;
                                    char[] found_signal = new char[56];
                                    for (int ptr = bit; ptr > bit - search_len; ptr -= factor) {
                                        found_signal[fsp] = signal_bit.charAt(ptr);
                                        fsp--;
                                    }
                                   	String fs = new String(found_signal);
                                    candidates.add(fs);
                                    found = true;
                                    bit -= search_len;
                                    break;
                                }
                                else continue;
                            }
                            if (found) break;
                            else {
                                factor++;
                                search_len += 56;
                            }
                        }         
                    }
            	}
            } //big for loop over
			
            
            int answer = 0;
            
            for (String candidate : candidates) {
                //System.out.println(candidate);
    			int sum = eval(candidate);
                if (sum == -1) continue;
                else if (sum == -2) {
                    answer = -2;
                    break;
                }
                else answer += sum;
			}
            
            System.out.println("#" + test_case + " " + answer);
		}
	}
}