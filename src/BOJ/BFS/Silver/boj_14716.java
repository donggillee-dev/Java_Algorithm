package BOJ.BFS.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//Logic
//bfs 진짜 간단한 문제
//풀이 시간 : 5분

public class boj_14716 {
    private static int r, c;
    private static int[] dir_x = {-1, 0, 1, 0, -1, 1, -1, 1};
    private static int[] dir_y = {0, -1, 0, 1, -1, -1, 1, 1};
    private static boolean[][] visited;
    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        r = Integer.parseInt(stk.nextToken());
        c = Integer.parseInt(stk.nextToken());
        visited = new boolean[r][c];

        for(int i = 0; i < r; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < c; j++) {
                int num = Integer.parseInt(stk.nextToken());

                if(num == 1) visited[i][j] = true;
            }
        }
    }
    private static boolean isRange(int nr, int nc) {
        if(nr < 0 || nc < 0 || nr >= r || nc >= c || !visited[nr][nc]) return false;

        return true;
    }
    private static void bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});

        while(!q.isEmpty()) {
            int[] node = q.poll();

            for(int i = 0; i < 8; i++) {
                int nr = node[0] + dir_x[i];
                int nc = node[1] + dir_y[i];

                if(!isRange(nr, nc)) continue;

                q.add(new int[]{nr, nc});
                visited[nr][nc] = false;
            }
        }
    }
    private static int solve() {
        int ans = 0;
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(visited[i][j]) {
                    bfs(i, j);
                    ans++;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        input();
        System.out.print(solve());
    }
}
