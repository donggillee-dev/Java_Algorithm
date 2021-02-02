package CodingTest.SW_Maestro;

import java.io.*;
import java.util.StringTokenizer;

public class Second_Test1 {
    static int N, ans = Integer.MIN_VALUE;
    static int[] Sweet, DP;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        Sweet = new int[N];
        DP = new int[N];
        StringTokenizer stk = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            Sweet[i] = Integer.parseInt(stk.nextToken());
        }

        DP[0] = Sweet[0];
        for(int i = 1; i < N; i++) {
            if(Sweet[i] < Sweet[i] + DP[i - 1]) DP[i] = Sweet[i] + DP[i - 1];
            else DP[i] = Sweet[i];
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
