package Programmers.Programmers_Kit_Greedy;

import java.util.*;

public class GymSuit {
    public static void main(String[] args) {
        int n = 3;
        int[] lost = {3};
        int[] reserve = {1};

        Solution sol = new Solution();
        System.out.println(sol.solution(n, lost, reserve));
    }

    private static class Solution {
        public int solution(int n, int[] lost, int[] reserve) {
            int answer = lost.length;
            Arrays.sort(lost);
            Arrays.sort(reserve);

            for(int i = 0; i < lost.length; i++) {
                for(int j = 0; j < reserve.length; j++) {
                    if(lost[i] == reserve[j]) {
                        lost[i] = -1;
                        reserve[j] = -1;
                        answer--;
                    }
                }
            }

            for(int i = 0; i < lost.length; i++) {
                if(lost[i] != -1) {
                    loop:for(int j = 0; j < reserve.length; j++) {
                        if(reserve[j] != -1 && Math.abs(lost[i] - reserve[j]) == 1) {
                            answer--;
                            lost[i] = -1;
                            reserve[j] = -1;
                            break loop;
                        }
                    }
                }
            }
            return n - answer;
        }
    }
}
