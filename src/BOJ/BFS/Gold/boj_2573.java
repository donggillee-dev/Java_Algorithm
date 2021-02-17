package BOJ.BFS.Gold;

import java.io.*;
import java.util.*;
//Logic
//[BOJ_2573] 빙산 problem solved
//
//빙산 측면에서 BFS로 탐색하는 방식으로 해결했다
//
//특정 빙산 즉 노드에서 4방향 탐색을 해서 바다와 닿은 면 개수 만큼 빙하의 높이를
//낮춰주고 그외의 다음 빙하의 경우에는 큐에 넣어줌
//
//탐색한 빙하의 개수와 녹이기 이전의 개수가 동일하면 분리되지 않았다는 것을 알 수 있으며
//이때 녹은 빙하의 개수를 전체 빙하의 개수에서 뺴준다.
//
//그렇지 않은 경우에는 빙하가 분리되었다는 뜻이므로 탐색 종료.
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
