package BOJ.TopologySort.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//Logic
//위상정렬로 풀 수 있는 문제
//선수 조건 배열과 depth 즉 학기를 저장할 배열을 세팅
//위상정렬을 돌기 전 루트노드 즉 선수조건이 0인 노드를 넣어주고 큐에서 하나씩 꺼내서 bfs를 돈다
//풀이 시간 : 10분
public class boj_14567 {
    private static void topologySort(int n, int[] degree, int[] answer, boolean[][] adj) {
        Queue<Integer> q = new LinkedList<>();

        for(int i = 1; i <= n; i++) {
            if(degree[i] == 0) {
                q.add(i);
                answer[i] = 1;
            }
        }

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int i = 1; i <= n; i++) {
                if(adj[cur][i]) {
                    answer[i] = Math.max(answer[i], answer[cur] + 1);

                    if(--degree[i] == 0) {
                        q.add(i);
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        int[] degree = new int[n + 1];
        int[] answer = new int[n + 1];
        boolean[][] adj = new boolean[n + 1][n + 1];

        for(int i = 1; i <= m; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());

            adj[a][b] = true;
            degree[b]++;
        }

        topologySort(n, degree, answer, adj);


        for(int i = 1; i <= n; i++) {
            sb.append(answer[i]).append(" ");
        }

        System.out.print(sb);
    }
}
