package Programmers_Kit_ExhaustiveSearch;

import java.util.Arrays;

public class Carpet {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int brown = 24, yellow = 24;

        int[] answer = sol.solution(brown, yellow);

        System.out.println(Arrays.toString(answer));
    }
    private static class Solution {
        public int[] solution(int brown, int yellow) {
            int[] answer = new int[2];

            loop1:for(int i = 3; i <= Integer.MAX_VALUE; i++) {
                for(int j = 3; j <= i; j++) {
                    int cnt_brown = 2 * i + (j - 2) * 2;
                    int cnt_yellow = (i - 2) * (j - 2);
                    if(cnt_brown == brown && cnt_yellow == yellow) {
                        answer[0] = i;
                        answer[1] = j;
                        break loop1;
                    }
                }
            }
            return answer;
        }
    }
}
