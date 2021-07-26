package BOJ.Dijkstra.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//Logic
//다익스트라, 플루이드 와샬로 풀수 있는 문제
//사실상 와샬이 더 쉬울듯?
//다익의 경우 => 이전에 거쳐온 경로에 대해 가장 첫번째 노드번호를 가져와서 처리해줘야함

//풀이 시간 : 35분

public class boj_1719 {
    private static int n, m;
    private static class Info {
        int next, dist;

        public Info(int next, int dist) {
            this.next = next;
            this.dist = dist;
        }
    }
    private static void input(ArrayList<ArrayList<Info>> adj) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        for(int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stk.nextToken());
            int next = Integer.parseInt(stk.nextToken());
            int dist = Integer.parseInt(stk.nextToken());

            adj.get(start).add(new Info(next, dist));
            adj.get(next).add(new Info(start, dist));
        }
    }
    private static int[][] dijk(ArrayList<ArrayList<Info>> adj) {
        int[][] ans = new int[n + 1][n + 1];
        PriorityQueue<Info> pq = new PriorityQueue<>(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                return o1.dist - o2.dist;
            }
        });

        for(int i = 1; i <= n; i++) {
            int[] opt = new int[n + 1];
            Arrays.fill(opt, 987654321);

            opt[i] = 0;
            pq.add(new Info(i, 0));

            while(!pq.isEmpty()) {
                Info node = pq.poll();
                int cur = node.next, dist = node.dist;

                if(opt[cur] < dist) continue;

                for(Info elem : adj.get(cur)) {
                    int nextDist = dist + elem.dist;

                    if(nextDist < opt[elem.next]) {
                        opt[elem.next] = nextDist;
                        pq.add(new Info(elem.next, nextDist));
                        if(cur == i) {
                            ans[i][elem.next] = elem.next;
                            continue;
                        }

                        if(ans[i][cur] != 0) {
                            ans[i][elem.next] = ans[i][cur];
                        } else {
                            ans[i][elem.next] = cur;
                        }
                    }
                }
            }
        }

        return ans;
    }
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        ArrayList<ArrayList<Info>> adj = new ArrayList<>();

        input(adj);

        int[][] ans = dijk(adj);

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == j) sb.append("- ");
                else sb.append(ans[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
