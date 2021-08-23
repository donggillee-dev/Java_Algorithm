package BOJ.DP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//Logic
//현재의 노드에서 0 -> 얼리어답터 x , 1 -> 얼리어답터
//현재의 얼리어답터까지의 최소값은 이전의 노드가 얼리어답터인지 아닌지로 점화식
//이전에 풀었던거인데도 다시 풀어보는데 오래걸림, 또 풀어보자

public class boj_2533 {
    private static int n;
    private static ArrayList<Integer>[] adj;

    private static int stoi(String str) {
        return Integer.parseInt(str);
    }
    private static void dp(int cur, int parent, int[][] dp) {
        dp[cur][0] = 0;
        dp[cur][1] = 1;

        for(int next : adj[cur]) {
            if(next == parent) continue;

            dp(next, cur, dp);
            dp[cur][0] += dp[next][1];
            dp[cur][1] += Math.min(dp[next][0], dp[next][1]);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        n = stoi(br.readLine());
        adj = new ArrayList[n + 1];
        int[][] dp = new int[n + 1][2];

        for(int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i = 0; i < n - 1; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = stoi(stk.nextToken());
            int b = stoi(stk.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }

        dp(1, -1, dp);

        System.out.println(Math.min(dp[1][1], dp[1][0]));
    }
}
