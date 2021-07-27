package BOJ.TSP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//Logic
//플루이드를 이용해서 a행성에서 b행성으로 가는데 가장 최단거리를 구함 => 중복이 있어도 상관없으니까
//그 배열을 기반으로 tsp를 돈다
//행성간의 비용 가격이 0 <= t <= 1000이다 그러므로 0일떄 스킵해주는 것을 빼줘야했음.... 이거땜에 시간, 틀림 엄천함

//풀이 시간 : 50분

public class boj_17182 {
    private static int n, k;
    private static final int max = 987654321;
    private static int stoi(String str) {
        return Integer.parseInt(str);
    }
    private static int tsp(int visited, int cur, int[][] arr, int[][] dp) {
        if(visited == ((1 << n) - 1)) {
            return 0;
        }

        if(dp[visited][cur] != max) return dp[visited][cur];

        for(int i = 0; i < n; i++) {
            if((visited & (1 << i)) != 0) continue;
            dp[visited][cur] = Math.min(dp[visited][cur], tsp(visited | (1 << i), i, arr, dp) + arr[cur][i]);
        }

        return dp[visited][cur];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = stoi(stk.nextToken());
        k = stoi(stk.nextToken());

        int[][] arr = new int[n][n];
        int[][] dp = new int[(1 << n)][n];
        for(int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                arr[i][j] = stoi(stk.nextToken());
            }
        }

        for(int i = 0; i < (1 << n); i++) {
            Arrays.fill(dp[i], max);
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                for(int l = 0; l < n; l++) {
                    if(arr[j][i] + arr[i][l] < arr[j][l]) {
                        arr[j][l] = arr[j][i] + arr[i][l];
                    }
                }
            }
        }

        tsp(1 << k, k, arr, dp);

        System.out.println(dp[1 << k][k]);
    }
}
