package SWEA.D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
//Logic
//다익스트라로 해결할 수 있는 문제
//우선순위 큐를 이용해서 4방 탐색을 해 나갈떄 최소비용 순으로 뽑아온다
//이동하려는 곳의 위치의 minCost값이 이때까지의 경로값보다 작을 경우에는 skip
//결과적으로 minMap의 도착지값만 리턴해주면 됨
//풀이 시간 : 15분
public class SupplyRoute {
    private static int[] dir_x = {-1, 0, 1, 0}, dir_y = {0, -1, 0, 1};

    private static int dijk(int n, int[][] map, int[][] minMap) {
        boolean[][] visited = new boolean[n][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        minMap[0][0] = 0;
        visited[0][0] = true;
        pq.add(new int[]{0, 0, map[0][0]});

        while(!pq.isEmpty()) {
            int[] inf = pq.poll();
            int r = inf[0], c = inf[1], cost = inf[2];

            if(minMap[r][c] < cost) continue;

            for(int i = 0; i < 4; i++) {
                int nr = r + dir_x[i];
                int nc = c + dir_y[i];

                if(nr < 0 || nc < 0 || nr >= n || nc >= n) continue;

                if(!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    minMap[nr][nc] = Math.min(minMap[nr][nc], cost + map[nr][nc]);
                    pq.add(new int[]{nr, nc, minMap[nr][nc]});
                }
            }
        }
        return minMap[n - 1][n - 1];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= t; tc++) {
            int n = Integer.parseInt(br.readLine());
            int[][] map = new int[n][n];
            int[][] minMap = new int[n][n];

            for(int i = 0; i < n; i++) {
                String line = br.readLine();
                for(int j = 0; j < n; j++) {
                    map[i][j] = line.charAt(j) - '0';
                    minMap[i][j] = 987654321;
                }
            }

            sb.append("#" + tc + " " + dijk(n, map, minMap)).append("\n");
        }

        System.out.print(sb);
    }
}