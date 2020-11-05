package Programmers_Kit_Greedy;

public class BiggestNumber {
    public static void main(String[] args) {
        String number = "999991119";
        String number2 = "999991120";

        int k = 3;
        Solution sol = new Solution();
        System.out.println(sol.solution(number, k));
    }
    private static class Solution {
        public String solution(String number, int k) {
            StringBuilder sb = new StringBuilder();

            for(int i = 0, index = -1; i < number.length() - k; i++) {
                int max = -1;
                for(int j = index + 1; j <= i + k; j++) {
                    if(max < (number.charAt(j) - '0')) {
                        index = j;
                        max = number.charAt(j) - '0';
                    }
                }
                sb.append(max);
            }
            return sb.toString();
        }
    }
}
