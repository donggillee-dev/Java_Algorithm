package Programmers.Programmers_2021_Kakao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution5 {
    private static int[] dist;
    private static int[] sheepArr;
    private static boolean[] visited;
    private static class Info implements Comparable<Info> {
        int node;
        int sheep, wolf;

        public Info(int node, int sheep, int wolf) {
            this.node = node;
            this.sheep = sheep;
            this.wolf = wolf;
        }

        @Override
        public int compareTo(Info o) {
            if(o.sheep == this.sheep) {
                return this.node - o.node;
            }

            return o.sheep - this.sheep;
        }
    }
    private static void bfs(ArrayList<Integer>[] adj, int[] info) {
        int n = info.length;
        sheepArr[0] = 1;

        for(int i = 1; i < n; i++) {
            if(info[i] == 0) {
                Queue<int[]> q = new LinkedList<>();
                q.add(new int[]{i, 1, 0});

                while(!q.isEmpty()) {
                    int[] elem = q.poll();
                    int cur = elem[0];
                    int sheep = elem[1];
                    int wolf = elem[2];

                    for(int next : adj[cur]) {
                        if(next < cur) {
                            if(info[next] == 1) {
                                wolf++;
                            } else {
                                sheep++;
                            }

                            if(sheep + sheepArr[0] > wolf) {
                                sheepArr[next] += sheep;
                                q.add(new int[]{next, sheep, wolf});
                            }
                        }
                    }
                }
            }
        }
    }
    private static int dijk(ArrayList<Integer>[] adj, int[] info) {
        PriorityQueue<Info> pq = new PriorityQueue<>();
        int ans = -1;

        dist[0] = 1;
        pq.add(new Info(0, 1, 0));

        while(!pq.isEmpty()) {
            Info inf = pq.poll();

            if(ans < inf.sheep) {
                ans = inf.sheep;
                sheepArr[inf.node] = ans;
            }
            if(dist[inf.node] > (inf.sheep - inf.wolf)) continue;

            for(int next : adj[inf.node]) {
                if(dist[next] != -987654321) { //재방문, 되돌아가는거
                    if(sheepArr[next] < inf.sheep || dist[next] < (inf.sheep - inf.wolf)) {
//                        if(next > inf.node && info[next] == 1) inf.wo
                        dist[next] = (inf.sheep - inf.wolf);
                        sheepArr[next] = inf.sheep;
                        if(info[next] == 1) inf.wolf++;
                        pq.add(new Info(next, inf.sheep, inf.wolf));
                    }
                } else {
                    int nextSheep = inf.sheep;
                    int nextWolf = inf.wolf;
                    if(info[next] == 0) {
                        nextSheep++;
                    } else {
                        nextWolf++;
                    }

                    if(nextSheep > nextWolf && dist[next] < nextSheep - nextWolf) {
                        dist[next] = nextSheep - nextWolf;
                        pq.add(new Info(next, nextSheep, nextWolf));
                    }
                }
            }
        }

        return ans;
    }

    private static int solution(int[] info, int[][] edges) {
        int length = info.length;
        visited = new boolean[length];
        dist = new int[length];
        sheepArr = new int[length];
        ArrayList<Integer>[] adj = new ArrayList[length];

        for(int i = 0; i < length; i++) {
            adj[i] = new ArrayList<>();
            dist[i] = -987654321;
        }

        for(int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        bfs(adj, info);

        return sheepArr[0];
    }
    public static void main(String[] args) {
//        System.out.println(solution(new int[]{0,0,1,1,1,0,1,0,1,0,1,1},
//                new int[][]{{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}}));
        System.out.println(solution(new int[]{0,1,0,1,1,0,1,0,0,1,0},
                new int[][]{{0,1},{0,2},{1,3},{1,4},{2,5},{2,6},{3,7},{4,8},{6,9},{9,10}}));
    }
}
