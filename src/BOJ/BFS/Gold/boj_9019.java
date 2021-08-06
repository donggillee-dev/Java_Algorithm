package BOJ.BFS.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//Logic
//각 숫자에 대해서 방문배열, 정답배열 생성
//BFS 돌아주면 된다

//풀이 시간 : 25분

public class boj_9019 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        while(n --> 0) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            boolean[] visited = new boolean[10001];
            String[] ans = new String[10001];
            Queue<Integer> q = new LinkedList<>();

            visited[a] = true;
            q.add(a);
            Arrays.fill(ans, "");

            while(!q.isEmpty() && !visited[b]) {
                int cur = q.poll();
                int D = (cur * 2) % 10000;
                int S = 0;
                int L = (cur % 1000) * 10 + cur /1000;
                int R = (cur % 10) * 1000 + cur / 10;

                if(cur == 0) S = 9999;
                else S = cur - 1;

                if(!visited[D]) {
                    q.add(D);
                    visited[D] = true;
                    ans[D] = ans[cur] + "D";
                }

                if(!visited[S]) {
                    q.add(S);
                    visited[S] = true;
                    ans[S] = ans[cur] + "S";
                }

                if(!visited[L]) {
                    q.add(L);
                    visited[L] = true;
                    ans[L] = ans[cur] + "L";
                }

                if(!visited[R]) {
                    q.add(R);
                    visited[R] = true;
                    ans[R] = ans[cur] + "R";
                }
            }
            sb.append(ans[b]).append("\n");
        }

        System.out.print(sb);
    }
}
