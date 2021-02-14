package BOJ.LIS.Silver;

import java.io.*;
import java.util.*;

public class boj_11053 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;
        int N = Integer.parseInt(br.readLine());
        int ans = Integer.MIN_VALUE;
        int[] An = new int[N];
        int[] DP = new int[N];

        stk = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            An[i] = Integer.parseInt(stk.nextToken());
        }

        Arrays.fill(DP, 1);
        if(N >= 2) {
            if (An[0] < An[1]) {
                DP[1] = DP[0] + 1;
            } else {
                DP[1] = 1;
            }
            for (int i = 2; i < N; i++) {
                for (int j = i - 1; j >= 0; j--) {
                    if (An[i] > An[j]) {
                        DP[i] = Math.max(DP[i], DP[j] + 1);
                    }
                }
            }
        }
        for(int i = 0; i < N; i++) {
            if(ans < DP[i]) ans = DP[i];
        }
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}
