package BackTracking;

import java.io.*;
import java.util.*;

public class algo_15649 {
    static StringBuilder sb = new StringBuilder();
    static int[] stack;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BackTrack BT = new BackTrack();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        stack = new int[N];
        visited = new boolean[M + 1];
        BT.DFS(N, M, 0);

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}

class BackTrack {
    void DFS(int n, int m, int d) {
        if(d == m) {
            for(int i = 0; i < n; i++) {
                algo_15649.sb.append(algo_15649.stack[i]).append(" ");
            }
            algo_15649.sb.append("\n");
            return;
        } else {
            for(int i = 1; i <= n; i++) {
                if(!algo_15649.visited[i]) {
                    algo_15649.stack[d] = i;
                    algo_15649.visited[i] = true;
                    DFS(n, m, d + 1);
                    algo_15649.visited[i] = false;
                }
            }
        }
        return;
    }
}