package BOJ.DP.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1309 {
    private static final int mod = 9901;
    private static int solution(int n, int[][] dp) {
        for(int i = 1; i < n; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % mod;
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % mod;
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % mod;
        }

        return (dp[n - 1][0] + dp[n - 1][1] + dp[n - 1][2]) % mod;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n][3]; //n행의 사자를 아예 안 넣는 경우, 왼쪽, 오른쪽에 넣는 경우

        dp[0][0] = dp[0][1] = dp[0][2] = 1;

        System.out.println(solution(n, dp));
    }
}
