package BOJ.MST.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//Logic
//MST로 그냥 풀면 되는 문제

public class boj_4386 {
    private static class Info implements Comparable<Info> {
        int starA, starB;
        double dist;

        public Info(int starA, int starB, double dist) {
            this.starA = starA;
            this.starB = starB;
            this.dist = dist;
        }

        @Override
        public int compareTo(Info o) {
            return Double.compare(this.dist, o.dist);
        }
    }
    private static int findParent(int start) {
        if(start == parent[start]) return start;
        else return parent[start] = findParent(parent[start]);
    }
    private static boolean unionFind(int starA, int starB) {
        starA = findParent(starA);
        starB = findParent(starB);

        if(starA == starB) return false;

        parent[starB] = starA;
        return true;
    }
    private static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        double[][] starMap = new double[n][2];
        PriorityQueue<Info> pq = new PriorityQueue<>();

        StringTokenizer stk;
        parent = new int[n];

        for(int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            starMap[i][0] = Double.parseDouble(stk.nextToken());
            starMap[i][1] = Double.parseDouble(stk.nextToken());
            parent[i] = i;
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j) continue;

                double width = Math.abs(starMap[i][0] - starMap[j][0]);
                double height = Math.abs(starMap[i][1] - starMap[j][1]);
                double dist = Math.sqrt(width * width + height * height);

                pq.add(new Info(i, j, dist));
            }
        }

        int conn = 0;
        double ans = 0;
        while(!pq.isEmpty()) {
            Info inf = pq.poll();
            if(unionFind(inf.starA, inf.starB)) ans += inf.dist;
            if(conn == n - 1) break;
        }

        System.out.printf("%.2f", ans);
    }
}
