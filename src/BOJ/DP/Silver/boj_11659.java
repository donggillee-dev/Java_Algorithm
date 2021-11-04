package BOJ.DP.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//누적합을 이용하여 구간합을 구한다

public class boj_11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int[] arr = new int[N + 1];

        stk = new StringTokenizer(br.readLine());
        arr[1] = Integer.parseInt(stk.nextToken());

        for(int i = 2; i <= N; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
            arr[i] += arr[i - 1];
        }

        while(M --> 0) {
            stk = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(stk.nextToken()) - 1;
            int j = Integer.parseInt(stk.nextToken());

            sb.append(arr[j] - arr[i]).append("\n");
        }

        System.out.print(sb);
    }
}
