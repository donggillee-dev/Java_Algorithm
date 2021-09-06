package BOJ.Dijkstra.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_9370 {
    private static int n, m, t, s, g, h;

    private static class Info implements Comparable<Info> {
        int node, weight;
        boolean flag;

        public Info(int next, int weight) {
            this.node = next;
            this.weight = weight;
            this.flag = false;
        }

        public Info(int next, int weight, boolean flag) {
            this.node = next;
            this.weight = weight;
            this.flag = flag;
        }

        @Override
        public int compareTo(Info o) {
            return this.weight - o.weight;
        }
    }

    private static int[] dist;
    private static HashMap<Integer, Boolean> cand;
    private static ArrayList<Info>[] adj;

    private static HashSet<Integer> dijk() {
        PriorityQueue<Info> pq = new PriorityQueue<>();
        HashSet<Integer> set = new HashSet<>();

        pq.add(new Info(s, 0, false));
        dist[s] = 0;

        while(!pq.isEmpty()) {
            Info inf = pq.poll();

            if(cand.get(inf.node) != null && inf.flag) {
                set.add(inf.node);
            }
            if(dist[inf.node] < inf.weight) continue;

            for(Info next : adj[inf.node]) {
                int nextWeight = inf.weight + next.weight;
                boolean visited = false;
                if((next.node == g && inf.node == h) || (next.node == h && inf.node == g) || inf.flag) {
                    visited = true;
                }
                if(dist[next.node] > nextWeight) {
                    dist[next.node] = nextWeight;
                    pq.add(new Info(next.node, nextWeight, visited));
                }
            }
        }

        return set;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;
        int TC = Integer.parseInt(br.readLine());

        while (TC-- > 0) {
            stk = new StringTokenizer(br.readLine());
            n = Integer.parseInt(stk.nextToken());
            m = Integer.parseInt(stk.nextToken());
            t = Integer.parseInt(stk.nextToken());

            stk = new StringTokenizer(br.readLine());
            s = Integer.parseInt(stk.nextToken());
            g = Integer.parseInt(stk.nextToken());
            h = Integer.parseInt(stk.nextToken());

            dist = new int[n + 1];
            adj = new ArrayList[n + 1];
            cand = new HashMap<>();

            for(int i = 1; i <= n; i++) {
                adj[i] = new ArrayList<>();
                dist[i] = 987654321;
            }

            for(int i = 0; i < m; i++) {
                stk = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(stk.nextToken());
                int to = Integer.parseInt(stk.nextToken());
                int weight = Integer.parseInt(stk.nextToken());

                adj[from].add(new Info(to, weight));
                adj[to].add(new Info(from, weight));
            }

            for(int i = 0; i < t; i++) {
                int candidate = Integer.parseInt(br.readLine());
                cand.put(candidate, true);
            }

            PriorityQueue<Integer> pq = new PriorityQueue<>();
            pq.addAll(dijk());

            while(!pq.isEmpty()) {
                sb.append(pq.poll()).append(" ");
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }
}
