package BOJ.MST.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//Logic
//아는 개념인데도 조그만 부분에서 계속 실수해서 많이 틀린 문제
//1. 좌표는 1백만까지 값의 범위로 주어지는데 좌표간의 거리 계산에서 Math.pow할때 값의 초과가 일어날 수 있는걸 간과함 => double로 바꿔서 해결

//풀이 시간 : 25분

public class boj_1774 {
    private static int n;
    private static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static class Aisle implements Comparable<Aisle> {
        int node1, node2;
        double dist;

        public Aisle(int node1, int node2, double dist) {
            this.node1 = node1;
            this.node2 = node2;
            this.dist = dist;
        }

        @Override
        public int compareTo(Aisle o) {
            return Double.compare(this.dist, o.dist);
        }
    }
    private static void initParent(int[] parents) {
        for(int i = 1; i <= n; i++) {
            parents[i] = i;
        }
    }
    private static PriorityQueue<Aisle> calcDist(Pos[] adj) {
        PriorityQueue<Aisle> pq = new PriorityQueue<>();

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == j) continue;

                double dist_x = Math.abs(adj[i].x - adj[j].x);
                double dist_y = Math.abs(adj[i].y - adj[j].y);
                double dist = Math.sqrt((dist_x * dist_x) + (dist_y * dist_y));

                pq.add(new Aisle(i, j, dist));
            }
        }

        return pq;
    }
    private static int findParent(int x, int[] parents) {
        if(x == parents[x]) return x;
        else return parents[x] = findParent(parents[x], parents);
    }
    private static boolean unionFind(int a, int b, int[] parents) {
        a = findParent(a, parents);
        b = findParent(b, parents);

        if(a == b) return false;

        if(a < b) parents[a] = b;
        else parents[b] = a;

        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken()), cnt = 0;
        int[] parents = new int[n + 1];
        Pos[] adj = new Pos[n + 1];

        initParent(parents);

        for(int i = 1; i <= n; i++) {
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            adj[i] = new Pos(x, y);
        }

        //이미 연결되어있는 애들 연결
        for(int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());

            if(unionFind(a, b, parents)) cnt++;
        }

        //정점간의 거리 계산
        PriorityQueue<Aisle> pq = calcDist(adj);
        double ans = 0;

        while(!pq.isEmpty()) {
            Aisle elem = pq.poll();

            if(unionFind(elem.node1, elem.node2, parents)) {
                cnt++;
                ans += elem.dist;
            }
            if(cnt == n - 1) break;
        }

        System.out.printf("%.2f", (double)(ans * 100) / 100);
    }
}
