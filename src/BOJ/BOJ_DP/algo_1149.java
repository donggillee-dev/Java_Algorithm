package BOJ.BOJ_DP;

import java.io.*;
import java.util.*;

public class algo_1149 {
    static int[][] RGB = new int[1001][4];
    static int[][] Cost = new int[1001][4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;
        int N = Integer.parseInt(br.readLine());
        int ans = 0;
        stk = new StringTokenizer(br.readLine());

        RGB[1][1] = Integer.parseInt(stk.nextToken());
        RGB[1][2] = Integer.parseInt(stk.nextToken());
        RGB[1][3] = Integer.parseInt(stk.nextToken());
        for(int i = 2; i <= N; i++) {
            stk = new StringTokenizer(br.readLine());
            Arrays.fill(RGB[i], Integer.MAX_VALUE);
            for(int j = 1; j <= 3; j++) {
                Cost[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        ans = DP(N);
        sb.append(ans).append("\n");

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }

    private static int DP(int N) {
        int ans = Integer.MAX_VALUE;
        for(int i = 2; i <= N; i++) {
            for(int j = 1; j <= 3; j++) {
                if(RGB[i - 1][j] != Integer.MAX_VALUE) {
                    for(int k = 1; k <= 3; k++) {
                        if(j != k && RGB[i - 1][j] + Cost[i][k] < RGB[i][k]) {
                            RGB[i][k] = RGB[i - 1][j] + Cost[i][k];
                        }
                    }
                }
            }
        }
        for(int i = 1; i <= 3; i++) {
            if(RGB[N][i] < ans) ans = RGB[N][i];
        }
        return ans;
    }
}
