package BOJ.DP.Gold;

import java.io.*;
import java.util.*;
//Logic
//그냥 Dijkstra 알고리즘
public class boj_1753 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer stk = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(stk.nextToken());
        int E = Integer.parseInt(stk.nextToken());
        int S = Integer.parseInt(br.readLine());
        int[] dist = new int[V + 1];
        ArrayList<int[]>[] graph = new ArrayList[V + 1];
        PriorityQueue<int[]> Q = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        for(int i = 1; i <= V; i++) {
            dist[i] = 987654321;
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < E; i++) {
            stk = new StringTokenizer(br.readLine());
            graph[Integer.parseInt(stk.nextToken())].add(new int[]{Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken())});
        }

        Q.add(new int[]{S, 0});
        dist[S] = 0;
        while(!Q.isEmpty()) {
            int[] elem = Q.poll();

            if(elem[1] > dist[elem[0]]) continue;

            for(int[] nextNode : graph[elem[0]]) {
                int nextDist = elem[1] + nextNode[1];
                if(dist[nextNode[0]] > nextDist) {
                    dist[nextNode[0]] = nextDist;
                    Q.add(new int[]{nextNode[0], nextDist});
                }
            }
        }

        for(int i = 1; i <= V; i++) {
            if(dist[i] == 987654321) sb.append("INF").append("\n");
            else sb.append(dist[i]).append("\n");
        }

        System.out.println(sb);
    }
}
