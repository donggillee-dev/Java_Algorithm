package SWEA.D4;

import java.io.*;
import java.util.StringTokenizer;

public class SquareRoom {
    static int N;
    static int[][] Map;
    static int[][] DP;
    static int[] dir_x = {-1, 1, 0, 0};
    static int[] dir_y = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int TestCase = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= TestCase; tc++) {
            int answer_val = Integer.MAX_VALUE;
            int answer_length = Integer.MIN_VALUE;
            N = Integer.parseInt(br.readLine());
            Map = new int[N + 1][N + 1];
            DP = new int[N + 1][N + 1];
            StringTokenizer stk;

            for(int i = 1; i <= N; i++) {
                stk = new StringTokenizer(br.readLine());
                for(int j = 1; j <= N; j++) {
                    Map[i][j] = Integer.parseInt(stk.nextToken());
                }
            }
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    int ret = DFS(i, j);

                    if(ret == answer_length && Map[i][j] < answer_val) {
                        answer_val = Map[i][j];
                    } else if(ret > answer_length) {
                        answer_val = Map[i][j];
                        answer_length = ret;
                    }
                }
            }

            sb.append("#" + tc + " " ).append(answer_val).append(" ").append(answer_length).append("\n");
        }
        bw.write(String.valueOf(sb));
        bw.close();
        br.close();
    }
    private static int DFS(int row, int col) {
        if(DP[row][col] != 0) return DP[row][col];
        DP[row][col] = 1;

        for(int i = 0; i < 4; i++) {
            int nr = row + dir_x[i];
            int nc = col + dir_y[i];
            if(nr < 1 || nc < 1 || nr > N || nc > N) continue;

            if(Map[row][col] + 1 == Map[nr][nc]) {
                DP[row][col] = Math.max(DP[row][col], DFS(nr, nc) + 1);
            }
        }

        return DP[row][col];
    }
}
