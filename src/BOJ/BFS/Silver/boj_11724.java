package BOJ.BFS.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//Logic
//연결된 그룹은 구하면 되는 문제
//bfs와 연결 리스트로 해결 가능

public class boj_11724 {
    private static int N, M;

    private static void bfs(int idx, boolean[] visited, ArrayList<Integer>[] adj) {
        Queue<Integer> q = new LinkedList<>();
        visited[idx] = true;
        q.add(idx);

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int next : adj[cur]) {
                if(!visited[next]) {
                    q.add(next);
                    visited[next] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        int ans = 0;
        ArrayList<Integer>[] adj = new ArrayList[N + 1];
        boolean[] visited = new boolean[N + 1];

        for(int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        while(M --> 0) {
            stk = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(stk.nextToken());
            int v = Integer.parseInt(stk.nextToken());

            adj[u].add(v);
            adj[v].add(u);
        }

        for(int i = 1; i <= N; i++) {
            if(!visited[i]) {
                ans++;
                bfs(i, visited, adj);
            }
        }

        System.out.println(ans);
    }
}
