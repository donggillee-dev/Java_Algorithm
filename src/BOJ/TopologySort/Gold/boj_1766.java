package BOJ.TopologySort.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//Logic
//너무 어렵게 생각해서 헤맨 문제
//문제 내에 모든 문제를 풀 수 있는 경우만 주어진다고 되어있으므로 문제풀기의 선수행 과제가 주어지지 않는 경우는 없다
//그러므로 그냥 우선순위 큐를 이용해서 위상정렬중에 가장 낮은 번호가 먼저 뽑히게끔 처리
//풀이 시간 : 35분
public class boj_1766 {
    private static void ts(int n, int[] degree, ArrayList<Integer>[] list) {
        Deque<Integer> dq = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<Integer> q = new PriorityQueue<>();

        for(int i = 1; i <= n; i++) {
            if(degree[i] == 0) {
                q.add(i);
            }
        }

        while(!q.isEmpty()) {
            int cur = q.poll();

            sb.append(cur).append(" ");

            for(int next : list[cur]) {
                visited[next] = true;

                if(--degree[next] == 0) {
                    q.add(next);
                }
            }
        }
        System.out.print(sb);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        int[] degree = new int[n + 1];
        ArrayList<Integer>[] adj = new ArrayList[n + 1];

        for(int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            adj[a].add(b);
            degree[b]++;
        }

        ts(n, degree, adj);
    }
}
