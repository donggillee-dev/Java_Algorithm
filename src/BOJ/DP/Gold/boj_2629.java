package BOJ.DP.Gold;

import java.io.*;
import java.util.StringTokenizer;

public class boj_2629 {
    private static int N;
    private static int[] arr;
    private static boolean[][] DP;

    private static void brute(int cnt, int weight) {
        if (cnt > N || DP[cnt][weight]) return;

        DP[cnt][weight] = true;
        brute(cnt + 1, weight + arr[cnt]);
        brute(cnt + 1, Math.abs(weight - arr[cnt]));
        brute(cnt + 1, weight);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        StringTokenizer stk = new StringTokenizer(br.readLine());
        arr = new int[N + 1];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        DP = new boolean[N + 1][400001];
        brute(0, 0);

        int cnt = Integer.parseInt(br.readLine());
        stk = new StringTokenizer(br.readLine());

        while (cnt-- > 0) {
            int num = Integer.parseInt(stk.nextToken());
            if(!DP[N][num])sb.append("N").append(" ");
            else sb.append("Y").append(" ");
        }

        System.out.println(sb);
    }
}