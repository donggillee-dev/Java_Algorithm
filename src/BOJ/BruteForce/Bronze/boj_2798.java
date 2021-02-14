package BOJ.BruteForce.Bronze;

import java.io.*;
import java.util.StringTokenizer;

public class boj_2798 {
    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stk.nextToken()), M = Integer.parseInt(stk.nextToken());
        int[] card = new int[N];
        int MAX = Integer.MIN_VALUE, tmp = 0;

        stk = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < N; i++) {
            card[i] = Integer.parseInt(stk.nextToken());
        }

        for(int i = 0; i < N - 2; i++) {
            for(int j = i+1; j < N - 1; j++) {
                for(int k = j+1; k < N; k++) {
                    tmp = card[i] + card[j] + card[k];
                    if(tmp >= MAX && tmp <= M) MAX = tmp;
                }
            }
        }
        sb.append(MAX).append("\n");
        bw.write(String.valueOf(sb));
        br.close();
        bw.flush();
        bw.close();
        return;
    }
}
