package BOJ.DP.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//DP로 풀 수 있는 문제
//인덱스 처리때문에 예전에 애먹었는데 지금 생각해보니까 그냥 아래 오른쪽 아래만 생각하면 됨

//풀이 시간 : 20분

public class boj_1932 {
    private static int n;
    private static int stoi(String str) {
        return Integer.parseInt(str);
    }
    private static int[][] input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];

        for(int i = 0, m = 0; i < n; i++, m++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j <= m; j++) {
                arr[i][j] = stoi(stk.nextToken());
            }
        }

        return arr;
    }
    private static int solution(int[][] arr, int[][] dp) {
        int max = -1;
        dp[0][0] = arr[0][0];

        for(int i = 0, m = 1; i < n - 1; i++, m++) {
            for(int j = 0; j < m; j++) {
                dp[i + 1][j] = Math.max(dp[i][j] + arr[i + 1][j], dp[i + 1][j]);
                dp[i + 1][j + 1] = Math.max(dp[i][j] + arr[i + 1][j + 1], dp[i + 1][j + 1]);
            }
        }

        for(int i = 0; i < n; i++) {
            max = Math.max(max, dp[n - 1][i]);
        }

        return max;
    }
    public static void main(String[] args) {
        int[][] arr = null, dp = null;

        try {
            arr = input();
            dp = new int[n][n];
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(solution(arr, dp));
    }
}
