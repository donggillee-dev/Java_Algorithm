package BackTracking;
import java.io.*;
import java.util.*;

public class algo_15652 {
    static int N;
    static int M;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        sb = new StringBuilder();
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        MakeArr(0, 0, "");

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }

    private static void MakeArr(int start, int length, String str) {
        if(length == M) {
            sb.append(str).append("\n");
        } else {
            for(int i = start; i < N; i++) {
                MakeArr(i, length + 1, str + (i + 1) + " ");
            }
        }
        return;
    }
}
