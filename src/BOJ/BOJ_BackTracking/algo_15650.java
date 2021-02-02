package BOJ.BOJ_BackTracking;
import java.io.*;
import java.util.*;

public class algo_15650 {
    static StringBuilder sb;
    static int N;
    static int M;
    static boolean[] visit;

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