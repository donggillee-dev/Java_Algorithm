package BOJ.TSP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//Logic
//비트마스킹과 TSP알고리즘을 접목시켜 풀 수 있는 문제이다
//TSP 특성상 어디를 출발지로 하던 도착지와 동일해지기떄문에 어디서 출발해도 상관 없음
//풀이 시간 : 30분

public class boj_16991 {
    private static final double inf = 987654321;
    private static int n;
    private static double[][] map, dp;
    private static class Info {
        int x, y;
        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static void initMap(Info[] input) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j) continue;

                Info node1 = input[i];
                Info node2 = input[j];
                int x_dist = Math.abs(node1.x - node2.x);
                int y_dist = Math.abs(node1.y - node2.y);

                map[i][j] = Math.sqrt(Math.pow(x_dist, 2) + Math.pow(y_dist, 2));
            }
        }
    }
    private static double tsp(int visited, int cur) {
        if(visited == ((1 << n) - 1)) {
            if(map[cur][0] != 0) return map[cur][0];
            else return inf;
        }

        if(dp[visited][cur] != inf) return dp[visited][cur];

        for(int i = 0; i < n; i++) {
            if((visited & (1 << i)) != 0 || map[cur][i] == 0) continue;
            dp[visited][cur] = Math.min(dp[visited][cur], tsp(visited | (1 << i), i) + map[cur][i]);
        }

        return dp[visited][cur];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        n = Integer.parseInt(br.readLine());
        map = new double[n][n];
        dp = new double[(1 << n)][n];
        Info[] input = new Info[n];

        for(int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            input[i] = new Info(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()));
        }

        initMap(input);
        int length = dp.length;

        for(int i = 0; i < length; i++) {
            Arrays.fill(dp[i], inf);
        }

        tsp(1, 0);
        System.out.print(dp[1][0]);
    }
}
