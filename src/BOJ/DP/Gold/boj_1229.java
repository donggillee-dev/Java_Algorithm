package BOJ.DP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//Logic
//아슬아슬하게 통과한 문제
//모든 n은 빅뱅수끼리 표현이 가능하다
//가장 적은 횟수로 표현하는 방법은
//이전까지의 빅뱅수에서 더해보는법

//풀이 시간 : 35분

public class boj_1229 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        int[] bigArr = new int[1000];

        if(n >= 1 && n <= 5) {
            System.out.println(n);
            System.exit(0);
        }

        int num = 1;
        int width = 5;
        int idx = 0;
        while(num <= 1000000) {
            bigArr[idx] = num;
            num += width;
            width += 4;
            idx++;
        }

        Arrays.fill(dp, 10);

        for(int i = 1; i <= 5; i++) {
            dp[i] = i;
        }

        dp[6] = 1;

        for(int i = 6, bigIdx = 2; i <= n; i++) {
            if(i == bigArr[bigIdx]) {
                dp[i] = 1;
                bigIdx++;
            } else {
                for(int j = bigIdx - 1; j >= 0; j--) {
                    int diff = i - bigArr[j];
                    dp[i] = Math.min(dp[i], dp[diff] + dp[bigArr[j]]);
                }
            }
        }
        System.out.println(dp[n]);
    }
}
