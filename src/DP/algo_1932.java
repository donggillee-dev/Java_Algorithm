package DP;

import java.io.*;
import java.util.*;

public class algo_1932 {
    static int[][] TriAnInfo;
    static int[][] DPArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;
        int N = Integer.parseInt(br.readLine());
        int ans = Integer.MIN_VALUE;

        TriAnInfo = new int[N][N];
        DPArr = new int[N][N];

        for(int i = 0; i < N; i++) {
            int j = 0;
            stk = new StringTokenizer(br.readLine());
            Arrays.fill(DPArr[i], Integer.MIN_VALUE);

            while(stk.hasMoreTokens()) {
                TriAnInfo[i][j] = Integer.parseInt(stk.nextToken());
                j++;
            }
        }
        DPArr[0][0] = TriAnInfo[0][0];
        DPArr[1][0] = TriAnInfo[0][0] + TriAnInfo[1][0];
        DPArr[1][1] = TriAnInfo[0][0] + TriAnInfo[1][1];

        DP(N);

        for(int i = 0; i < N; i++) {
            if(ans < DPArr[N - 1][i]) ans = DPArr[N - 1][i];
        }
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }

    private static void DP(int N) {
        for(int i = 2; i < N; i++) {
            for(int j = 0; j <= i; j++) {
                if(j == 0) {
                    DPArr[i][j] = DPArr[i - 1][j] + TriAnInfo[i][j];
                } else if(i == j) {
                    DPArr[i][j] = DPArr[i - 1][j - 1] + TriAnInfo[i][j];
                } else {
                    if(DPArr[i][j] < DPArr[i - 1][j - 1] + TriAnInfo[i][j]) DPArr[i][j] = DPArr[i - 1][j - 1] + TriAnInfo[i][j];
                    if(DPArr[i][j] < DPArr[i - 1][j] + TriAnInfo[i][j]) DPArr[i][j] = DPArr[i - 1][j] + TriAnInfo[i][j];
                }
            }
        }
        return;
    }
}
