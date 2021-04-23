package BOJ.Dijkstra.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//Logic
//다익스트라를 이용하면 풀 수 있는 문제
//컴퓨터간의 의존성 즉 그래프 방향을 이용해서 최소 시간을 탐색 -> 컴퓨터가 감염되는데 s초 후니까 가장 빠르게 감염된 애부터 감염되어나가기 시작
//중요한 것은 감염된 컴퓨터의 갯수를 구할 때 초기값과 다른 것을 체크
//그리고 초기 감염 시작한 컴퓨터는 dist를 0으로 세팅
//풀이 시간 : 25분
public class boj_10282 {
    private static int[] dijk(int n, int c, int[] dist, ArrayList<int[]>[] adj) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int ans = 0;
        int cnt = 0;
        dist[c] = 0;
        pq.add(new int[]{c, 0});

        while (!pq.isEmpty()) {
            int[] elem = pq.poll();

            int cur = elem[0];
            int time = elem[1];

            if (dist[cur] < time) continue;

            for (int[] nextNode : adj[cur]) {
                int next = nextNode[0];
                int nextTime = nextNode[1];

                if (dist[next] > dist[cur] + nextTime) {
                    dist[next] = dist[cur] + nextTime;
                    pq.add(new int[]{next, dist[next]});
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (dist[i] != 987654321) {
                cnt++;
                if (ans < dist[i]) {
                    ans = dist[i];
                }
            }
        }
        return new int[]{cnt, ans};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            stk = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(stk.nextToken());
            int d = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken());
            int[] dist = new int[n + 1];
            ArrayList<int[]>[] adj = new ArrayList[n + 1];

            for (int i = 1; i <= n; i++) {
                dist[i] = 987654321;
                adj[i] = new ArrayList<>();
            }

            for (int i = 0; i < d; i++) {
                stk = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(stk.nextToken());
                int b = Integer.parseInt(stk.nextToken());
                int s = Integer.parseInt(stk.nextToken());

                adj[b].add(new int[]{a, s});
            }
            int[] ans = dijk(n, c, dist, adj);
            sb.append(ans[0] + " " + ans[1]).append("\n");
        }
        System.out.print(sb);
    }
}
