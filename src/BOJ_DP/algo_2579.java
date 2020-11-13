package BOJ_DP;

import java.io.*;

public class algo_2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] Stair = new int[N + 1];
        int[] DP = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            Stair[i] = Integer.parseInt(br.readLine());
        }
        DP[1] = Stair[1];

        if(N >= 2) {
            DP[2] = DP[1] + Stair[2];
        }

        for(int i = 3; i <= N; i++) {
            DP[i] = Math.max(DP[i - 2] + Stair[i], DP[i - 3] + Stair[i - 1] + Stair[i]);
        }

        sb.append(DP[N]).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}