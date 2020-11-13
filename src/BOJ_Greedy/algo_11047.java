package BOJ_Greedy;

import java.io.*;
import java.util.*;

public class algo_11047 {
    static int N, K, ans = 0;
    static int[] An;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken());
        An = new int[N];
        for(int i = 0; i < N; i++) {
            An[i] = Integer.parseInt(br.readLine());
        }
        int tmp = N - 1;

        while(K > 0) {
            if(K < An[tmp]) tmp--;
            else {
                while(K >= An[tmp]) {
                    ans++;
                    K -= An[tmp];
                }
            }
        }
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}
