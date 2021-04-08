package BOJ.DFS.Gold;

import java.io.*;
import java.util.StringTokenizer;
//Logic
//DFS로 해결할 수 있는 문제
//시간 초과가 나서 다시 풀었다...
//Cycle이 존재하는 경우에는 단순히 true로 바꿔줄 뿐만 아니라 재방문을 막아주기 위해 새로운 cycle배열을 생성
    //즉 visited => 단순 방문 처리
    //  cycle => 사이클 체크 여부 배열, 그리고 어떠한 인원이 사이클과 연결하려고 하면 막아주는 용도이기도함

//사이클 여부 체크할 시에 따라가면서 team인원수 ++
//풀이 시간 : 3시간....

public class boj_9466 {
    private static int team = 0;
    private static int[] arr;
    private static boolean[] visited, cycle;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer stk = new StringTokenizer(br.readLine());
            arr = new int[n + 1];
            visited = new boolean[n + 1];
            cycle = new boolean[n + 1];

            for(int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(stk.nextToken());
            }

            for(int i = 1; i <= n; i++) {
                if(!visited[i]);
                    dfs(i);
            }
            sb.append(n - team).append("\n");
            team = 0;
        }
        System.out.print(sb);
    }
    private static void dfs(int start) {
        if(visited[start]) return;

        visited[start] = true;
        int next = arr[start];

        if(!visited[next]) dfs(next);
        else {
            if(!cycle[next]) {
                team++;

                for(int i = next; i != start; i = arr[i]) {
                    team++;
                }
            }
        }
        cycle[start] = true;
    }
}
