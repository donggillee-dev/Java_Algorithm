package Programmers_Lv2;

import java.util.Arrays;
public class PickingGround {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] land = {{1,2,3,5}, {5,6,7,8}, {4,3,2,1}};
        System.out.println("answer : " + sol.solution(land));
    }
    private static class Solution {

        int solution(int[][] land) {
            int answer = Integer.MIN_VALUE;
            int[][] DP = new int[land.length][4];

            for(int i = 0; i < land.length; i++) {
                DP[i] = Arrays.copyOf(land[i], land[i].length);
            }

            for(int i = 0; i < DP.length - 1; i++) {
                for(int j = 0; j < DP[i].length; j++) {
                    for(int k = 0; k < DP[i].length; k++) {
                        if(( j != k) && (DP[i + 1][k] < DP[i][j] + land[i + 1][k])) {
                            DP[i + 1][k] = DP[i][j] + land[i + 1][k];
                            answer = Math.max(answer, DP[i + 1][k]);
                        }
                    }
                }
            }
            return answer;
        }
    }
}
