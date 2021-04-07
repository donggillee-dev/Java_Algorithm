package Programmers.Programmers_2021_KakaoCommerce;

import java.util.*;

public class Solution3 {
    public static void main(String[] args) {
        Solution3 sol = new Solution3();

        System.out.println(Arrays.toString(sol.solution(6, new int[]{1,1,1,1,1,1}, new int[][]{{1,2}, {1,3}, {1,4}, {3,5}, {3,6}})));
        System.out.println(Arrays.toString(sol.solution(4, new int[]{2,1,2,2}, new int[][]{{1,2}, {1,3}, {2,4}})));
        System.out.println(Arrays.toString(sol.solution(5, new int[]{1,1,2,3,4}, new int[][]{{1,2}, {1,3}, {1,4}, {1,5}})));
    }
    private int[] solution(int n, int[] passenger, int[][] train) {
        int[] answer = new int[2];
        int[] new_passenger = new int[n + 1];
        int[] dist = new int[n + 1];
        ArrayList<Integer>[] adj = new ArrayList[n + 1];

        for(int i = 1; i <= n; i++) {
            new_passenger[i] = passenger[i - 1];
        }

        for(int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] rail : train) {
            int start = rail[0], end = rail[1];

            adj[start].add(end);
            adj[end].add(start);
        }

        dijkstra(adj, new_passenger, dist, n);

        int target = 1, num = -1;

        for(int i = 1; i <= n; i++) {
            if(dist[i] > num) {
                target = i;
                num = dist[i];
            } else if(dist[i] == num) {
                target = i;
            }
        }

        answer[0] = target;
        answer[1] = num;

        return answer;
    }
    private static void dijkstra(ArrayList<Integer>[] adj, int[] passenger, int[] dist, int n) {
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<int[]> Q = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        Q.add(new int[]{1, passenger[1]});
        visited[1] = true;
        dist[1] = passenger[1];

        while(!Q.isEmpty()) {
            int[] info = Q.poll();
            int node = info[0], weight = info[1];

            if(dist[node] > weight) continue;

            for(int target : adj[node]) {
                if(dist[target] < weight + passenger[target] && !visited[target]) {
                    dist[target] = weight + passenger[target];
                    visited[target] = true;
                    Q.add(new int[]{target, dist[target]});
                }
            }
        }
    }
}

//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.Queue;

//public class Solution3 {
//    static class info {
//        int dest, weight;
//
//        info(int dest, int weight) {
//            this.dest = dest;
//            this.weight = weight;
//        }
//    }
//
//    public static int[] solution(int n, int[] passenger, int[][] train) {
//        int[] answer = new int[2];
//        ArrayList<Integer> arr[] = new ArrayList[n + 1];
//        for (int i = 1; i <= n; i++)
//            arr[i] = new ArrayList<>();
//        for (int i = 0; i < train.length; i++) {
//            arr[train[i][0]].add(train[i][1]);
//            arr[train[i][1]].add(train[i][0]);
//        }
//
//        int[] distance = new int[n + 1];
//        Queue<info> q = new LinkedList<>();
//        q.add(new info(1, passenger[0]));
//        distance[1] = passenger[0];
//        while (!q.isEmpty()) {
//            info cur = q.poll();
//
//            for (int next : arr[cur.dest]) {
//                if (distance[next] != 0)
//                    continue;
//                distance[next] = cur.weight + passenger[next - 1];
//                q.add(new info(next, distance[next]));
//            }
//        }
//        int index = 1, maxPassenger = 0;
//        for (int i = 1; i <= n; i++) {
//            if (distance[i] >= maxPassenger) {
//                maxPassenger = distance[i];
//                index = i;
//            }
//        }
//        answer[0] = index;
//        answer[1] = maxPassenger;
//        return answer;
//    }
//
//    public static void main(String[] args) {
//        int n = 4;
//        int[] passenger = {2, 1, 2, 2};
//        int[][] train = {{2,1},{1,3},{2,4}};
//        solution(n, passenger, train);
//    }
//}