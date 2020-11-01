package Programmers_Kit_ExhaustiveSearch;
import java.util.*;

public class FindPrime {
    public static void main(String[] args) {
        String numbers = "011";

        Solution sol = new Solution();
        System.out.println( sol.solution(numbers) );

        return;
    }
    private static class Solution {
        static int answer;
        static boolean[] visited;
        static Stack<Integer> S = new Stack<>();
        public int solution(String numbers) {
            visited = new boolean[numbers.length()];

            DFS(0, "", numbers);
            S.clear();

            return answer;
        }
        public void DFS(int idx, String str, String numbers) {
            if(str.length() >= 1 && str.charAt(0) != '0') {
                int num = Integer.parseInt(str);
                if(num >= 2 && !S.contains(num) && isPrime(num)) {
                    S.push(num);
                    answer++;
                }
            }

            for(int i = 0; i < numbers.length(); i++) {
                if(!visited[i]) {
                    str += numbers.charAt(i);
                    visited[i] = true;
                    DFS(i, str, numbers);
                    visited[i] = false;
                    str = str.substring(0, str.length() - 1);
                }
            }

            return;
        }
        public boolean isPrime(int number) {
            boolean ret = true;

            for(int i = 2; i <= Math.sqrt((double)number); i++) {
                if(number % i == 0) {
                    ret = false;
                    break;
                }
            }
            return ret;
        }
    }
}
