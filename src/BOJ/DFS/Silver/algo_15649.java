package BOJ.DFS.Silver;

import java.io.*;
import java.util.*;

public class algo_15649 {
    private static Stack<Integer> S = new Stack<Integer>();
    private static int N;
    private static int M;
    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BackTrack BT = new BackTrack();
        StringTokenizer stk = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        BT.Mknum(0, "");

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static class BackTrack {
        void Mknum(int start, String str) {
            if(start == M) {
                sb.append(str).append("\n");
            } else {
                start++;
                for(int i = 1; i <= N; i++) {
                    if(!S.contains(i)) {
                        S.push(i);
                        Mknum(start, str + i + " ");
                        S.pop();
                    }
                }
            }
            return;
        }
    }
}


