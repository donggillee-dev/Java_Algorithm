package SWEA.D3;

import java.io.*;
import java.util.StringTokenizer;

public class HamburgerDiet {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        StringTokenizer stk;
        for(int tc = 1; tc <= T; tc++) {
            stk = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(stk.nextToken());
            int L = Integer.parseInt(stk.nextToken());
            int[][] DP = new int[N + 1][L + 1];
            int[] cal = new int[N + 1];
            int[] del = new int[N + 1];

            for(int i = 1; i <= N; i++) {
                stk = new StringTokenizer(br.readLine());
                del[i] = Integer.parseInt(stk.nextToken());
                cal[i] = Integer.parseInt(stk.nextToken());
            }

            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= L; j++) {
                    if(cal[i] > j) {
                        DP[i][j] = DP[i - 1][j];
                    } else {
                        DP[i][j] = Math.max(DP[i - 1][j], del[i] + DP[i - 1][j - cal[i]]);
                    }
                }
            }
            sb.append("#" + tc + " ").append(DP[N][L]).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
