package BOJ.DP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_5557 {
    private static int n;
    private static int[] arr;
    private static long[][] dp; //i번째 값 더해서 나온 합 까지의 경우의 수
    private static long sol(int depth, int sum) {
        if(depth == n - 1) {
            if(sum == arr[n - 1]) return 1; //등식이 성립하면 return 1
            else return 0;
        }

        if(dp[depth][sum] != -1) return dp[depth][sum]; //메모이제이션

        long mem = 0;

        if(sum + arr[depth] <= 20) { //합
            mem += sol(depth + 1, sum + arr[depth]);
        }

        if(sum - arr[depth] >= 0) { //차
            mem += sol(depth + 1, sum - arr[depth]);
        }

        return dp[depth][sum] = mem;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new long[n][21];

        StringTokenizer stk = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(sol(1, arr[0]));
    }
}
