package BOJ.UnionFind.Gold;

import java.io.*;
import java.util.StringTokenizer;

//Logic
//친구비 문제 => Union-Find
//친구의 관계가 주어졌을 시 그 관계를 Union-Find로 묶어주되 가장 비용이 적은 쪽으로 비용이 높은 쪽이 함쳐져야하는 문제
//합쳐줄때 조상의 번호도 갱신하여주고 조상의 cost도 원래 비용이 더 높았던 쪽에 낮은쪽의 조상 비용을 넣어준다.
public class boj_16562 {
    private static int[] union;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int k = Integer.parseInt(stk.nextToken());

        union = new int[N + 1];
        int[] cost = new int[N + 1];

        stk = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(stk.nextToken());
            union[i] = i;
        }

        for(int i = 0; i < M; i++) {
            stk = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(stk.nextToken());
            int node2 = Integer.parseInt(stk.nextToken());

            unionFind(node1, node2, cost);
        }

        int sum = 0;
        for(int i = 1; i <= N; i++) {
            if(union[i] == i) {
                sum += cost[i];
            }
        }

        if(sum <= k) {
            sb.append(sum);
        } else {
            sb.append("Oh no");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
    private static int findParent(int x) {
        if(x == union[x]) return x;
        return union[x] = findParent(union[x]);
    }
    private static void unionFind(int node1, int node2, int[] cost) {
        int root1 = findParent(node1);
        int root2 = findParent(node2);

        if(cost[root1] < cost[root2]) {
            union[root2] = union[root1];
            cost[root2] += cost[root1];
        } else {
            union[root1] = union[root2];
            cost[root1] += cost[root2];
        }
    }
}
