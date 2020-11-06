package Programmers_Lv2;

public class OneTwoFourWorld {
    public static void main(String[] args) {
        int n = 44;
        Solution sol = new Solution();

        System.out.println(sol.solution(n));

        return;
    }
    private static class Solution {
        public String solution(int n) {
            String answer = "";
            StringBuilder sb = new StringBuilder();

            while(n != 0) {
                if(n <= 3) {
                    if(n == 3)
                        sb.insert(0, '4');
                    else
                        sb.insert(0, Integer.toString(n));
                    n = 0;
                } else {
                    int num = n / 3;
                    int rem = n % 3;

                    if(rem == 0) {
                        num--;
                        rem = 4;
                    }
                    sb.insert(0, Integer.toString(rem));
                    n = num;
                }
            }
            answer = String.valueOf(sb);
            return answer;
        }
    }
}
