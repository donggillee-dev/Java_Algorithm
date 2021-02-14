package BOJ.DP.Gold;

import java.io.*;
import java.util.*;

public class boj_1520 {
    static int[][] Dir = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static int[][] Map;
    static int[][] DP;
    static int row, col;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        row = Integer.parseInt(stk.nextToken());
        col = Integer.parseInt(stk.nextToken());

        Map = new int[row][col];
        DP = new int[row][col];

        for(int i = 0; i < row; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < col; j++) {
                Map[i][j] = Integer.parseInt(stk.nextToken());
                DP[i][j] = -1;
            }
        }
        sb.append(Way(0, 0)).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static int Way(int x, int y) {
        if(x == row - 1 && y == col - 1) return 1;

        if(DP[x][y] != -1) return DP[x][y];

        DP[x][y] = 0;

        for(int i = 0; i < 4; i++) {
            int x1 = x + Dir[i][0];
            int y1 = y + Dir[i][1];

            if(x1 >= 0 && x1 < row && y1 >= 0 && y1 < col) {
                if(Map[x][y] > Map[x1][y1]) {
                    DP[x][y] += Way(x1, y1);
                }
            }
        }
        return DP[x][y];
    }
}
