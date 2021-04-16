package BOJ.LCA.Platinum;

//Logic
//LCA 알고리즘이지만 노드의 개수가 많은 관게로 각 노드의 2^x번쨰 부모를 저장하는 방식으로 시간을 단축할 수 있다
//parents[i][j] = i번째 노드의 2^j번째 부모

import java.io.*;
import java.util.*;

//다시 풀어보자
//필요한거
//1. DFS,
//2. depth배열
//3. parents베열
//4. lca 함수
public class boj_11438 {
    private static int N, K;
    private static List<List<Integer>> list;

    private static void dfs(int cnt, int node, int[] depth, int[][] parent) {
        depth[node] = cnt;

        for(int elem : list.get(node)) {
            if(depth[elem] == 0) {
                dfs(cnt + 1, elem, depth, parent);
                parent[elem][0] = node;
            }
        }
    }
    private static void initParent(int[][] parent) {
        for(int k = 1; k < K; k++) {
            for(int i = 1; i <= N; i++) {
                parent[i][k] = parent[parent[i][k - 1]][k - 1];
            }
        }
    }
    private static int lca(int a, int b, int[] depth, int[][] parent) {
        if(depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        for(int i = K - 1; i >= 0; i--) {
            if(Math.pow(2, i) <= depth[a] - depth[b]) {
                a = parent[a][i];
            }
        }

        if(a == b) return a;

        for(int i = K - 1; i >= 0; i--) {
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
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();

        for(int i = 0; i < N + 1; i++) {
            list.add(new ArrayList<>());
        }

        for(int i = 0; i < N - 1; i++) {
            stk = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stk.nextToken());
            int end = Integer.parseInt(stk.nextToken());
            list.get(start).add(end);
            list.get(end).add(start);
        }

        int tmp = 1;
        K = 0;

        while(tmp <= N) {
            tmp <<= 1;
            K++;
        }

        int[] depth = new int[N + 1];
        int[][] parent = new int[N + 1][K];

        dfs(1, 1, depth, parent);
        initParent(parent);

        int M = Integer.parseInt(br.readLine());
        while(M --> 0) {
            stk = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(stk.nextToken());
            int node2 = Integer.parseInt(stk.nextToken());

            sb.append(lca(node1, node2, depth, parent)).append("\n");
        }

        System.out.print(sb);
    }
}
