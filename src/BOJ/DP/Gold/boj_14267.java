package BOJ.DP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//Logic
//처음에는 BFS인줄 알고 풀었는데 시간초과 => 칭찬에 대해서 계속 bfs 호출해서 그럼, 다 입력 받아서 배얄에 저장해놓고 한번에 bfs돌면 문제 없음
//DFS로 풀면 된다는 게시글을 발견, 해보니까 BFS로도 풀림
//dp로 풀어보기 위해 블로그 참고 => top-down 방식으로 해결
//다시 풀어보자

//풀이 시간 : 50분

public class boj_14267 {
    private static int dp(int node, int[] parent, int[] ans, int[] arr) {
        if(node == -1) return 0;
        if(ans[node] != -1) {
            return ans[node];
        } else {
            ans[node] = arr[node];
        }

        return ans[node] += dp(parent[node], parent, ans, arr);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        int[] parent = new int[n + 1];
        int[] arr = new int[n + 1];
        int[] ans = new int[n + 1];

        Arrays.fill(ans, -1);
        stk = new StringTokenizer(br.readLine());

        for(int i = 1; i <= n; i++) {
            parent[i] = Integer.parseInt(stk.nextToken());
        }

        for(int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int emp = Integer.parseInt(stk.nextToken());
            int com = Integer.parseInt(stk.nextToken());

            arr[emp] += com;
        }

        for(int i = 1; i <= n; i++) {
            sb.append(dp(i, parent, ans, arr)).append(" ");
        }

        System.out.print(sb);
    }
}

//public class boj_14267 {
//    private static int[] costArr, ans;
//    private static boolean[] visited;
//    private static ArrayList<Integer>[] adj;
//    private static void dfs(int node, int cost) {
//        int sum = cost + costArr[node];
//        ans[node] = sum;
//
//        for(int next : adj[node]) {
//            if(!visited[next]) {
//                visited[next] = true;
//                dfs(next, sum);
//            }
//        }
//    }
//    private static void bfs(int node) {
//        Queue<int[]> q = new LinkedList<>();
//
//        q.add(new int[]{node, 0});
//        visited[node] = true;
//
//        while(!q.isEmpty()) {
//            int[] elem = q.poll();
//
//            int sum = elem[1] + costArr[elem[0]];
//
//            ans[elem[0]] = sum;
//
//            for(int next : adj[elem[0]]) {
//                if(!visited[next]) {
//                    visited[next] = true;
//                    q.add(new int[]{next, sum});
//                }
//            }
//        }
//    }
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//        StringTokenizer stk = new StringTokenizer(br.readLine());
//        int n = Integer.parseInt(stk.nextToken());
//        int m = Integer.parseInt(stk.nextToken());
//        ans = new int[n + 1];
//        costArr = new int[n + 1];
//
//        adj = new ArrayList[n + 1];
//        visited = new boolean[n + 1];
//
//        stk = new StringTokenizer(br.readLine());
//
//        for(int i = 0; i <= n; i++) {
//            adj[i] = new ArrayList<>();
//        }
//
//        for(int i = 1; i <= n; i++) {
//            int parent = Integer.parseInt(stk.nextToken());
//
//            if(parent == -1) continue;
//            adj[parent].add(i);
//        }
//
//        for(int i = 0; i < m; i++) {
//            stk = new StringTokenizer(br.readLine());
//            int emp = Integer.parseInt(stk.nextToken());
//            int com = Integer.parseInt(stk.nextToken());
//
//            costArr[emp] += com;
//        }
//
////        dfs(1, 0);
//
//        bfs(1);
//        for(int i = 1; i <= n; i++) {
//            sb.append(ans[i]).append(" ");
//        }
//
//        System.out.print(sb);
//    }
//}
