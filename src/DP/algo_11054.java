package DP;

import java.io.*;
import java.util.*;

public class algo_11054 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int[] Sn = new int[N];
        int[] DP1 = new int[N];
        int[] DP2 = new int[N];
        int ans = Integer.MIN_VALUE;

        Arrays.fill(DP1, 1);
        Arrays.fill(DP2, 1);

        for(int i = 0; i < N; i++) {
            Sn[i] = Integer.parseInt(stk.nextToken());
        }

        for(int i = 0; i < N; i++) {
            for(int j = i + 1; j < N; j++) {
                if(Sn[j] > Sn[i]) {
                    DP1[j] = Math.max(DP1[i] + 1, DP1[j]);
                }
            }
        }
        for(int i = N - 1; i >= 0; i--) {
            for(int j = i - 1; j >= 0; j--) {
                if(Sn[j] > Sn[i]) {
                    DP2[j] = Math.max(DP2[i] + 1, DP2[j]);
                }
            }
        }

        for(int i = 0; i < N; i++) {
            ans = Math.max(DP1[i] + DP2[i], ans);
        }

        sb.append(ans - 1).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}
