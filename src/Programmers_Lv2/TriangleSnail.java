package Programmers_Lv2;

import java.util.Arrays;

public class TriangleSnail {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int n = 5;
        int[] ans = sol.solution(n);
        System.out.println(Arrays.toString(ans));
    }
    private static class Solution {
        public int[] solution(int n) {
            int[] answer;
            int[][] map = new int[n][n + n - 1];

            int y_idx = map[0].length / 2 + 1;
            int x_idx = -1;
            int val = 0;

            while(n > 0) {
                for(int i = 0; i < n; i++) {
                    val++;
                    x_idx++;
                    y_idx--;
                    map[x_idx][y_idx] = val;
                }
                n--;
                if(n == 0) break;
                for(int i = 0; i < n; i++) {
                    y_idx += 2;
                    val++;
                    map[x_idx][y_idx] = val;
                }
                n--;
                if(n == 0) break;
                for(int i = 0; i < n; i++) {
                    val++;
                    y_idx--;
                    x_idx--;
                    map[x_idx][y_idx] = val;
                }
                n--;
                if(n == 0) break;
            }

            answer = new int[val];
            int idx = 0;
            for(int i = 0; i < map.length; i++) {
                for(int j = 0; j < map[i].length; j++) {
                    if(map[i][j] != 0) {
                        answer[idx] = map[i][j];
                        idx++;
                    }
                }
            }
            return answer;
        }
    }
}
