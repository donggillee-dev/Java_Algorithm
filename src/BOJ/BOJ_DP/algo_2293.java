package BOJ.BOJ_DP;
import java.io.*;
import java.util.*;

public class algo_2293 {
    static int N, K;
    static int[] DP = new int[10001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken());

        DP[0] = 1;

        for(int i = 0; i < N; i++) {
            int Coin = Integer.parseInt(br.readLine());

            for(int j = Coin; j <= 10000; j++) {
                DP[j] += DP[j - Coin];
            }
        }

        sb.append(DP[K]).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}
