package BOJ.DP.Silver;
import java.io.*;
import java.util.*;

public class boj_1010 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        StringTokenizer stk;

        for(int i = 0; i < T; i++) {
            stk = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(stk.nextToken());
            int M = Integer.parseInt(stk.nextToken());

            int result = 1;

            for(int j = 1; j <= N; j++) {
                result = result*(M-j+1)/j;
            }
            sb.append(result).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
