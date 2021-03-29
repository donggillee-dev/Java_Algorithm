package BOJ.DP.Gold;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_2533 {
    private static ArrayList<Integer>[] adj;
    private static int[][] dp;
    private static void dp(int cur, int parent) {
        dp[cur][0] = 0;
        dp[cur][1] = 1;

        for(int next : adj[cur]) {
            if(next != parent) {
                dp(next, cur);
                dp[cur][0] += dp[next][1];
                dp[cur][1] += Math.min(dp[next][0], dp[next][1]);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        int N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N + 1];
        dp = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i = 0; i < N-1; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }

        dp(1, -1);

        System.out.print(Math.min(dp[1][0], dp[1][1]));
    }
}
