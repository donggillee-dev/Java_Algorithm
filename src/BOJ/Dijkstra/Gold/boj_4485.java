package BOJ.Dijkstra.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//Logic
//다익스트라로 해결할 수 있는 문제
//풀이 시간 : 20분

public class boj_4485 {
    private static int[] dRow = {-1, 0, 1, 0};
    private static int[] dCol = {0, -1, 0, 1};

    private static class Info implements Comparable<Info> {
        int row, col, cost;

        public Info(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }

        @Override
        public int compareTo(Info o) {
            return this.cost - o.cost;
        }
    }

    private static void init(int[][] map, int[][] dp) {
        for (int i = 0; i < 130; i++) {
            Arrays.fill(map[i], 0);
            Arrays.fill(dp[i], 987654321);
        }
    }

    private static int dijk(int n, int[][] map, int[][] dp) {
        int ans = Integer.MAX_VALUE;
        dp[0][0] = map[0][0];
        PriorityQueue<Info> pq = new PriorityQueue<>();

        pq.add(new Info(0, 0, dp[0][0]));

        while(!pq.isEmpty()) {
            Info inf = pq.poll();

            if(inf.col == n - 1 && inf.row == n - 1) {
                ans = Math.min(ans, inf.cost);
                continue;
            }

            if(dp[inf.row][inf.col] < inf.cost) continue;

            for(int i = 0; i < 4; i++) {
                int nr = inf.row + dRow[i];
                int nc = inf.col + dCol[i];

                if(nr < 0 || nc < 0 || nr >= n || nc >= n) continue;

                int nextCost = inf.cost + map[nr][nc];

                if(nextCost < dp[nr][nc]) {
                    dp[nr][nc] = nextCost;
                    pq.add(new Info(nr, nc, nextCost));
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;

        int n = 0, idx = 1;
        int[][] map = new int[130][130];
        int[][] dp = new int[130][130];

        while (true) {
            n = Integer.parseInt(br.readLine());

            if (n == 0) break;

            init(map, dp);

            for (int i = 0; i < n; i++) {
                stk = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(stk.nextToken());
                }
            }

            sb.append("Problem " + (idx++) + ": " + dijk(n, map, dp)).append("\n");
        }

        System.out.print(sb);
    }
}
