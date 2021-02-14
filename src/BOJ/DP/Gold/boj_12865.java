package BOJ.DP.Gold;

import java.io.*;
import java.util.*;

public class boj_12865 { //Knapsack Problem
    static int[][] KnapSack;
    static int[][] Prd;
    static int PrdNum = 0, MaxWeight = 0, ans = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        PrdNum = Integer.parseInt(stk.nextToken());
        MaxWeight = Integer.parseInt(stk.nextToken());
        KnapSack = new int[PrdNum + 1][MaxWeight + 1];
        Prd = new int[PrdNum + 1][2];

        Arrays.fill(KnapSack[0], 0);//행, 열 0인 부분 0으로 초기화
        for(int i = 0; i < PrdNum; i++) {
            KnapSack[i][0] = 0;
        }

        for(int i = 1; i <= PrdNum; i++) {
            stk = new StringTokenizer(br.readLine());
            Prd[i][0] = Integer.parseInt(stk.nextToken());
            Prd[i][1] = Integer.parseInt(stk.nextToken());
        }

        for(int i = 1; i <= PrdNum; i++) {
            for(int j = 1; j <= MaxWeight; j++) {
                if(Prd[i][0] <= j) {
                    KnapSack[i][j] = Math.max(Prd[i][1] + KnapSack[i - 1][j - Prd[i][0]], KnapSack[i - 1][j]);
                } else {
                    KnapSack[i][j] = KnapSack[i - 1][j];
                }
            }
        }

        for(int i = 1; i <= PrdNum; i++) {
            if(ans < KnapSack[i][MaxWeight]) ans = KnapSack[i][MaxWeight];
        }

        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}
