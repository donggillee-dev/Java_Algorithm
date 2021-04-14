package SWEA;

import java.io.*;
import java.util.*;

public class BreakBrick {
    private static int[] dir_x = {-1, 0, 1, 0};
    private static int[] dir_y = {0, -1, 0, 1};
    private static class Info {
        int h, w, val;
        public Info(int h, int w, int val) {
            this.h = h;
            this.w = w;
            this.val = val;
        }
    }
    private static int getidx(int H, int W, int[][] arr) {
        int sum = Integer.MIN_VALUE;
        int idx = -1;

        for(int i = 0; i < W; i++) {
            Queue<Info> q = new LinkedList<>();
            int tmp = 0;
            for(int j = 0; j < H; j++) {
                if(arr[j][i] != 0) {
                    q.add(new Info(j, i, arr[j][i]));
                    break;
                }
            }

            while(!q.isEmpty()) {
                tmp++;
                Info inf = q.poll();
                arr[inf.h][inf.w] = 0;
                int width = inf.val;

                for(int dir = 0; dir < 4; dir++) {
                    for(int j = 1; j <= width - 1; j++) {
                        int nh = inf.h + dir_x[dir] * j;
                        int nw = inf.w + dir_y[dir] * j;

                        if(nh < 0 || nw < 0 || nh >= H || nw >= W) continue;

                        if(arr[nh][nw] == 0) continue;
                        q.add(new Info(nh, nw, arr[nh][nw]));
                    }
                }
            }
            if(tmp > sum) {
                idx = i;
                sum = tmp;
            }
        }

        return idx;
    }
    private static void simulation(int idx, int H, int W, int[][] arr) {
        Queue<Info> q = new LinkedList<>();
        for(int i = 0; i < H; i++) {
            if(arr[i][idx] != 0) {
                q.add(new Info(i, idx, arr[i][idx]));
                break;
            }
        }

        while(!q.isEmpty()) {
            Info inf = q.poll();
            arr[inf.h][inf.w] = 0;
            int width = inf.val;

            for(int i = 0; i < 4; i++) {
                for(int j = 1; j <= width - 1; j++) {
                    int nh = inf.h + dir_x[i] * j;
                    int nw = inf.w + dir_y[i] * j;

                    if(nh < 0 || nw < 0 || nh >= H || nw >= W) continue;

                    if(arr[nh][nw] == 0) continue;
                    q.add(new Info(nh, nw, arr[nh][nw]));
                    arr[nh][nw] = 0;
                }
            }
        }
    }
    private static void pull(int H, int W, int[][] arr) {
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < W; i++) {
            for(int j = H - 1; j >= 0; j--) {
                if(arr[j][i] != 0) {
                    q.add(arr[j][i]);
                }
            }
            for(int j = H - 1; j >= 0; j--) {
                if(!q.isEmpty()) {
                    arr[j][i] = q.poll();
                } else {
                    arr[j][i] = 0;
                }
            }
        }
    }
    private static void DFS(int H, int W, int start, int depth, int[][] arr) {
        if(depth == 0) {
            int sum = 0;

            for(int i = 0; i < H; i++) {
                for(int j = 0; j < W; j++) {
                    if(arr[i][j] != 0) sum++;
                }
            }

            if(sum < ans) ans = sum;
            return;
        }

        for(int i = start; i < W; i++) {
            boolean flag = false;
            for(int j = H - 1; j >= 0; j--) {
                if(arr[j][i] != 0) {
                    flag = true;
                    break;
                }
            }
            if(flag) {
                int[][] copy_arr = copy(H, W, arr);
                simulation(start, H, W, copy_arr);
                pull(H, W, copy_arr);
                DFS(H, W, i, depth - 1, copy_arr);
            }
        }
    }
    private static int[][] copy(int H, int W, int[][] arr) {
        int[][] copy_arr = new int[H][W];

        for(int i = 0; i < H; i++) {
            for(int j = 0; j < W; j++) {
                copy_arr[i][j] = arr[i][j];
            }
        }

        return copy_arr;
    }
    private static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            stk = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(stk.nextToken());
            int W = Integer.parseInt(stk.nextToken());
            int H = Integer.parseInt(stk.nextToken());

            int[][] arr = new int[H][W];

            for(int i = 0; i < H; i++) {
                stk = new StringTokenizer(br.readLine());
                for(int j = 0; j < W; j++) {
                    arr[i][j] = Integer.parseInt(stk.nextToken());
                }
            }

            DFS(H, W, 0, N, arr);

            System.out.println(ans);
        }
    }
}
