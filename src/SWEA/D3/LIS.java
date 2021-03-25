package SWEA.D3;

import java.io.*;
import java.util.StringTokenizer;

public class LIS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;
        int TestCase = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= TestCase; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N], dp = new int[N];
            int ans = -1;

            stk = new StringTokenizer(br.readLine());

            for(int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(stk.nextToken());
            }

            for(int i = 0; i < N; i++) {
                dp[i] = 1;
                for(int j = 0; j < i; j++) {
                    if(arr[i] > arr[j] && dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        ans = Math.max(dp[i], ans);
                    }
                }
            }
            sb.append("#" + tc + " " + ans).append("\n");
        }
        System.out.print(sb);
    }
}
