package BOJ.DFS.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//Logic
//BFS를 이용해서 풀면 되는 문제

//풀이 시간 : 12분
public class boj_1240 {
    private static int bfs(int start, int target, boolean[] visited, ArrayList<int[]>[] adj) {
        Queue<int[]> q = new LinkedList<>();

        Arrays.fill(visited, false);
        q.add(new int[]{start, 0});
        visited[start] = true;

        while(!q.isEmpty()) {
            int[] elem = q.poll();

            int cur = elem[0];
            int curDist = elem[1];

            if(cur == target) return curDist;

            for(int[] e : adj[cur]) {
                int next = e[0];
                int nextDist = e[1];

                if(!visited[next]) {
                    visited[next] = true;
                    q.add(new int[]{next, curDist + nextDist});
                }
            }
        }

        return 0;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        ArrayList<int[]>[] adj = new ArrayList[n + 1];
        boolean[] visited = new boolean[n + 1];

        for(int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i = 0; i < n - 1; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken());

            adj[a].add(new int[]{b, c});
            adj[b].add(new int[]{a, c});
        }

        for(int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stk.nextToken());
            int target = Integer.parseInt(stk.nextToken());

            sb.append(bfs(start, target, visited, adj)).append("\n");
        }

        System.out.print(sb);
    }
}
