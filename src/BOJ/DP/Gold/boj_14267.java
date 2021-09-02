package BOJ.DP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//Logic
//처음에는 BFS인줄 알고 풀었는데 시간초과
//DFS로 풀면 된다는 게시글을 발견 -> 아마 bfs보다는 현재 노드의 자식들을 다 탐색할 필요가 없어서 빠른듯?
//dp로 풀어보기 위해 블로그 참고 => top-down 방식으로 해결

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
