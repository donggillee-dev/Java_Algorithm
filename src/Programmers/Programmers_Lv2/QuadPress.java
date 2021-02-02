package Programmers.Programmers_Lv2;

import java.util.Arrays;

public class QuadPress {
    public static void main(String[] args) {
        int[][] arr = {{1,1,0,0},{1,0,0,0},{1,0,0,1},{1,1,1,}};
        Solution sol = new Solution();

        int[] ans = sol.solution(arr);

        System.out.println(Arrays.toString(ans));

        return;
    }
    private static class Solution {
        static int zeroNum = 0;
        static int oneNum = 0;

        public int[] solution(int[][] arr) {
            int[] answer = new int[2];

            div(arr, 0, arr.length, 0, arr.length);

            answer[0] = zeroNum;
            answer[1] = oneNum;

            return answer;
        }
        public void div(int[][] arr, int start_x, int end_x, int start_y, int end_y) {
            int num = arr[start_x][start_y];
            boolean flag = true;

            loop:for(int i = start_x; i < end_x; i++) {
                for(int j = start_y; j < end_y; j++) {
                    if(arr[i][j] != num) {
                        flag = false;
                        break loop;
                    }
                }
            }

            if(!flag) {
                int mid_x = (start_x + end_x) / 2;
                int mid_y = (start_y + end_y) / 2;

                div(arr, start_x, mid_x, start_y, mid_y); //2사분면
                div(arr, start_x, mid_x, mid_y, end_y); //1사분면
                div(arr, mid_x, end_x, start_y, mid_y); //3사분면
                div(arr, mid_x, end_x, mid_y, end_y); //4사분면
            } else {
                if(num == 1) oneNum++;
                else zeroNum++;
            }
            return;
        }
    }
}
