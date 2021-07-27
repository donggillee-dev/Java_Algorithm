package BOJ.TSP.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//Logic
//그냥 외판원 순회 문제
//풀이 시간 : 20분

public class boj_10971 {
    private static int n;
    private static final int max = 987654321;
    private static int stoi(String str) {
        return Integer.parseInt(str);
    }
    private static int tsp(int visited, int cur, int[][] arr, int[][] dp) {
        if(visited == ((1 << n) - 1)) {
            if(arr[cur][0] != 0) return arr[cur][0];
            return max;
        }

        if(dp[visited][cur] != max) return dp[visited][cur];

        for(int i = 0; i < n; i++) {
            if((visited & (1 << i)) != 0 || arr[cur][i] == 0) continue;

            dp[visited][cur] = Math.min(dp[visited][cur], tsp(visited | (1 << i), i, arr, dp) + arr[cur][i]);
        }

        return dp[visited][cur];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        int[][] dp = new int[1 << n][n];

        for(int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                arr[i][j] = stoi(stk.nextToken());
            }
        }

        for(int i = 0; i < (1 << n); i++) {
            Arrays.fill(dp[i], max);
        }

        tsp(1, 0, arr, dp);
        System.out.println(dp[1][0]);
    }
}
