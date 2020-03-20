import java.io.*;
import java.util.*;

public class Test2 {
    static int[][] ans;
    static int[][] map;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        ans = new int[50][50];
        map = new int[50][50];

        for(int i = 0; i < T; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stk.nextToken());
            M = Integer.parseInt(stk.nextToken());
            for(int j = 0; j < N; j++) {
                Arrays.fill(ans[i], 0);
                StringTokenizer stk2 = new StringTokenizer(br.readLine());
                for(int k = 0; k < M; k++) {
                    map[j][k] = Integer.parseInt(stk2.nextToken());
                }
            }

            for(int t = 0; t < N; t++) {
                for(int v = 0; v < M - 1; v++) {
                    if(map[t][v] == 1) {
                        if(map[t + 1][v + 1] == 1 && map[t + 1][v] == 1 && map[t][v + 1] == 1) {
                            ans[t][v] = 1;
                            ans[t][v + 1] = 1;
                            ans[t + 1][v + 1] = 1;
                            ans[t + 1][v] = 1;
                        }
                    }
                }
            }
            if(IsChk()) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");

            flush();
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }


    private static void flush() {
        for(int i = 0; i < N; i++) {
            Arrays.fill(map[i], 0);
            Arrays.fill(ans[i], 0);
        }
    }

    private static boolean IsChk() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] != ans[i][j]) return false;
            }
        }
        return true;
    }

}
