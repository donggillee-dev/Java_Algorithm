package BOJ.LCA.Gold;

import java.io.*;
import java.util.*;
//Logic
//LCA 2문제의 DP방식을 그대로 적용하면 되는 문제이다

public class boj_11437 {
    private static int N, D;
    private static ArrayList<ArrayList<Integer>> list;
    private static void dfs(int cnt, int node, int[] depths, int[][] parents) {
        depths[node] = cnt;

        for(int next : list.get(node)) {
            if(depths[next] == 0) {
                dfs(cnt + 1, next, depths, parents);
                parents[next][0] = node;
            }
        }
    }
    private static void initParents(int[][] parents) {
        for(int i = 1; i < D; i++) {
            for(int j = 1; j <= N; j++) {
                parents[j][i] = parents[parents[j][i - 1]][i - 1];
            }
        }
    }
    private static int lca(int a, int b, int[] depths, int[][] parents) {
        if(depths[a] < depths[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        for(int i = D - 1; i >= 0; i--) {
            if(Math.pow(2, i) <= depths[a] - depths[b]) {
                a = parents[a][i];
            }
        }

        if(a == b) return a;

        for(int i = D - 1; i >= 0; i--) {
            if(parents[a][i] != parents[b][i]) {
                a = parents[a][i];
                b = parents[b][i];
            }
        }

        return parents[a][0];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;
        list = new ArrayList<>();

        N = Integer.parseInt(br.readLine());

        for(int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        int bit = 1;

        while(bit <= N) {
            bit <<= 1;
            D++;
        }

        for(int i = 0; i < N - 1; i++) {
            stk = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(stk.nextToken());
            int node2 = Integer.parseInt(stk.nextToken());

            list.get(node1).add(node2);
            list.get(node2).add(node1);
        }

        int[] depths = new int[N + 1];
        int[][] parents = new int[N + 1][D];

        dfs(1, 1, depths, parents);
        initParents(parents);

        int M = Integer.parseInt(br.readLine());
        while(M --> 0) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            sb.append(lca(a, b, depths, parents)).append("\n");
        }

        System.out.print(sb);
    }
}
