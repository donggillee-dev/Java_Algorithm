package BOJ.DFS.Silver;
import java.io.*;
import java.util.*;

public class algo_15651 {
    private static int N;
    private static int M;
    private static StringBuilder Sb;
    public static void main(String[] args) throws IOException {
        BufferedReader Br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter Bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Sb = new StringBuilder();

        StringTokenizer Stk = new StringTokenizer(Br.readLine());
        N = Integer.parseInt(Stk.nextToken());
        M = Integer.parseInt(Stk.nextToken());

        MakeNum(0, "");

        Bw.write(String.valueOf(Sb));
        Bw.flush();
        Bw.close();
        Br.close();
    }

    private static void MakeNum(int length, String str) {
        if(length == M) {
            Sb.append(str).append("\n");
        } else {
            for(int i = 0; i < N; i++) {
                MakeNum(length + 1, str + (i + 1) + " ");
            }
        }
    }
}
