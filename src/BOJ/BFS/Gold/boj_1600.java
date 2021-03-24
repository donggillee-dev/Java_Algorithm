package BOJ.BFS.Gold;

import java.io.*;
import java.util.*;

//Logic
//BFS로 풀 수 있는 문제
//말 능력치를 사용해서 해당 위치에 도달할 수 있는지 아닌지로 BFS를 돌아서 도착지에 도달하면 이동 횟수를 출력해주고 종료해주면 된다

public class boj_1600 {
    private static class monInfo {
        int row, col, cnt, ablt;
        public monInfo(int row, int col, int cnt, int ablt) {
            this.row = row;
            this.col = col;
            this.cnt = cnt;
            this.ablt = ablt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(stk.nextToken()), H = Integer.parseInt(stk.nextToken());
        boolean[][] Map = new boolean[H][W];

        for(int i = 0; i < H; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < W; j++) {
                Map[i][j] = (Integer.parseInt(stk.nextToken()) == 1);
            }
        }

        bfs(H, W, K, Map);
    }
    private static void bfs(int H, int W, int K, boolean[][] Map) {
        short[] dir_x = {-1, -2, -2, -1, 1, 2, 2, 1, 1, 0, -1, 0};
        short[] dir_y = {-2, -1, 1, 2, 2, 1, -1, -2, 0, 1, 0, -1};
        boolean[][][] visited = new boolean[H][W][K + 1];
        Queue<monInfo> Q = new LinkedList<>();

        visited[0][0][0] = true;
        Q.add(new monInfo(0, 0, 0, 0));

        while(!Q.isEmpty()) {
            monInfo inf = Q.poll();
            int x = inf.row, y = inf.col, cnt = inf.cnt, ablt = inf.ablt;

            if(x == H - 1 && y == W - 1) {
                System.out.println(cnt);
                return;
            }

            if(ablt < K) {
                for(int i = 0; i < 8; i++) {
                    int nx = inf.row + dir_x[i];
                    int ny = inf.col + dir_y[i];

                    if(nx < 0 || ny < 0 || nx >= H || ny >= W || Map[nx][ny] || visited[nx][ny][ablt + 1]) continue;

                    visited[nx][ny][ablt + 1] = true;

                    Q.add(new monInfo(nx, ny, inf.cnt + 1, ablt + 1));
                }
            }
            for(int i = 8; i < 12; i++) {
                int nx = inf.row + dir_x[i];
                int ny = inf.col + dir_y[i];

                if(nx < 0 || ny < 0 || nx >= H || ny >= W || Map[nx][ny] || visited[nx][ny][ablt]) continue;

                visited[nx][ny][ablt] = true;
                Q.add(new monInfo(nx, ny, inf.cnt + 1, ablt));
            }
        }
        System.out.println(-1);
    }
}
