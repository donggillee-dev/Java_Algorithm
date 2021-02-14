package BOJ.BFS.Gold;

import java.io.*;
import java.util.Queue;
import java.util.LinkedList;
import java.util.StringTokenizer;

//Logic

//공기 확산을 BFS로 하면 풀 수 있는 문제이다
//치즈의 개수가 0이 될때까지 공기의 확산은 계속된다
//1. 공기를 우선적으로 확산시킨다
//  1-1. 공기가 확산된 위치는 백트레킹으로 제거하여 해당 위치를 큐에 넣지 않도록 한다
//  1-2. 공기가 확산된 위치에서 4방으로 움직여보고 치즈가 존재한다면 그 위치의 visited를 1 증가하여 준다
//      이렇게 하면 다른 방향의 공기에서 해당 치즈에 닿게 되었을시 2, 즉 공기가 닿은면 개수가 도출되므로 2면 이상이 노출된 치즈는 녹아내리게 해준다

// 2. 위 로직을 치즈의 개수가 0이 될때까지 시간을 1 씩 증가시켜주면서 반복하면 결과가 도출된다.
public class boj_2638 {
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
