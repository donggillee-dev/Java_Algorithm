package BOJ.BruteForce.Silver;

import java.io.*;
import java.util.StringTokenizer;

public class boj_1182 {
    private static int[] arr;
    private static int answer, N, S;
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        S = Integer.parseInt(stk.nextToken());
        arr = new int[N];

        stk = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        Permutation(0, 0, 0);

        sb.append(answer);
        bw.write(String.valueOf(sb));
        bw.close();
        br.close();
    }
    private static void Permutation(int cnt, int sum, int idx) {
        if(sum == S) {
            if(cnt != 0)
                answer++;
            if(idx >= N) return;
        }

        for(int i = idx; i < N; i++) {
            Permutation(cnt + 1, sum + arr[i], i + 1);
        }
    }
}
