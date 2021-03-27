package BOJ.Dijkstra.Gold;

import java.io.*;
import java.util.*;
//Logic
//파라메트릭 서치를 사용해야 하는 문제
//이분 탐색의 기준 값은 케이블 선의 비용으로 한다
//이분 탐색 할때마다 다익스트라를 도는데 이분 탐색 기준인 케이블선의 비용을 기준으로 돈다 => 그 과정에서 그 기준 값 이상의 케이블선 사용을 K번 이하로 하여
//도착지까지 잘 돌 수 있는지 체크
//다익에서 사용되는 우선순위큐는 K보다 큰 케이블을 몇번 사용했는지를 가지고 한다.

public class boj_1800 {
    private static class Info implements Comparable<Info>{
        int node, cost;
        public Info(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Info o) {
            return this.cost - o.cost;
        }
    }
    private static boolean dijk(int N, int K, int mid, int[] dist, ArrayList<Info>[] adj) {
        Arrays.fill(dist, 987654321);
        dist[1] = 0;
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(1, 0));

        while(!pq.isEmpty()) {
            Info elem = pq.poll();

            int node = elem.node, cnt = elem.cost;
            if(dist[node] < cnt) continue;
            for(Info inf : adj[node]) {
                int nextCnt;
                if(inf.cost > mid) nextCnt = cnt + 1;
                else nextCnt = cnt;

                if(dist[inf.node] > nextCnt) {
                    dist[inf.node] = nextCnt;
                    pq.add(new Info(inf.node, nextCnt));
                }
            }
        }

        return dist[N] <= K;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken()), P = Integer.parseInt(stk.nextToken()), K = Integer.parseInt(stk.nextToken());
        int low = 0, high = 1000000;
        int[] dist = new int[N + 1];
        ArrayList<Info>[] adj = new ArrayList[N + 1];

        for(int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i = 0; i < P; i++) {
            stk = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stk.nextToken()), end = Integer.parseInt(stk.nextToken()), cost = Integer.parseInt(stk.nextToken());
            adj[start].add(new Info(end, cost));
            adj[end].add(new Info(start, cost));
        }

        int answer = -1;
        while(low <= high) {
            int mid = (low + high) / 2;

            if(dijk(N, K, mid, dist, adj)) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        System.out.print(answer);
    }
}