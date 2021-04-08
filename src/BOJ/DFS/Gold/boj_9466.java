package BOJ.DFS.Gold;

import java.io.*;
import java.util.StringTokenizer;
//Logic
//DFS로 해결할 수 있는 문제
//다만 Cycle에 대해서 스택 오버플러우가 발생할수도 있기때문에 탐색했던 위치는 true로 세팅해주고
//원래 위치로 돌아오지 못한다면 모두 다시 false로 세팅해준다

public class boj_9466 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine()), cnt = 0;
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int[] arr = new int[n + 1];
            boolean[] visited = new boolean[n + 1];

            for(int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(stk.nextToken());
            }

            for(int i = 1; i <= n; i++) {
                if(arr[i] == i) {
                    visited[i] = true;
                    continue;
                }
                if(!visited[i]) {
                    dfs(i, i, visited, arr);
                }
            }
            for(int i = 1; i <= n; i++) {
                if(!visited[i]) cnt++;
            }
            sb.append(cnt).append("\n");
        }
        System.out.print(sb);
    }
    private static boolean dfs(int origin, int start, boolean[] visited, int[] arr) {
        if(!visited[start]) {
            if(origin == arr[start]) {
                visited[start] = true;
                return true;
            } else {
                visited[start] = true;
                if(dfs(origin, arr[start], visited, arr)) {
                    visited[start] = true;
                    return true;
                } else {
                    visited[start] = false;
                    return false;
                }
            }
        } else return false;
    }
}
