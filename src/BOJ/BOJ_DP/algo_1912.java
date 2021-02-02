package BOJ.BOJ_DP;
import java.io.*;
import java.util.*;

public class algo_1912 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int ans = Integer.MIN_VALUE;
        int N = Integer.parseInt(br.readLine());
        int[] An = new int[N];
        int[] DP = new int[N];
        StringTokenizer stk = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            An[i] = Integer.parseInt(stk.nextToken());
            DP[i] = An[i];
        }

        for(int i = 0; i < N; i++) {
            for(int j = i + 1; j < N; j++) {
                if(DP[j] < DP[j - 1] + An[j]) {
                    DP[j] = DP[j - 1] + An[j];
                } else break;
            }
        }
        for(int i = 0; i < N; i++) {
            if(DP[i] > ans) ans = DP[i];
        }
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}
