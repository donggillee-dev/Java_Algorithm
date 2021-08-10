package BOJ.DP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic

//연속합1 문제를 양방향에서 생각하면 되는 문제
//다만 ans를 초기에 dp1[0] 값으로 설정하고 왼 -> 오 방향의 부분합중 최대값으로 처리
//그리고 중간중간 하나씩 빼면 어떨지를 생각해주면 된다

//풀이 시간 : 1시간 10분

public class boj_13398 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        long[] dp1 = new long[n];
        long[] dp2 = new long[n];
        long ans = 0;
        StringTokenizer stk = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        dp1[0] = arr[0];
        ans = dp1[0];
        dp2[n - 1] = arr[n - 1];

        for(int i = 1; i < n; i++) {
            dp1[i] = Math.max(dp1[i - 1] + arr[i], arr[i]);
            ans = Math.max(ans, dp1[i]);
        }

        for(int i = n - 2; i >= 0; i--) {
            dp2[i] = Math.max(dp2[i + 1] + arr[i], arr[i]);
        }

        for(int i = 1; i < n - 1; i++) {
            ans = Math.max(ans, dp1[i - 1] + dp2[i + 1]);
        }

        System.out.println(ans);
    }
}
