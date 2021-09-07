package BOJ.Dijkstra.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//Logic
//아직 의문인 문제, 냄새를 탐지한 구간을 통과했는지 아닌지 여부를 pq에 넣어서 다익을 돌리려했으나 계속 틀림
//결국 힌트를 참고해서 탐지 구간의 가중치를 홀수로 하고 나머지를 모두 짝수로 해서
//후보군중에 홀수인 부분이 존재하면 냄새가 탐지된 구간을 거쳤다는 추측이 성립하므로 결과 도출
//이 과정에서 987654321인지 아닌지 판단 추가 필수

public class boj_9370 {
    private static int n, m, t, s, g, h;

    private static class Info implements Comparable<Info> {
        int node, weight;

        public Info(int next, int weight) {
            this.node = next;
            this.weight = weight;
        }

        @Override
        public int compareTo(Info o) {
            return this.weight - o.weight;
        }
    }

    private static int[] dist;
    private static HashMap<Integer, Boolean> cand;
    private static ArrayList<Info>[] adj;

    private static PriorityQueue<Integer> dijk() {
        PriorityQueue<Info> pq = new PriorityQueue<>();

        pq.add(new Info(s, 0));
        dist[s] = 0;

        while(!pq.isEmpty()) {
            Info inf = pq.poll();

            if(dist[inf.node] < inf.weight) continue;

            for(Info next : adj[inf.node]) {
                int nextWeight = inf.weight + next.weight;

                if(dist[next.node] > nextWeight) {
                    dist[next.node] = nextWeight;
                    pq.add(new Info(next.node, nextWeight));
                }
            }
        }
        PriorityQueue<Integer> ret = new PriorityQueue<>();

        for(int node : cand.keySet()) {
            if(dist[node] != 987654321 && dist[node] % 2 == 1) {
                ret.add(node);
            }
        }

        return ret;
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

                if((from == g && to == h) || (from == h && to == g)) {
                    weight = (weight * 2 - 1);
                } else {
                    weight = (weight * 2);
                }

                adj[from].add(new Info(to, weight));
                adj[to].add(new Info(from, weight));
            }

            for(int i = 0; i < t; i++) {
                int candidate = Integer.parseInt(br.readLine());
                cand.put(candidate, true);
            }

            PriorityQueue<Integer> pq = dijk();

            if(pq.isEmpty()) continue;

            while(!pq.isEmpty()) {
                sb.append(pq.poll()).append(" ");
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }
}
