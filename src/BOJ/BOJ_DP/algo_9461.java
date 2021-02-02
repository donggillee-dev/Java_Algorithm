package BOJ.BOJ_DP;

import java.io.*;

public class algo_9461 {
    static long[] Padovan = new long[101];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int N = 0;

        Padovan[1] = 1;
        Padovan[2] = 1;
        Padovan[3] = 1;

        for(int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            DP(N);
            sb.append(Padovan[N]).append("\n");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void DP(int N) {
        for(int i = 3; i <= N; i++) {
            Padovan[i] = Padovan[i - 3] + Padovan[i - 2];
        }
        return;
    }
}
