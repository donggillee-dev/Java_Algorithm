package BOJ.DP.Gold;

import java.io.*;
import java.util.*;
//Logic
//특정 행의 문이 닫힌 모양은
// 0 : 왼쪽이 닫혀있거나, 1 : 오른쪽이 닫혀있거나, 2 : 둘 다 열려있거나
// dp[i][j][k] 배열을 생성 => 이 배열은 i번째행까지 j개의 문을 닫았을시에 위 k모양일 경우 최대의 값
// 즉 특정 행의 미술관 문 모양이 0과 같다면 i - 1에는 0, 2 두개만 올 수 있음
// 1과 같다면 i - 1에는 1, 2 둘 만 올 수 있음

//위와 같은 방식으로 dp[i][j][k]에 대해서 점화식을 세울 수 있음
//그리고 K개의 문을 닫는 것에 대해서 특정행까지 0개의 문을 닫을수도 있으므로 해당 for문은 0부터 K까지
//즉, 모양이 0, 1번인 케이스에 대해서 예외처리 필요
//또한, 모양이 2번인 케이스에 대해서 i번째 행까지 j개의 문을 닫는데 i == j 만큼 문을 닫는 경우는 있을 수 없으므로 예외 처리
public class boj_10476 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken()), K = Integer.parseInt(stk.nextToken());
        int[][] gallery = new int[N + 1][2];
        int[][][] dp = new int[201][201][3];

        for (int i = 1; i <= N; i++) {
            stk = new StringTokenizer(br.readLine());
            gallery[i][0] = Integer.parseInt(stk.nextToken());
            gallery[i][1] = Integer.parseInt(stk.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                if (j != 0) {
                    dp[i][j][0] = Math.max(dp[i - 1][j - 1][0], dp[i - 1][j - 1][2]) + gallery[i][1];
                    dp[i][j][1] = Math.max(dp[i - 1][j - 1][1], dp[i - 1][j - 1][2]) + gallery[i][0];
                }
                if(i != j) {
                    dp[i][j][2] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1]);
                    dp[i][j][2] = Math.max(dp[i][j][2], dp[i - 1][j][2]);
                    dp[i][j][2] += (gallery[i][0] + gallery[i][1]);
                }
            }
        }

        System.out.print(Math.max(Math.max(dp[N][K][0], dp[N][K][1]), dp[N][K][2]));
    }
}
