package Programmers.Programmers_Lv2;

public class JumpAndTP {
    public static void main(String[] args) {
        System.out.println(Solution.solution(5));
    }
    private static class Solution {
        private static int solution(int num) {
            int answer = 0;

            while(num > 0) {
                if(num % 2 == 1) answer++;
                num /= 2;
            }

            return answer;
        }
    }
}
