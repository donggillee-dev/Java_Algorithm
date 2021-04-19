package BOJ.FloydWarshall.Gold;

import java.io.*;
import java.util.*;

public class boj_11404 {
    private static final int inf = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] adj = new int[n + 1][n + 1];
        StringTokenizer stk;

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                adj[i][j] = inf;
                if (i == j) adj[i][j] = 0;
            }
        }

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int w = Integer.parseInt(stk.nextToken());

            adj[a][b] = Math.min(adj[a][b], w);
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (adj[i][j] == inf)
                    sb.append(0).append(" ");
                else
                    sb.append(adj[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
