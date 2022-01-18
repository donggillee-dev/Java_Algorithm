package BOJ.BFS.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_2606 {
    private static int bfs(ArrayList<Integer>[] adj, boolean[] visited) {
        int ans = 0;
        Queue<Integer> q = new LinkedList<>();

        q.add(1);
        visited[1] = true;

        while(!q.isEmpty()) {
            int num = q.poll();

            for(int next : adj[num]) {
                if(!visited[next]) {
                    visited[next] = true;
                    q.add(next);
                    ans++;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] adj = new ArrayList[N + 1];
        boolean[] visited = new boolean[N + 1];

        for(int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        while(M --> 0) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }

        System.out.println(bfs(adj, visited));
    }
}
