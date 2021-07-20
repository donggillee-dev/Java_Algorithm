package BOJ.DP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Lobic
//잠화식에서 계속 꼬여서 힌트를 보고 푼 문제
//각 라운드에서 어떤 팀이 못넣었냐 넣었냐를 판별해서 진행해 나가야되는게 요점

//풀이 시간 : 45분

public class boj_1344 {
    private static final boolean[] prime = {false, false, true, true, false, true, false, true, false, false, false, true, false, true, false, false, false, true, false};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int N = 19;
        double a = Double.parseDouble(br.readLine()) / 100;
        double b = Double.parseDouble(br.readLine()) / 100;
        double ans = 0;
        double[][][] dp = new double[N][N][N]; //각 라운드별 골을 얼마씩 넣었는지

        dp[1][0][0] = (1 - a) * (1 - b); //둘 다 못 넣는 경우
        dp[1][0][1] = (1 - a) * b; //b는 넣는 경우
        dp[1][1][0] = a * (1 - b); //a는 넣는 경우
        dp[1][1][1] = a * b; //a, b 둘 다 넣는 경우

        for(int round = 2; round < N; round++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    //둘 다 못 넣은 경우
                    dp[round][i][j] += dp[round - 1][i][j] * (1 - a) * (1 - b);
                    if(i != 0) //a는 꼭 넣음
                        dp[round][i][j] += dp[round - 1][i - 1][j] * a * (1 - b);
                    if(j != 0) //b는 꼭 넣음
                        dp[round][i][j] += dp[round - 1][i][j - 1] * b * (1 - a);
                    if(i != 0 && j != 0) //둘 다 넣은 경우
                        dp[round][i][j] += dp[round - 1][i - 1][j - 1] * a * b;
                }
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(prime[i] || prime[j]) ans += dp[N - 1][i][j];
            }
        }

        System.out.println(ans);
    }
}
