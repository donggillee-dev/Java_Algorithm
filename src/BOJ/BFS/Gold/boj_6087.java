package BOJ.BFS.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//Logic
//단순히 BFS를 돌면서 얼마나 최소한으로 꺽어서 도착지에 도달하냐를 구하는 문제이다.
//visited배열에는 이때까지 탐색한 노드중에 최소로 꺽은 횟수가 들어있다
//계속 틀렸던 이유는 도착지의 visited값을 변경해주지 않았기에 틀렸었음. 또한 90도 전환에 대해서만 생각해주어여함
//거울은 90만 꺽을 수 있지 180를 꺽거나 할 수 없음

public class boj_6087 {
    private static class Info {
        int x, y, dir, cnt;
        public Info(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        int col = Integer.parseInt(stk.nextToken()), row = Integer.parseInt(stk.nextToken());
        int start_row = 0, start_col = 0, end_row = 0, end_col = 0, ans = 987654321;
        boolean isFound = false;

        int[] dir_x = {-1, 0, 1, 0}, dir_y = {0, -1, 0, 1};
        char[][] map = new char[row][col];
        int[][] visited = new int[row][col];


        for(int i = 0; i < row; i++) {
            map[i] = br.readLine().toCharArray();
            Arrays.fill(visited[i], 987654321);
            for(int j = 0; j < col; j++) {
                if(map[i][j] == 'C') {
                    if(isFound) {
                        end_row = i;
                        end_col = j;
                    } else {
                        start_row = i;
                        start_col = j;
                        isFound = true;
                    }
                }
            }
        }

        visited[start_row][start_col] = 0;
        Queue<Info> Q = new LinkedList<>();

        for(int i = 0; i < 4; i++) {
            int nr = start_row + dir_x[i];
            int nc = start_col + dir_y[i];

            if(nr < 0 || nc < 0 || nr >= row || nc >= col || map[nr][nc] == '*') continue;

            visited[nr][nc] = 0;
            Q.add(new Info(nr, nc, i, 0));
        }

        while(!Q.isEmpty()) {
            Info pos = Q.poll();

            if(pos.x == end_row && pos.y == end_col && pos.cnt < ans) {
                ans = pos.cnt;
                visited[pos.x][pos.y] = pos.cnt;
            }

            for(int i = 0; i < 4; i++) {
                int nr = pos.x + dir_x[i];
                int nc = pos.y + dir_y[i];
                int nextCnt = pos.cnt;

                if(nr < 0 || nc < 0 || nr >= row || nc >= col || map[nr][nc] == '*') continue;

                if(Math.abs(i - pos.dir) == 2) continue;

                if(pos.dir != i) nextCnt++;

                if(visited[nr][nc] < nextCnt) continue;

                visited[nr][nc] = nextCnt;

                Q.add(new Info(nr, nc, i, nextCnt));
            }
        }

        System.out.println(ans);
    }
}
