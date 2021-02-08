package SWEA.D3;

import java.io.*;
import java.util.StringTokenizer;

public class SpotMart {
    static int answer = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int TestCase = Integer.parseInt(br.readLine());
        StringTokenizer stk;

        for(int tc = 1; tc <= TestCase; tc++) {
            stk = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(stk.nextToken());
            int M = Integer.parseInt(stk.nextToken());
            int[] arr = new int[N];
            stk = new StringTokenizer(br.readLine());
            for(int cnt = 0; cnt < N; cnt++) {
                arr[cnt] = Integer.parseInt(stk.nextToken());
            }
            getSum(arr, 0, 0, 0, M);

            sb.append("#"+tc+" ");
            if(answer < 0) sb.append(-1).append("\n");
            else sb.append(answer).append("\n");

            answer = Integer.MIN_VALUE;
        }

        bw.write(sb.toString());
        bw.close();
        br.close();

    }
    private static void getSum(int[] arr, int start_idx, int depth, int sum, int max) {
        if(depth == 2) {
            if(sum > answer && sum <= max) answer = sum;
            return;
        }

        for(int i = start_idx; i < arr.length; i++) {
            getSum(arr, i + 1, depth + 1, sum + arr[i], max);
        }

        return;
    }
}
