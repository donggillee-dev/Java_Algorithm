package BOJ.DP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//점화식을 이용해 현재 가지고 있는 문자의 개수로 만들 수 있는 문자열 개수를 확인
//확인된 개수를 가지고 k번째보다 더 많은걸 만들 수 있는지, 못만드는지로 a를 붙일지 z를 붙일지를 선택
//다시 풀어야함

public class boj_1256 {
    private static StringBuilder sb = new StringBuilder();

    private static long mem(int n, int m, long[][] dp) {
        if (n == 0 || m == 0) return 1;
        if (dp[n][m] != 0) return dp[n][m];

        return dp[n][m] = Long.min(mem(n - 1, m, dp) + mem(n, m - 1, dp), 1000000001);
    }

    private static void getAns(int n, int m, long k, long[][] dp) {
        if (n == 0) {
            for (int i = 0; i < m; i++) {
                sb.append('z');
            }

            return;
        } else if (m == 0) {
            for (int i = 0; i < n; i++) {
                sb.append('a');
            }

            return;
        }

        long cnt = mem(n - 1, m, dp);

        if (cnt >= k) { //만들 수 있는 경우의 수보다 아래의 번째수 문자열을 접근하는 경우
            sb.append('a');
            getAns(n - 1, m, k, dp);
        } else { //가지고 있는 문자 갯수로 만들 수 있는 문자열 경우의 수보다 접근하려는 번쨰수가 더 큰거라면 z를 붙여야함
            sb.append('z');
            getAns(n, m - 1, k - cnt, dp);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        long k = Long.parseLong(stk.nextToken());
        long[][] dp = new long[n + 1][m + 1];

        getAns(n, m, k, dp);

        if (mem(n, m, dp) < k) {
            System.out.println(-1);
        } else {
            System.out.print(sb);
        }
    }
}
