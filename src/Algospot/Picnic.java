package Algospot;

import java.io.*;
import java.util.*;

public class Picnic {
    static boolean[][] Relationship = new boolean[10][10];
    static boolean[] Friend = new boolean[10];
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int C, N, M;

        C = Integer.parseInt(br.readLine());
        for(int c = 0; c < C; c++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stk.nextToken());
            M = Integer.parseInt(stk.nextToken());

            stk = new StringTokenizer(br.readLine());

            for(int i = 0; i < M; i++) {
                int f1 = Integer.parseInt(stk.nextToken());
                int f2 = Integer.parseInt(stk.nextToken());
                if(f1 < f2) Relationship[f1][f2] = true;
                else Relationship[f2][f1] = true;
            }
            dfs(0, N, M, 0, 1);
            for(int i = 0; i < 10; i++)
                Arrays.fill(Relationship[i], false);
            sb.append(ans).append("\n");
            ans = 0;
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    public static void dfs(int depth, int N, int M, int f1, int f2) {
        if(depth * 2 == N) {
            ans += 1;
            return;
        }

        for(int i = f1; i < N; i++) {
            for(int j = f1 + 1; j < N; j++) {
                if(Relationship[i][j] && !Friend[i] && !Friend[j]) {
                    Friend[i] = true;
                    Friend[j] = true;
                    dfs(depth + 1, N, M, i, j);
                    Friend[i] = false;
                    Friend[j] = false;
                }
            }
        }
        return;
    }
}
