package BOJ.BruteForce.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//Logic
//브루트 포스로 풀 수 있을거라고 생각했지만... 메모이제이션으로 해서 풀었음
//아무리 생각해도 13!에 대해서는 최적의 방법이 생각나지 않음...
//풀이 시간 : 120분
public class boj_17610 {
    private static int N;
    private static int[] arr;
    private static boolean[][] DP;
    private static void brute( int cnt, int weight) {
        if(cnt > N || DP[cnt][weight]) return;

        DP[cnt][weight] = true;
        brute(cnt + 1, weight + arr[cnt]);
        brute(cnt + 1, Math.abs(weight - arr[cnt]));
        brute(cnt + 1, weight);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer stk = new StringTokenizer(br.readLine());
        arr = new int[N + 1];
        int sum = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
            sum += arr[i];
        }
        DP = new boolean[N + 1][sum + 1];
        brute(0, 0);

        int answer = 0;
        for(int i = 1; i <= sum; i++)
            if(!DP[N][i]) answer++;
        System.out.print(answer);
    }
}
