package SWEA.D3;

import java.io.*;
import java.util.Queue;
import java.util.LinkedList;

public class Harvest {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        Queue<FarmInfo> Q = new LinkedList<>();
        int[] dir_row = {-1, 1, 0, 0};
        int[] dir_col = {0, 0, -1, 1};
        int TestCase = Integer.parseInt(br.readLine());

        StringBuilder tmp = new StringBuilder();
        //for testCase
        for(int tc = 0; tc < TestCase; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[][] farm = new int[N][N];
            boolean[][] visited = new boolean[N][N];
            int start_row = N / 2;
            int start_col = N / 2;
            int endDepth = N / 2;
            int answer = 0;

            //input info
            for(int i = 0; i < N; i++) {
                tmp.append(br.readLine());
                for (int j = 0; j < N; j++) {
                    farm[i][j] = tmp.charAt(j) - '0';
                }
                tmp.delete(0, tmp.length());
            }

            visited[start_row][start_col] = true;
            Q.offer(new FarmInfo(0, start_row, start_col));

            while(!Q.isEmpty()) {
                FarmInfo info = Q.poll();
                answer += farm[info.r][info.c];

                for(int i = 0; i < dir_row.length; i++) {
                    int nx = info.r + dir_row[i];
                    int ny = info.c + dir_col[i];

                    if(nx < 0 || ny < 0 || nx >= N || ny >= N || info.depth == endDepth) continue;

                    if(!visited[nx][ny]) {
                        Q.offer(new FarmInfo(info.depth + 1, nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }

            sb.append("#" + (tc + 1) + " ").append(answer).append("\n");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
    private static class FarmInfo {
        int depth;
        int r, c;

        public FarmInfo(int depth, int r, int c) {
            this.depth = depth;
            this.r = r;
            this.c = c;
        }
    }
}
