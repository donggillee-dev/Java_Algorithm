package BOJ.DP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//dp를 이용하여 풀 수 있는 문제
//비행기의 고도와 현재 거리를 이용해서 dp배열 생성
//dp[d][0] => 도착지에 고도 0
//dp[d][height] = dp[d - 1][height + 1] + dp[d - 1][height - 1]
//이 점화식으로 풀 수 있다


//다시 풀기 완료!!

public class boj_22236 {
    private static int d, m;
    private static long[][] dp = new long[4001][4001];

    private static int stoi(String str) {
        return Integer.parseInt(str);
    }

    private static void init() {
        for(int i = 0; i <= 4000; i++) {
            for(int j = 0; j <= 4000; j++) {
                dp[i][j] = -1;
            }
        }
    }

    private static long calc(int idx, int height) {
        if(idx > 0 && idx < d && height <= 0) return 0;

        if(idx == 0) {
            if(height != 0) return 0;
            else return 1;
        }

        if(dp[idx][height] != -1) return dp[idx][height];

        dp[idx][height] = 0;

        return (dp[idx][height] += (calc(idx - 1, height - 1) + calc(idx - 1, height + 1))) % m;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        d = stoi(stk.nextToken());
        m = stoi(stk.nextToken());

        init();

        System.out.println(calc(d, 0));

    }
}
