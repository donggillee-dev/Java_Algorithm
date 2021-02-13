package BOJ.BOJ_StepByStep.Gold;

import java.io.*;
import java.util.Queue;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class algo_2638 {
    private static int N, M, cheeseCnt;
    private static short[][] visited;
    private static int[][] arr;
    private static final int[] dir_x = {0, 1, 0, -1};
    private static final int[] dir_y = {1, 0, -1, 0};
    private static class PosInfo {
        int row, col;
        public PosInfo(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    private static Queue<PosInfo> Q = new LinkedList<>();

    private static void airSpread() {
        Q.add(new PosInfo(0, 0));
        visited = new short[N][M];
        visited[0][0]++;
        while(!Q.isEmpty()) {
            PosInfo tmp = Q.poll();

            for(int i = 0; i < 4; i++) {
                int nx = tmp.row + dir_x[i];
                int ny = tmp.col + dir_y[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                if(arr[nx][ny] == 1) {
                    visited[nx][ny]++;
                    if(visited[nx][ny] >= 2) {
                        arr[nx][ny] = 0;
                        cheeseCnt--;
                    }
                } else {
                    if(visited[nx][ny] == 0 && arr[nx][ny] == 0) {
                        visited[nx][ny]++;
                        Q.add(new PosInfo(nx, ny));
                    }
                }
            }
        }
    }

    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        arr = new int[N][M];
        int answer = 0;

        for(int i = 0; i < N; i++) {
            sb.append(br.readLine());
            for(int j = 0, idx = 0; j < M; j++, idx += 2) {
                arr[i][j] = sb.charAt(idx) - '0';
                if(arr[i][j] == 1) cheeseCnt++;
            }
            sb.setLength(0);
        }

        while(cheeseCnt > 0) {
            airSpread();//외부 공기 확산
            answer++;//시간 증가
        }
        sb.append(answer);
        bw.write(String.valueOf(sb));
        bw.close();
        br.close();
    }
}
