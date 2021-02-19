package SWEA;

import java.io.*;
import java.util.StringTokenizer;

public class Cook {
    private static int answer = Integer.MAX_VALUE, N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer stk;
        int TestCase = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= TestCase; tc++) {
            N = Integer.parseInt(br.readLine());
            boolean[] visited = new boolean[N + 1];
            int[][] Arr = new int[N + 1][N + 1];

            for(int i = 1; i <= N; i++) {
                stk = new StringTokenizer(br.readLine());
                for(int j = 1; j <= N; j++) {
                    Arr[i][j] = Integer.parseInt(stk.nextToken());
                }
            }

            for(int i = 1; i <= N; i++) {
                visited[i] = true;
                DFS(1, i, Arr, visited);
                visited[i] = false;
            }
            sb.append("#" + tc + " ").append(answer).append("\n");
            answer = Integer.MAX_VALUE;
        }
        bw.write(String.valueOf(sb));
        bw.flush();
    }
    private static void DFS(int cnt, int idx, int[][] Arr, boolean[] visited) {
        if(cnt == N / 2) {
            int first = 0, second = 0;
            for(int i = 1; i <= N - 1; i++) {
                for(int j = i + 1; j <= N; j++) {
                    if(visited[i] && visited[j]) {
                        first += Arr[i][j];
                        first += Arr[j][i];
                    } else if(!visited[i] && !visited[j]) {
                        second += Arr[i][j];
                        second += Arr[j][i];
                    }
                }
            }
            answer = Math.min(answer, Math.abs(first - second));
            return;
        } else {
            for(int i = idx + 1; i <= N; i++) {
                if(!visited[i]) {
                    visited[i] = true;
                    DFS(cnt + 1, i, Arr, visited);
                    visited[i] = false;
                }
            }
        }
    }
}
