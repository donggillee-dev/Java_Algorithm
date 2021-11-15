package BOJ.DP.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_21317 {
    private static int N, K;
    private static void dfs(int idx, int use, int big, int[][] dp, int[][] info) {
        if(idx >= N) return;
        if(dp[idx][big] < use) return;

        if(dp[idx][big] > use) dp[idx][big] = use;

        dfs(idx + 1, use + info[idx][0], big, dp, info);
        dfs(idx + 2, use + info[idx][1], big, dp, info);
        if(big == 0) {
            dfs(idx + 3, use + K, big + 1, dp, info);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        N = Integer.parseInt(br.readLine());
        K = 0;
        int[][] info = new int[N][2];
        int[][] dp = new int[N][2];

        dp[N - 1][0] = 987654321;
        dp[N - 1][1] = 987654321;

        for(int i = 0; i < N - 1; i++) {
            stk = new StringTokenizer(br.readLine());
            info[i][0] = Integer.parseInt(stk.nextToken());
            info[i][1] = Integer.parseInt(stk.nextToken());
            dp[i][0] = 987654321;
            dp[i][1] = 987654321;
        }
        K = Integer.parseInt(br.readLine());

        dfs(0, 0, 0, dp, info);
        System.out.println(Math.min(dp[N - 1][0], dp[N - 1][1]));
    }
}
