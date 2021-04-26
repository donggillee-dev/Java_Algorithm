package BOJ.Dijkstra.Gold;

import java.io.*;
import java.util.*;
//Logic
//다익스트라 알고리즘을 이용해서 풀 수 있는 문제
//end에 도달하면 dijk 종료하는게 키 포인트
//풀이 시간 : 5분
public class boj_1916 {
    private static void dijk(int start, int end, int[] dist, ArrayList<int[]>[] adj) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
           @Override
           public int compare(int[] o1, int[] o2) {
               return o1[1] - o2[1];
           }
        });

        dist[start] = 0;
        pq.add(new int[]{start, 0});

        while(!pq.isEmpty()) {
            int[] elem = pq.poll();

            int cur = elem[0];
            int curCost = elem[1];

            if(cur == end) return;
            if(dist[cur] < curCost) continue;

            for(int[] v : adj[cur]) {
                int next = v[0];
                int nextCost = v[1];

                if(dist[next] > dist[cur] + nextCost) {
                    dist[next] = dist[cur] + nextCost;
                    pq.add(new int[]{next, dist[next]});
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        final int inf = 987654321;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[] dist = new int[n + 1];
        ArrayList<int[]>[] adj = new ArrayList[n + 1];

        for(int i = 1; i <= n; i++) {
            dist[i] = inf;
            adj[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken());

            adj[a].add(new int[]{b, c});
        }

        stk = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(stk.nextToken());
        int end = Integer.parseInt(stk.nextToken());

        dijk(start, end, dist, adj);

        System.out.println(dist[end]);
    }
}
