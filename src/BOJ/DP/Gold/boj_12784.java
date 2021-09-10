package BOJ.DP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//Logic
//DFS방식으로 풀 수 있는 문제
//현재 노드에서 자식으로 들어가서 나오는 모든 최솟값들의 합을 가지고 현재 노드에서의 간선값과 비교
//초기값으로 그대로 나오면 다이너마이트 개수는 0개

public class boj_12784 {
    private static class Info {
        int c, w;

        public Info(int c, int w) {
            this.c = c;
            this.w = w;
        }
    }

    private static boolean[] visited;
    private static ArrayList<Info>[] adj;

    private static int dp(int node, int val) {
        int sum = 0;
        boolean flag = false;

        visited[node] = true;

        for(Info inf : adj[node]) {
            if(!visited[inf.c]) {
                flag = true;
                sum += dp(inf.c, inf.w);
            }
        }

        if(!flag) return val;
        else return Math.min(sum, val);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            stk = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(stk.nextToken());
            int m = Integer.parseInt(stk.nextToken());
            adj = new ArrayList[n + 1];
            visited = new boolean[n + 1];

            for (int i = 1; i <= n; i++) {
                adj[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                stk = new StringTokenizer(br.readLine());
                int c1 = Integer.parseInt(stk.nextToken());
                int c2 = Integer.parseInt(stk.nextToken());
                int w = Integer.parseInt(stk.nextToken());

                adj[c1].add(new Info(c2, w));
                adj[c2].add(new Info(c1, w));
            }
            int answer = dp(1, 987654321);

            if(answer == 987654321) sb.append(0);
            else sb.append(answer);

            sb.append("\n");
        }

        System.out.print(sb);
    }
}
