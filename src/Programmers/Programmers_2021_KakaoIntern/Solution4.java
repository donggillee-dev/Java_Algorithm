package Programmers.Programmers_2021_KakaoIntern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution4 {
    public static void main(String[] args) {
//        solution(3, 1, 3, new int[][]{{1, 2, 2}, {3,2,3}}, new int[]{2});
        System.out.println(solution(4, 1, 4, new int[][]{{1, 2, 1}, {3, 2, 1}, {2, 4, 1}}, new int[]{2, 3}));
    }

    private static class Info implements Comparable<Info> {
        int node;
        int dist;

        public Info(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Info o) {
            return this.dist - o.dist;
        }
    }

    private static int solution(int n, int start, int end, int[][] roads, int[] traps) {
        int[] dist = new int[n + 1];
        boolean[] isTrap = new boolean[n + 1];
        int[][] adj = new int[n + 1][n + 1];

        Arrays.fill(dist, 987654321);

        for(int trap : traps) {
            isTrap[trap] = true;
        }

        for(int i = 0; i < roads.length; i++) {
            int p = roads[i][0];
            int q = roads[i][1];
            int s = roads[i][2];

            if(adj[p][q] != 0) {
                if(adj[p][q] > s) {
                    adj[p][q] = s;
                }
            } else {
                adj[p][q] = s;
            }
        }

        PriorityQueue<Info> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.add(new Info(start, 0));
        int answer = 987654321;
        while(!pq.isEmpty()) {
            Info elem = pq.poll();

            int cur = elem.node;
            int curDist = elem.dist;

            if(cur == end) {
                if(answer > curDist) {
                    answer = curDist;
                    break;
                }
            }

            for(int next = 1; next <= n; next++) {
                if(adj[cur][next] != 0) {
                    int nextDist = curDist + adj[cur][next];
                    if(isTrap[next]) {
                        boolean[] rotate = new boolean[n + 1];
                        for(int i = 1; i <= n; i++) {
                            if(adj[i][next] != 0) {
                                rotate[i] = true;
                                int tmp = adj[next][i];
                                adj[next][i] = adj[i][next];
                                adj[i][next] = tmp;
                            }
                        }
                        for(int i = 1; i <= n; i++) {
                            if(adj[next][i] != 0 && !rotate[i]) {
                                int tmp = adj[i][next];
                                adj[i][next] = adj[next][i];
                                adj[next][i] = tmp;
                            }
                        }
                        pq.add(new Info(next, nextDist));
                    } else {
                        if(dist[next] > nextDist) {
                            dist[next] = nextDist;
                            pq.add(new Info(next, nextDist));
                        }
                    }
                }
            }
        }

        return answer;
    }
}
