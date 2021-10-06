package BOJ.BFS.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//bfs로 풀면 된다고 생각했지만 백트레킹으로 풀어야하는 문제
//bfs는 가장 빠른 최단 거리를 뽑아야하는 것
//모든 경우의 수를 체크해서 가장 많은 고구마를 먹을 경로를 체크하는건 dfs를 이용해야함
//그래프 놓은지 오래됐다 다시 감 찾자


public class boj_21772 {
    private static int R, C, T;
    private static int ans = 0;
    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, -1, 0, 1};

    private static void dfs(int r, int c, int cnt, int time, char[][] map) {
        ans = Math.max(ans, cnt);

        for(int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(nr < 0 || nc < 0 || nr >= R || nc >= C || time + 1 > T) continue;
            if(map[nr][nc] == '#') continue;

            int isSweetPotato = map[nr][nc] == 'S' ? 1 : 0;

            if(map[nr][nc] == 'S') {
                map[nr][nc] = '.';
                dfs(nr, nc, cnt + 1, time + 1, map);
                map[nr][nc] = 'S';
            } else {
                dfs(nr, nc, cnt, time + 1, map);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        R = Integer.parseInt(stk.nextToken());
        C = Integer.parseInt(stk.nextToken());
        T = Integer.parseInt(stk.nextToken());
        int sr = 0, sc = 0;
        char[][] map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);

                if (map[i][j] == 'G') {
                    sr = i;
                    sc = j;
                }
            }
        }

        dfs(sr, sc, 0, 0, map);

        System.out.println(ans);
    }
}
