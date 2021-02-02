package BOJ.BOJ_BackTracking;

import java.io.*;
import java.util.*;

public class algo_14889 {
    static int N;
    static int[][] Stat;
    static boolean[] Choosen;
    static int TeamStart = 0;
    static int TeamLink = 0;
    static int Abs = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;

        N = Integer.parseInt(br.readLine());
        Stat = new int[N + 1][N + 1];
        Choosen = new boolean[N + 1];

        for(int i = 1; i <= N; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                Stat[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        for(int i = 1; i <= N; i++) {
            Choosen[i] = true;
            dfs(1, i);
            Choosen[i] = false;
        }
        sb.append(Abs).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }

    private static void dfs(int cnt, int idx) {
        if(cnt == N / 2) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    if(i != j) {
                        if(Choosen[i] && Choosen[j]) {
                            TeamLink += Stat[i][j];
                        }
                        if(!Choosen[i] && !Choosen[j]) {
                            TeamStart += Stat[i][j];
                        }
                    }
                }
            }
            if(Abs > Math.abs(TeamLink - TeamStart)) Abs = Math.abs(TeamLink - TeamStart);
            TeamStart = 0;
            TeamLink = 0;
        } else {
            for(int i = idx + 1; i <= N; i++) {
                if(!Choosen[i]) {
                    Choosen[i] = true;
                    dfs(cnt + 1, i);
                    Choosen[i] = false;
                }
            }
        }
    }
}