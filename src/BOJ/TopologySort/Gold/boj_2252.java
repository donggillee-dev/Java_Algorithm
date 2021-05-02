package BOJ.TopologySort.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//Logic
//위상 정렬을 이용하면 되는 문제
//단순히 2차원 배열을 만들어서 위상 정렬 돌리면 되겠지 했는데 계속 메모리 초과가 남
//문제 제대로 읽고 메모리 계산해보자, 2차원 배열로 구현하면 n이 320,000이기에 128mb안에 구현 불가능
//인접 행렬보다는 인접 리스트 사용하자

//풀이 시간 : 20분
public class boj_2252 {
    private static void tp(int n, int[] degree, ArrayList<Integer>[] adj) {
        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        for(int i = 1; i <= n; i++) {
            if(degree[i] == 0) {
                q.add(i);
                sb.append(i).append(" ");
                visited[i] = true;
            }
        }

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int next : adj[cur]) {
                if(--degree[next] == 0) {
                    q.add(next);
                    sb.append(next).append(" ");
                    visited[next] = true;
                }
            }

        }

        for(int i = 1; i <= n; i++) {
            if(!visited[i]) {
                sb.append(i).append(" ");
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
        for(int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        while(m --> 0) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            adj[a].add(b);
            degree[b]++;
        }

        tp(n, degree, adj);
    }
}
