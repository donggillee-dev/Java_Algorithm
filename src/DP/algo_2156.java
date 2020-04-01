package DP;

import java.io.*;

public class algo_2156 {
    static int[] DP;
    static int[] An;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int ans = Integer.MIN_VALUE;
        N = Integer.parseInt(br.readLine());
        DP = new int[N + 1];
        An = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            An[i] = Integer.parseInt(br.readLine());
        }

        DP[1] = An[1];

        if(N >= 2) {
            DP[2] = DP[1] + An[2];
            for(int i = 3; i <= N; i++) {
                DP[i] = Math.max(DP[i - 2] + An[i], DP[i - 3] + An[i - 1] + An[i]); //전이나 전전의 포도주를 마시고 현재의 포도주를 마셨을때 비교
                DP[i] = Math.max(DP[i - 1], DP[i]);//현재의 포도주를 마실지 안마실지 비교
            }
        }
        for(int i = 1; i <= N; i++) {
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
