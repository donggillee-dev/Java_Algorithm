package BOJ.DFS.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1987 {
    private static int R, C, answer = Integer.MIN_VALUE;
    private static char[][] Map;
    private static int[][] visited;
    private static int[] dir_x = {1, 0, -1, 0}, dir_y = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        R = Integer.parseInt(stk.nextToken());
        C = Integer.parseInt(stk.nextToken());
        Map = new char[R][C];
        visited = new int[R][C];

        for(int i = 0; i < R; i++) {
            sb.append(br.readLine());
            for(int j = 0; j < C; j++) {
                Map[i][j] = sb.charAt(j);
            }
            sb.setLength(0);
        }

        DFS(0, 0, 1, 1 << (Map[0][0] - 'A'));

        System.out.println(answer);
    }
    private static void DFS(int row, int col, int count, int bit) {
        if(visited[row][col] == bit) return;
        visited[row][col] = bit;
        answer = Math.max(answer, count);

        for(int i = 0; i < 4; i++) {
            int nr = row + dir_x[i];
            int nc = col + dir_y[i];

            if(nr < 0 || nc < 0 || nc >= C || nr >= R || (bit & (1 << Map[nr][nc] - 'A')) != 0) continue;

            DFS(nr, nc, count + 1, bit | (1 << Map[nr][nc] - 'A'));
        }
    }
}