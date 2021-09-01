package BOJ.DP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Logic
//현재 높이 i에서 필요한 자동차의 개수는
//0부터 i - 2까지의 필요한 각 자동차의 개수를 * 2해서 더해준 값과 같다
//그림으로 생각해보자~

public class boj_12888 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] dp = new long[61];

        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 3;

        for(int i = 3; i <= n; i++) {
            dp[i] = 1;
            for(int j = 0; j <= i - 2; j++) {
                dp[i] += (dp[j] * 2);
            }
        }

        System.out.println(dp[n]);
    }
}
