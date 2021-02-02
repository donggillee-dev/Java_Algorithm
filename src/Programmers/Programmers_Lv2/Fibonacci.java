package Programmers.Programmers_Lv2;
import java.io.*;

public class Fibonacci {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int answer = 0;
        int N = 0;
        int[] DP = new int[100001];

        N = Integer.parseInt(br.readLine());

        DP[0] = 0;
        DP[1] = 1;

        for(int i = 2; i <= N; i++) {
            DP[i] = (DP[i - 1] + DP[i - 2]) % 1234567;
        }

        answer = DP[N];

        sb.append(answer).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}
