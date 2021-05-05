package BOJ.DP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//Logic
//i, j에서 만들 수 있는 가장 큰 정사각형의 크기는 위, 왼쪽, 대각 왼쪽 위에 대해서 가장 작은 정사각형의 길이를 가져와서
//1을 더해줘야함
//그 이유는 정사각형의 특성상 가로가 늘어나면 세로도 늘어남 => 작은쪽에 맞춰서 1을 증가시킬 수 있는지를 체크
//작은 쪽에 초점을 맞춰야하는 문제이다
//큰 쪽에 맞추게 되면
// 11
//이와 같은 상황에서 (1, 2)에서 만들 수 있는 가장 큰 정사각형의 한변 길이는 2가 된다

//풀이 시간 : 60분
public class boj_1915 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(stk.nextToken());
        int col = Integer.parseInt(stk.nextToken());
        int[][] dp = new int[row + 1][col + 1];
        int ans = 0;

        for(int i = 1; i <= row; i++) {
            String line = br.readLine();
            for(int j = 1; j <= col; j++) {
                dp[i][j] = line.charAt(j - 1) - '0';
                if(dp[i][j] != 0) {
                    int temp = Math.min(dp[i - 1][j - 1], dp[i - 1][j]);
                    dp[i][j] = Math.min(dp[i][j - 1], temp) + 1;

                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }

        System.out.println(ans * ans);
    }
}
