package BOJ.BFS.Gold;

import java.io.*;
import java.util.*;

public class boj_2573 {
    private static class Pos {
        int row, col;
        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int[] dir_x = {-1, 1, 0, 0};
        int[] dir_y = {0, 0, -1, 1};
        int[][] arr;
        boolean[][] visited;
        int N = Integer.parseInt(stk.nextToken()), M = Integer.parseInt(stk.nextToken()), answer = 0;
        Queue<Pos> Q = new LinkedList<>();
        Pos p = new Pos(0, 0);

        int totalCnt = 0;
        arr = new int[N][M];
        visited = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(stk.nextToken());
                if(arr[i][j] != 0){
                    p.row = i;
                    p.col = j;
                    totalCnt++;
                }
            }
        }


        while(totalCnt > 0) {
            int nodeCnt = 0, meltCnt = 0;
            Q.add(new Pos(p.row, p.col));
            visited[p.row][p.col] = true;
            while(!Q.isEmpty()) {
                Pos tmp = Q.poll();
                int cnt = 0;
                for(int i = 0; i < 4; i++) {
                    int nr = tmp.row + dir_x[i];
                    int nc = tmp.col + dir_y[i];

                    if(nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc]) continue;

                    if(!visited[nr][nc]) {
                        if(arr[nr][nc] == 0) {
                            cnt++;
                        } else {
                            visited[nr][nc] = true;
                            Q.add(new Pos(nr, nc));
                        }
                    }
                }
                arr[tmp.row][tmp.col] -= cnt;
                if(arr[tmp.row][tmp.col] <= 0) {
                    arr[tmp.row][tmp.col] = 0;
                    meltCnt++;
                } else {
                    p.row = tmp.row;
                    p.col = tmp.col;
                }
                nodeCnt++;
            }


            if(nodeCnt != totalCnt) break;
            else totalCnt -= meltCnt;
            answer++;

            visited = new boolean[N][M];
        }
        if(totalCnt == 0) {
            System.out.println(0);
        } else {
            System.out.println(answer);
        }
    }
}
