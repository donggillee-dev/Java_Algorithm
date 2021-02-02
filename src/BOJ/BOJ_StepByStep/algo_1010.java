package BOJ.BOJ_StepByStep;
import java.io.*;
import java.util.*;

public class algo_1010 {
    static long ans = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer stk;
        for(int Case = 0; Case < T; Case++) {
            stk = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(stk.nextToken());
            int M = Integer.parseInt(stk.nextToken());
            if(N != M) {
                Solve(1, N, 0, M);
            }
            sb.append(ans).append("\n");
            ans = 1;
        }
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void Solve(int depth, int N, int start, int M) {
        int end = M - start - (N - depth);
        if(depth == N) {
            return;
        } else {
            Solve(depth + 1, N, start + 1, M);
        }
    }
}
