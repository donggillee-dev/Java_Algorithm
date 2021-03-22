package Jungol.DFS;

import java.io.*;
import java.util.StringTokenizer;

public class jungol_1733 {
    private static final int ROW = 19, COL = 19;
    private static int[] dir_x = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] dir_y = {0, 1, 1, 1, 0, -1, -1 ,-1};
    private static int[][] Map = new int[ROW][COL];
    private static boolean[][] visited = new boolean[ROW][COL];
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;
        int winColor = 0, winRow = 0, winCol = 0;
        for(int row = 0; row < ROW; row++) {
            stk = new StringTokenizer(br.readLine());
            for(int col = 0; col < COL; col++) {
                Map[row][col] = Integer.parseInt(stk.nextToken());
            }
        }

        loop:for(int col = 0; col < COL; col++) {
            for(int row = 0; row < ROW; row++) {

                if(Map[row][col] != 0) {
                    int blackWhite = Map[row][col];
                    visited[row][col] = true;
                    for(int i = 0; i < 8; i++) {
                        int length = 2;
                        int nr = row + dir_x[i];
                        int nc = col + dir_y[i];

                        if(nr < 0 || nc < 0 || nr >= ROW || nc >= COL || Map[nr][nc] != blackWhite) continue;

                        if(Map[nr][nc] == blackWhite && !visited[nr][nc]) {
                            length += DFS(blackWhite, nr, nc, i);
                        }

                        if(length == 5) {
                            winColor = blackWhite;
                            winRow = row;
                            winCol = col;
                            break loop;
                        }
                    }
                }
            }
        }
        sb.append(winColor).append("\n");
        if(winColor != 0) {
            sb.append(winRow + 1).append(" ").append(winCol + 1).append("\n");
        }
        bw.write(String.valueOf(sb));
        bw.close();
        br.close();
    }
    private static int DFS(int blackWhite, int row, int col, int dir) {
        int nr = row + dir_x[dir];
        int nc = col + dir_y[dir];

        if(nr < 0 || nc < 0 || nr >= ROW || nc >= COL || Map[nr][nc] != blackWhite) return 0;
        else {
            visited[nr][nc] = true;
            return (1 + DFS(blackWhite, nr, nc, dir));
        }
    }
}
