package BOJ.BOJ_StepByStep.Silver;

import java.io.*;
import java.util.StringTokenizer;

public class algo_1535 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] L = new int[N + 1];
        int[] J = new int[N + 1];
        int[][] DP = new int[N + 1][101];
        boolean[] visited = new boolean[N];

        StringTokenizer stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            L[i] = Integer.parseInt(stk.nextToken());
        }
        stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            J[i] = Integer.parseInt(stk.nextToken());
        }

        for(int idx = 1; idx <= N; idx++) {
            for(int i = 1; i < 100; i++) {
                if(i >= L[idx]) {
                    DP[idx][i] = Math.max(J[idx] + DP[idx - 1][i - L[idx]], DP[idx - 1][i]);
                } else {
                    DP[idx][i] = DP[idx - 1][i];
                }
            }
        }

        sb.append(DP[N][99]).append("\n");
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}

