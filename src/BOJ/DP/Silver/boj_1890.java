package BOJ.DP.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//Logic
//DP 문제, bottom-up 방식으로 해결했다
//다만 초기의 dp배열의 값을 -1로 해주지 않고 기저조건을 0으로 하게될 경우 런타임에러가 발생한다
    //아마도 재귀함수를 탈출하지 못해서 그런것 같다 배열의 값이 0이기에
//풀이 시간 : 15분

public class boj_1890 {
    private static int n;
    private static long sol(int row, int col, int[][] arr, long[][] dp) {
        if(row == n - 1 && col == n - 1) {
            dp[row][col] = 1;
            return 1;
        }
        if(row < 0 || col < 0 || row >= n || col >= n) return 0;
        if(dp[row][col] != -1) return dp[row][col];

        dp[row][col] = 0;

        dp[row][col] += sol(row + arr[row][col], col, arr, dp);
        dp[row][col] += sol(row, col + arr[row][col], arr, dp);

        return dp[row][col];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        long[][] dp = new long[n][n];

        for(int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            Arrays.fill(dp[i], -1);
            for(int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        System.out.println(sol(0, 0, arr, dp));
    }
}
