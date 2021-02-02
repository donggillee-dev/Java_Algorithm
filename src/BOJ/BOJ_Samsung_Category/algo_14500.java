package BOJ.BOJ_Samsung_Category;
import java.io.*;
import java.util.*;

public class algo_14500 {
    static int N, M, ans = 0;
    static int[][] Canvas;
    static boolean[][] Visited;
    static int[] DirX = {1, -1, 0, 0};
    static int[] DirY = {0, 0, 1, -1};
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        Canvas = new int[N][M];
        Visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                Canvas[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                Visited[i][j] = true;
                DFS(0, i, j, Canvas[i][j]);
                Visited[i][j] = false;
                if(i == 0) {
                    if(j == 0 || j == M - 1) continue;
                } else if(i == N - 1) {
                    if(j == 0 || j == M - 1) continue;
                }

                if(i == 0) { // ㅜ
                    ans = Math.max(ans, Canvas[i][j] + Canvas[i][j - 1] + Canvas[i][j + 1] + Canvas[i + 1][j]);
                } else if(i == N -1) { //ㅗ
                    ans = Math.max(ans, Canvas[i][j] + Canvas[i - 1][j] + Canvas[i][j - 1] + Canvas[i][j + 1]);
                } else if(j == 0) {// ㅏ
                    ans = Math.max(ans, Canvas[i][j] + Canvas[i][j + 1] + Canvas[i - 1][j] + Canvas[i + 1][j]);
                } else if(j == M - 1) {//ㅓ
                    ans = Math.max(ans, Canvas[i][j] + Canvas[i][j - 1] + Canvas[i - 1][j] + Canvas[i + 1][j]);
                } else {
                    ans = Math.max(ans, Canvas[i][j] + Canvas[i][j - 1] + Canvas[i][j + 1] + Canvas[i + 1][j]);
                    ans = Math.max(ans, Canvas[i][j] + Canvas[i - 1][j] + Canvas[i][j - 1] + Canvas[i][j + 1]);
                    ans = Math.max(ans, Canvas[i][j] + Canvas[i][j + 1] + Canvas[i - 1][j] + Canvas[i + 1][j]);
                    ans = Math.max(ans, Canvas[i][j] + Canvas[i][j - 1] + Canvas[i - 1][j] + Canvas[i + 1][j]);
                }
            }
        }

        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void DFS(int depth, int x, int y, int val) {
        if(depth == 3) {
            if(ans < val) ans = val;
            return;
        }

        for(int i = 0; i < 4; i++) {
            int nx = x + DirX[i];
            int ny = y + DirY[i];
            if (nx < 0 || ny < 0 || nx > N - 1 || ny > M - 1) continue;
            if(!Visited[nx][ny]) {
                Visited[nx][ny] = true;
                DFS(depth + 1, nx, ny, val + Canvas[nx][ny]);
                Visited[nx][ny] = false;
            }
        }
        return;
    }
}
