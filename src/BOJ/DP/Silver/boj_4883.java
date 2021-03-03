package BOJ.DP.Silver;

import java.io.*;
import java.util.*;

//Logic
//DP 문제
// 맨 첫번쨰 줄의 가운데에서 맨 아랫줄 가운데까지 가는 최적의 경로를 찾는 문제이다
// 다음 행에서 이전 행에서 내려올 수 있는 경로중 가장 작은DP값으로 행의 DP값을 갱신시켜주는 방법으로 진행하여 결과를 도출

//풀이 시간 : 40분
public class boj_4883 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        final long max = 987654321;

        int tc = 1;
        while(true) {
            int N = Integer.parseInt(br.readLine());
            if(N == 0) break;
            long[][] DP = new long[N][3];
            int[][] Map = new int[N][3];
            StringTokenizer stk;

            for(int i = 0; i < N; i++) {
                stk = new StringTokenizer(br.readLine());
                for(int j = 0; j < 3; j++) {
                    Map[i][j] = Integer.parseInt(stk.nextToken());
                }
            }
            DP[0][0] = max;
            DP[0][1] = Map[0][1];
            DP[0][2] = Map[0][2] + DP[0][1];

            for(int i = 1; i < N; i++) {
                DP[i][0] = MIN2(DP[i - 1][0], DP[i - 1][1]) + Map[i][0];
                DP[i][1] = MIN4(DP[i - 1][0], DP[i - 1][1], DP[i - 1][2], DP[i][0]) + Map[i][1];
                DP[i][2] = MIN3(DP[i - 1][2], DP[i - 1][1], DP[i][1]) + Map[i][2];
            }
            sb.append(tc + ". " + DP[N - 1][1]).append("\n");
            tc++;
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
    private static long MIN2(long a1, long a2) {
        if(a1 < a2) return a1;
        else return a2;
    }
    private static long MIN3(long a1, long a2, long a3) {
        long min;

        if(a1 < a2) min = a1;
        else min = a2;

        if(min > a3) min = a3;

        return min;
    }
    private static long MIN4(long a1, long a2, long a3, long a4) {
        long min1, min2;

        if(a1 < a2) min1 = a1;
        else min1 = a2;

        if(a3 < a4) min2 = a3;
        else min2 = a4;

        if(min1 < min2) return min1;
        else return min2;
    }

}
