package Programmers.Programmers_Kit_ExhaustiveSearch;

import java.util.*;

public class SemiTest {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] answer = {1,2,3,4,5};
        System.out.println(Arrays.toString(sol.solution(answer)));
    }
    private static class Solution {
        public int[] solution(int[] answers) {
            int[] answer;
            int[] P1 = {1, 2, 3, 4, 5};
            int[] P2 = {2, 1, 2, 3, 2, 4, 2, 5};
            int[] P3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
            int[] score = new int[3];
            int j = 0, k = 0, l = 0;
            int max = Integer.MIN_VALUE;
            List<Integer> list = new ArrayList<Integer>();

            for(int i = 0; i < answers.length; i++) {
                if(P1[j] == answers[i]) score[0]++;
                if(P2[k] == answers[i]) score[1]++;
                if(P3[l] == answers[i]) score[2]++;
                j++;
                k++;
                l++;
                if(j >= P1.length) j = 0;
                if(k >= P2.length) k = 0;
                if(l >= P3.length) l = 0;
            }
            max = Math.max(score[0], Math.max(score[1], score[2]));
            for(int i = 0; i < score.length; i++) {
                if(max == score[i]) {
                    list.add(i + 1);
                }
            }
            Collections.sort(list);
            return list.stream().mapToInt(i -> i.intValue()).toArray();
        }
    }
}
