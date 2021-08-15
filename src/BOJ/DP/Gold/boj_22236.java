package BOJ.DP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic

//dp[i][j] = dp[i - 1][j + 1] + dp[i - 1][j - 1]
//현재 인덱스에 현재 고도까지의 경우의 수는
//이전 인덱스에서 고도 - 1, 고도 + 1 한거 두개 더한값과 동일

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

        if(idx > 0 && idx < d && height <= 0) return 0; //도착지에 도달하기 전에 고도가 땅에 닿는 경우

        if(idx == 0) { //도착지에 도달했을떄
            if(height == 0) return 1; //고도가 땅인경우는 1리턴
            else return 0;
        }

        if(dp[idx][height] != -1) return dp[idx][height]; //메모이제이션
        else dp[idx][height] = 0; //그렇지 않다면 0으로 초기화

        dp[idx][height] += calc(idx - 1, height - 1) + calc(idx - 1, height + 1);

        return dp[idx][height] % m;
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
