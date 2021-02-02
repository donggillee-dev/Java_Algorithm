package CodingTest.SW_Maestro;
import java.io.*;
import java.util.*;

public class Second_Imitaion_Test1 {
    static int N, K, ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken());
        stk = new StringTokenizer(br.readLine());

        for(int i = 0; i <= K; i++) {
            int idx = i, val = 1;

            while(idx <= N - 1) {
                val++;
                idx += ( K - 1 );

            }
            if(val < ans) ans = val;
        }
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}
