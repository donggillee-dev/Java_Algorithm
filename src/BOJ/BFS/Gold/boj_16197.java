package BOJ.BFS.Gold;

import java.io.*;
import java.util.*;

public class boj_16197 {
    private static class Info {
        int coin1_row, coin1_col, coin2_row, coin2_col, count;
        public Info(int row1, int col1, int row2, int col2, int count) {
            this.coin1_row = row1;
            this.coin1_col = col1;
            this.coin2_row = row2;
            this.coin2_col = col2;
            this.count = count;
        }
    }
    private static int[] dir_x = {-1, 0, 1, 0};
    private static int[] dir_y = {0, -1, 0, 1};
    private static int ans = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        PriorityQueue<Info> Q = new PriorityQueue<>(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                return o1.count - o2.count;
            }
        });
        boolean[][][][] visited = new boolean[21][21][21][21];
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        char[][] board = new char[N][M];

        int coin1_row = -1, coin1_col = -1, coin2_row = -1, coin2_col = -1;
        for(int i = 0; i < N; i++) {
            sb.append(br.readLine());
            for(int j = 0; j < M; j++) {
                board[i][j] = sb.charAt(j);
                if(board[i][j] == 'o') {
                    if(coin1_row == -1 && coin1_col == -1) {
                        coin1_row = i;
                        coin1_col = j;
                    } else {
                        coin2_row = i;
                        coin2_col = j;
                    }
                }
            }
            sb.setLength(0);
        }
        visited[coin1_row][coin1_col][coin2_row][coin2_col] = true;
        Q.add(new Info(coin1_row, coin1_col, coin2_row, coin2_col, 0));

        while(!Q.isEmpty()) {
            Info inf = Q.poll();
            visited[inf.coin1_row][inf.coin1_col][inf.coin2_row][inf.coin2_col] = true;
            if(inf.count > 10) break;
            for(int i = 0; i < 4; i++) {
                int nr1 = inf.coin1_row + dir_x[i];
                int nc1 = inf.coin1_col + dir_y[i];
                int nr2 = inf.coin2_row + dir_x[i];
                int nc2 = inf.coin2_col + dir_y[i];

                if(!isPossible(nr1, nc1, nr2, nc2, N, M, inf.count + 1)) continue;
                if(visited[nr1][nc1][nr2][nc2]) continue;

                if(board[nr1][nc1] == '#' && board[nr2][nc2] == '#') continue;

                if(board[nr1][nc1] == '#') {
                    nr1 = inf.coin1_row;
                    nc1 = inf.coin1_col;
                }
                if(board[nr2][nc2] == '#') {
                    nr2 = inf.coin2_row;
                    nc2 = inf.coin2_col;
                }
                visited[nr1][nc1][nr2][nc2] = true;
                Q.add(new Info(nr1, nc1, nr2, nc2, inf.count + 1));
            }
        }
        if(ans == 987654321 || ans > 10) ans = -1;
        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
    private static boolean isPossible(int nr1, int nc1, int nr2, int nc2, int N, int M, int count) {
        boolean flag1 = false, flag2 = false;

        if(nr1 < 0 || nc1 < 0 || nr1 >= N || nc1 >= M) flag1 = true;
        if(nr2 < 0 || nc2 < 0 || nr2 >= N || nc2 >= M) flag2 = true;

        if(flag1 && flag2) return false;
        else if(!flag1 && !flag2) return true;
        else {
            if(nr1 == nr2 && nc1 == nc2) return false;
            if(ans > count) ans = count;
            return false;
        }
    }
}
