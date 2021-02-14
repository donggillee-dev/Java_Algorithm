package BOJ.DFS.Silver;
import java.io.*;
import java.util.*;

public class boj_15650 {
    private static StringBuilder sb;
    private static int N;
    private static int M;
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        visit = new boolean[N];

        if (N == M) {
            for (int i = 1; i <= M; i++) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
        } else {
            MakeNumArr(0, 0, "");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }

    private static void MakeNumArr(int start, int length, String str) {
        if(length == M) {
            sb.append(str).append("\n");
        } else {
            for(int i = start; i < visit.length; i++) {
                if(!visit[i]) {
                    visit[i] = true;
                    MakeNumArr(i + 1, length + 1, str + (i + 1) + " ");
                    visit[i] = false;
                }
            }
        }
        return;
    }
}