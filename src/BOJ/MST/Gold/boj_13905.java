package BOJ.MST.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//Logic
//현재 시작노드값 최대로해놓고 거기서 하나씩 탐색
//탐색하면서 현재까지의 최대(d[i])와 현재에서의 다음꺼중에 최소값들 중 최대인 애를 d[i]에 넣어줌
//풀이 시간 : 45분

public class boj_13905 {
    private static class Info {
        int node, weight;

        public Info(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        PriorityQueue<Info> pq = new PriorityQueue<>(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                return o2.weight - o1.weight;
            }
        });
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        stk = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(stk.nextToken());
        int e = Integer.parseInt(stk.nextToken());
        int[] dist = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        ArrayList<Info>[] adj = new ArrayList[n + 1];

        for(int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stk.nextToken());
            int end = Integer.parseInt(stk.nextToken());
            int weight = Integer.parseInt(stk.nextToken());

            adj[start].add(new Info(end, weight));
            adj[end].add(new Info(start, weight));
        }

        dist[s] = 987654321;
        pq.add(new Info(s, 0));

        while(!pq.isEmpty()) {
            Info elem = pq.poll();
            int cur = elem.node;

            if(visited[cur]) continue;
            visited[cur] = true;

            for(Info nextElem : adj[cur]) {
                int nextNode = nextElem.node;
                int nextWeight = nextElem.weight;
                dist[nextNode] = Math.max(dist[nextNode], Math.min(dist[cur], nextWeight));
                pq.add(new Info(nextNode, nextWeight));
            }
        }

        System.out.println(dist[e]);
    }
}
