package BOJ.BFS.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//Logic
//불들의 bfs를 먼저 돌려주고 지훈이의 bfs를 돌려줘가면서 지훈이가 탈출할 수 있는지 여부를 체크하면되는 문제
//bfs를 돌때는 진행방향에 있는 곳이 불인지 벽인지를 체크
//불과 지훈이가 한 큐에서 같이 넣어져서 돌아가기때문에 둘다 이동할 수 없는 공간은 벽이며, 지훈이는 불에 들어갈 수 없고 불이 굳이 불이 있는 곳에 또 방문할 필요는 없음
//방문처리 여부는 지훈이에 대해서만 처리
//항상 문제 제대로 읽자...불은 하나만 있ㄴㄴ 것이아니라 여러개 있을수 있음

public class boj_4179 {
    private static int r, c;
    private static int[] dir_r = {-1, 0, 1, 0}, dir_c = {0, -1, 0, 1};
    private static class Info {
        int row, col, type, cnt;
        public Info(int row, int col, int type, int cnt) {
            this.row = row;
            this.col = col;
            this.type = type;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        Queue<Info> q = new LinkedList<>();

        r = Integer.parseInt(stk.nextToken());
        c = Integer.parseInt(stk.nextToken());

        char[][] map = new char[r][c];
        boolean[][] visited = new boolean[r][c];
        Info ji = null;

        for(int i = 0; i < r; i++) {
            String tmp = br.readLine();
            for(int j = 0; j < c; j++) {
                map[i][j] = tmp.charAt(j);
                if(map[i][j] == 'J') {
                    ji = new Info(i, j, 0, 0);
                } else if(map[i][j] == 'F') {
                    q.add(new Info(i, j, 1, 0));
                }
            }
        }
        q.add(ji);

        bfs(map, visited, q);
    }
    private static void bfs(char[][] map, boolean[][] visited, Queue<Info> q) {
        while(!q.isEmpty()) {
            Info inf = q.poll();

            if(inf.type == 0 && (inf.row == 0 || inf.row == r - 1 || inf.col == 0 || inf.col == c - 1)) {
                System.out.println(inf.cnt + 1);
                return;
            }

            for(int i = 0; i < 4; i++) {
                int nr = inf.row + dir_r[i];
                int nc = inf.col + dir_c[i];

                if(nr < 0 || nc < 0 || nr >= r || nc >= c || map[nr][nc] == '#' || map[nr][nc] == 'F') continue;

                if(inf.type == 0 && !visited[nr][nc]) {
                    q.add(new Info(nr, nc, inf.type, inf.cnt + 1));
                    visited[nr][nc] = true;
                } else if(inf.type == 1){
                    map[nr][nc] = 'F';
                    q.add(new Info(nr, nc, inf.type, inf.cnt + 1));
                }
            }
        }
        System.out.println("IMPOSSIBLE");
    }
}
