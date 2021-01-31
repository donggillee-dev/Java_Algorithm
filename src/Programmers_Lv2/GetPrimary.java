package Programmers_Lv2;

public class GetPrimary {
    public static void main(String[] args) {
        int[] nums = {1, 2, 7, 6, 4};
        System.out.println(Solution.solution(nums));
    }
    private static class Solution {
        private static int solution(int[] nums) {
            int answer = 0;

            for(int i = 0; i < nums.length - 2; i++) {
                for(int j = i + 1; j < nums.length - 1; j++) {
                    for(int k = j + 1; k < nums.length; k++) {
                        if(isPrime(nums[i] + nums[j] + nums[k])) answer++;
                    }
                }
            }

            return answer;
        }
        private static boolean isPrime(int sum) {
            int cnt = 0;
            for(int i = 1; i <= (int)Math.sqrt(sum); i++) {
                if(cnt >= 2) return false;
                if(sum % i == 0) cnt++;
            }

            return cnt == 1;
        }
    }
}