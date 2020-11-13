package BOJ_DP;

import java.io.*;

public class algo_1003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int n;
        int[][] OneZero = new int[41][2];

        OneZero[0][0] = 1;
        OneZero[0][1] = 0;
        OneZero[1][0] = 0;
        OneZero[1][1] = 1;
        OneZero[2][0] = 1;
        OneZero[2][1] = 1;
        OneZero[3][0] = 1;
        OneZero[3][1] = 2;
        OneZero[4][0] = 2;
        OneZero[4][1] = 3;
        OneZero[5][0] = 3;
        OneZero[5][1] = 5;
        for(int i = 6; i < 41; i++) {
            OneZero[i][0] = OneZero[i - 1][1];
            OneZero[i][1] = OneZero[i - 1][0] + OneZero[i - 1][1];
        }

        for(int i = 0; i < N; i++) {
            n = Integer.parseInt(br.readLine());
            sb.append(OneZero[n][0]).append(" ").append(OneZero[n][1]).append("\n");
        }
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}
