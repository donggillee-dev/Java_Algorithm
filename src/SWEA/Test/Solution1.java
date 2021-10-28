package SWEA.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.StringTokenizer;

//포는 1개, 그 외에는 장기 알들이 놓여져 있다
//
public class Solution1 {
    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, -1, 0, 1};
    private static int N;
    private static HashSet<Integer> ans;

    private static class Info {
        boolean visited;
        int num;

        public Info(boolean visited, int num) {
            this.visited = visited;
            this.num = num;
        }
    }

    private static boolean isRange(int row, int col) {
        if(row < 0 || col < 0 || row >= N || col >= N) return false;
        return true;
    }

    private static void dfs(int depth, int row, int col, Info[][] map) {
        if(depth == 3) return;

        for(int dir = 0; dir < 4; dir++) {
            int nr = row + dr[dir];
            int nc = col + dc[dir];
            boolean flag = false;

            while(isRange(nr, nc) && map[nr][nc].num == 1) {
                if(map[nr][nc].num == 1) {
                    nr += dr[dir];
                    nc += dc[dir];
                    flag = true;
                    break;
                }
                nr += dr[dir];
                nc += dc[dir];
            }

            if(flag) {
                while(isRange(nr, nc)) {
                    if(!map[nr][nc].visited) {
                        if(map[nr][nc].num == 1) {
                            ans.add(nr * N + nc);
                        }
                        map[nr][nc].visited = true;
                        dfs(depth + 1, nr, nc, map);
                        map[nr][nc].visited = false;
                    }
                    nr += dr[dir];
                    nc += dc[dir];
                }
            }


        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            ans = new HashSet<>();
            N = Integer.parseInt(br.readLine());
            int sr = 0, sc = 0;
            Info[][] map = new Info[N][N];

            for(int row = 0; row < N; row++) {
                stk = new StringTokenizer(br.readLine());
                for(int col = 0; col < N; col++) {
                    int num = Integer.parseInt(stk.nextToken());

                    if(num == 2) {
                        sr = row;
                        sc = col;
                    }
                    map[row][col] = new Info(false, num);
                }
            }

            dfs(0, sr, sc, map);
            sb.append("#" + tc + " " + ans.size());
        }

        System.out.println(sb);
    }
}
