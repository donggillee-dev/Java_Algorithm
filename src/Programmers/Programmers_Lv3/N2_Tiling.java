package Programmers.Programmers_Lv3;

public class N2_Tiling {

    public static void main(String[] args) {
        int n = 60000;
        Solution sol = new Solution();
        System.out.println(sol.solution(n));
    }
    private static class Solution {
        public int solution(int n) {
            int answer = 0;
            if(n == 1) return 1;
            else if(n == 2) return 2;

            int a = 1;
            int b = 2;

            for(int i = 0; i < n-2; i++) {
                answer = (a + b) % 1000000007;
                a = b;
                b = answer;
            }
            return answer;
        }
    }
}
