package BOJ.BFS.Gold;

import java.io.*;
import java.util.*;

//Logic
//BFS로 해결한 문제이다
//핵심은 Deque를 이용한 것이며 녹은 치즈의 공간을 다음 air에 대해서 바로 처리해주게끔 하기 위해
//addLast해주고 현재의 공기들이 지나갈 수 있는 곳들에 대해서 다 처리해줌 => 그럼 녹은 자리들부터 다시 공기 확산 시작
public class boj_2636 {
    private static void bfs(int R, int C, boolean[][] Map) {
        int ansTime = 0, ansCnt = 0, size;
        short[] dir_x = {-1, 0, 1, 0}, dir_y = {0, -1, 0, 1};
        boolean[][] visited = new boolean[R][C];
        Deque<int[]> Q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        Q.addFirst(new int[]{0, 0});
        visited[0][0] = true;

        while(!Q.isEmpty()) {
            size = Q.size();
            ansCnt = size;
            ansTime++;
            while(--size >= 0) {
                int[] inf = Q.poll();
                int x = inf[0];
                int y = inf[1];
                for(int i = 0; i < 4; i++) {
                    int nx = x + dir_x[i];
                    int ny = y + dir_y[i];

                    if(nx < 0 || ny < 0 || nx >= R || ny >= C || visited[nx][ny]) continue;

                    visited[nx][ny] = true;
                    if(Map[nx][ny]) {
                        Map[nx][ny] = false;
                        Q.addLast(new int[]{nx, ny});
                    } else {
                        size++;
                        Q.addFirst(new int[]{nx, ny});
                    }
                }
            }
        }
        sb.append(ansTime - 1).append("\n").append(ansCnt);
        System.out.print(sb);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(stk.nextToken()), C = Integer.parseInt(stk.nextToken());
        boolean[][] Map = new boolean[R][C];

        for(int i = 0; i < R; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++) {
                Map[i][j] = (Short.parseShort(stk.nextToken()) == 1);
            }
        }
        bfs(R, C, Map);
    }
}
