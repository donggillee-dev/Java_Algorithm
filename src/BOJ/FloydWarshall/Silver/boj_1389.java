package BOJ.FloydWarshall.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//플로이드 와샬, bfs 두개로 돌릴 수 있음
//fw로 풀이

public class boj_1389 {
    private static int N, M;

    private static void initArr(int[][] arr, BufferedReader br, StringTokenizer stk) throws IOException {
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                arr[i][j] = 987654321;
            }
        }

        for(int i = 0; i < M; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());

            arr[a][b] = 1;
            arr[b][a] = 1;
        }
    }

    private static void floyd(int[][] arr) {
        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    if(i == j) continue;

                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        int ans = 0, length = 987654321;
        int[][] arr = new int[N + 1][N + 1];

        initArr(arr, br, stk);
        floyd(arr);

        for(int i = 1; i <= N; i++) {
            int sum = 0;
            for(int j = 1; j <= N; j++) {
                if(i == j) continue;
                sum += arr[i][j];
            }

            if(length > sum) {
                length = sum;
                ans = i;
            }
        }

        System.out.println(ans);
    }
}
