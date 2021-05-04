package BOJ.LCA.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//Logic
//LCA 알고리즘 DP버전으로 해결
//루트를 뭐로 해야하는지 지문을 계속 읽어보느라 애먹었던 문제
//풀이 시간 : 25분

public class boj_3584 {
    private static void dfs(int cnt, int node, int[] depth, int[][] parent, ArrayList<ArrayList<Integer>> list) {
        depth[node] = cnt;

        for(int next : list.get(node)) {
            if(depth[next] == 0) {
                dfs(cnt + 1, next, depth, parent, list);
                parent[next][0] = node;
            }
        }
    }

    private static void initParent(int n, int k, int[][] parent) {
        for(int i = 1; i < k; i++) {
            for(int j = 1; j <= n; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
            }
        }
    }

    private static int lca(int a, int b, int k, int[] depth, int[][] parent) {
        if(depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        for(int i = k - 1; i >= 0; i--) {
            if(Math.pow(2, i) <= depth[a] - depth[b]) {
                a = parent[a][i];
            }
        }

        if(a == b) return a;

        for(int i = k - 1; i >= 0; i--) {
            if(parent[a][i] != parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }

        return parent[a][0];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;
        int t = Integer.parseInt(br.readLine());

        while(t --> 0) {
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            int n = Integer.parseInt(br.readLine());
            int tmp = 1;
            int k = 0;
            int root = 0;
            boolean flag = false;

            while(tmp <= n) {
                k++;
                tmp <<= 1;
            }

            for(int i = 0; i <= n; i++) {
                list.add(new ArrayList<>());
            }

            for(int i = 0; i < n - 1; i++) {
                stk = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(stk.nextToken());
                int b = Integer.parseInt(stk.nextToken());

                if(!flag) {
                    root = a;
                    flag = true;
                }
                list.get(a).add(b);
                list.get(b).add(a);
            }

            int[] depth = new int[n + 1];
            int[][] parent = new int[n + 1][k];

            dfs(1, root, depth, parent, list);

            initParent(n, k, parent);

            stk = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());

            sb.append(lca(a, b, k, depth, parent)).append("\n");
        }
        System.out.print(sb);
    }
}
