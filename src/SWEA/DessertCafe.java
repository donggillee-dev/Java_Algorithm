package SWEA;

import java.io.*;
import java.util.StringTokenizer;

public class DessertCafe {
    private static int[][] dir_arr = {{1,-1}, {1,1}, {-1,1}, {-1,-1}};
    private static int N, answer;
    private static int[][] Map;
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        StringTokenizer stk;

        for (int t = 1; t <= TC; t++) {
            N = Integer.parseInt(br.readLine());
            visited = new boolean[101];
            Map = new int[N][N];
            answer = -1;

            for (int i = 0; i < N; i++) {
                stk = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    Map[i][j] = Integer.parseInt(stk.nextToken());
                }
            }

            for (int i = 0; i < N - 2; i++) {
                for (int j = 1; j < N - 1; j++) {
                    visited[Map[i][j]] = true;
                    DFS(i, j, i, j, 0, 1);
                    visited[Map[i][j]] = false;
                }
            }
            sb.append("#" + t + " " + answer).append("\n");
        }
        System.out.println(sb);
//        bw.write(sb.toString());
//        bw.flush();
    }
    private static void DFS(int start_row, int start_col, int cur_row, int cur_col, int dir, int cnt) {
        if(dir >= 4) return;
        int nr = cur_row + dir_arr[dir][0];
        int nc = cur_col + dir_arr[dir][1];

        if(cnt >= 3 && nr == start_row && nc == start_col) {
            if(cnt > answer) {
                answer = cnt;
                return;
            }
        }

        if(nr < 0 || nc < 0 || nr >= N || nc >= N) return;
        if(visited[Map[nr][nc]]) return;

        visited[Map[nr][nc]] = true;
        DFS(start_row, start_col, nr, nc, dir, cnt + 1);
        DFS(start_row, start_col, nr, nc, dir + 1, cnt + 1);
        visited[Map[nr][nc]] = false;
    }
}