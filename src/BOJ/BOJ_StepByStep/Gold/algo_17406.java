package BOJ.BOJ_StepByStep.Gold;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class algo_17406 {
    private static class Info {
        int r;
        int c;
        int s;
        public Info(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
    static int[][] arr, cpy_arr;
    static int K, answer = Integer.MAX_VALUE;
    static ArrayList<Info> list = new ArrayList<>();
    static boolean[] visited;
    static int[] nums;
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(stk.nextToken());
        int col = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken());
        arr = new int[row][col];
        cpy_arr = new int[row][col];

        //입력부
        for(int i = 0; i < row; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < col; j++) {
                arr[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        visited = new boolean[K];
        nums = new int[K];

        for(int i = 0; i < K; i++) {
            stk = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken());
            int s = Integer.parseInt(stk.nextToken());
            list.add(new Info(r, c, s));
        }

        DFS(0);
//        DFS(arr, 0);
        sb.append(answer).append("\n");
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
    private static void DFS(int cnt) {
        if(cnt == K) {
            for(int i = 0; i < arr.length; i++) {
                System.arraycopy(arr[i], 0, cpy_arr[i], 0, arr[i].length);
            }
            for(int i = 0; i < nums.length; i++) {
                Info tmp = list.get(nums[i]);
                rotate(cpy_arr, tmp.r, tmp.c, tmp.s);
            }
            int min = Integer.MAX_VALUE;
            for(int i = 0; i < cpy_arr.length; i++) {
                int sum = 0;
                for(int j = 0; j < cpy_arr[i].length; j++) {
                    sum += cpy_arr[i][j];
                }
                min = Math.min(sum, min);
            }
            answer = Math.min(answer, min);
            return;
        }
        for(int i = 0; i < K; i++) {
            if(!visited[i]) {
                visited[i] = true;
                nums[cnt] = i;
                DFS(cnt + 1);
                visited[i] = false;
            }
        }
    }
//    private static void DFS(int[][] arr, int depth) {
//        if(depth == K) {
//            int min = Integer.MAX_VALUE;
//            for(int i = 0; i < arr.length; i++) {
//                int sum = 0;
//                for(int j = 0; j < arr[i].length; j++) {
//                    sum += arr[i][j];
//                }
//                min = Math.min(sum, min);
//            }
//            answer = Math.min(answer, min);
//            return;
//        }
//        for(int i = 0; i < list.size(); i++) {
//            if(!visited[i]) {
//                visited[i] = true;
//                Info tmp = list.get(i);
//                int[][] copy_arr = new int[arr.length][arr[0].length];
//                for(int idx = 0; idx < arr.length; idx++) {
//                    System.arraycopy(arr[idx], 0, copy_arr[idx], 0, arr[idx].length);
//                }
//                rotate(copy_arr, tmp.r, tmp.c, tmp.s);
//                DFS(copy_arr, depth + 1);
//                visited[i] = false;
//            }
//        }
//    }
    private static void rotate(int[][] arr, int r, int c, int s) {
        while(s >= 0) {
            //모서리를 위한 tmp, row, col 변수
            int tmp = 0;
            int left = c - s - 1;
            int right = c + s - 1;
            int top = r - s - 1;
            int bottom = r + s - 1;
            //모서리 저장
            tmp = arr[top][right];

            // 우
            for(int i = right; i > left; i--) {
                arr[top][i] = arr[top][i - 1];
            }
            // 상
            for(int i = top; i < bottom; i++) {
                arr[i][left] = arr[i + 1][left];
            }
            // 좌
            for(int i = left; i < right; i++) {
                arr[bottom][i] = arr[bottom][i + 1];
            }
            // 하
            for(int i = bottom; i > top; i--) {
                arr[i][right] = arr[i - 1][right];
            }

            if(!(left == right || top == bottom))
                arr[top + 1][right] = tmp;
            s--;
        }
    }
}
