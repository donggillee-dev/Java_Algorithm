package BOJ.Dijkstra.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//Logic
//경로의 경우의 수를 나눠서 풀 수 있는 문제
//다익스트라 + 경우의 수

public class boj_1504 {
    private static int N, E;
    private static class Node implements Comparable<Node> {
        int node, dist;

        public Node(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    public static int dijk(int start, int end, int[] minDist, ArrayList<Node>[] adj) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        Arrays.fill(minDist, 987654321);
        minDist[start] = 0;
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node elem = pq.poll();

            if(minDist[elem.node] < elem.dist) continue;

            for(Node nextNode : adj[elem.node]) {
                int nextDist = elem.dist + nextNode.dist;
                if(minDist[nextNode.node] > nextDist) {
                    minDist[nextNode.node] = nextDist;
                    pq.add(new Node(nextNode.node, nextDist));
                }
            }
        }

        return minDist[end];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        E = Integer.parseInt(stk.nextToken());
        ArrayList<Node>[] adj = new ArrayList[N + 1];
        int[] minDist = new int[N + 1];

        for(int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
            minDist[i] = 987654321;
        }

        for(int i = 0; i < E; i++) {
            stk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());
            int dist = Integer.parseInt(stk.nextToken());

            adj[from].add(new Node(to, dist));
            adj[to].add(new Node(from, dist));
        }

        stk = new StringTokenizer(br.readLine());
        int node1 = Integer.parseInt(stk.nextToken());
        int node2 = Integer.parseInt(stk.nextToken());

        long res1 = dijk(1, node1, minDist, adj);
        res1 += dijk(node1, node2, minDist, adj);
        res1 += dijk(node2, N, minDist, adj);

        long res2 = dijk(1, node2, minDist, adj);
        res2 += dijk(node2, node1, minDist, adj);
        res2 += dijk(node1, N, minDist, adj);

        if(res1 >= 987654321 && res2 >= 987654321) System.out.println(-1);
        else System.out.println(Math.min(res1, res2));
    }
}
