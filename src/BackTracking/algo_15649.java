package BackTracking;

import java.io.*;
import java.util.*;

public class algo_15649 {
    static Stack<Integer> S = new Stack<Integer>();
    static int N;
    static int M;
    static StringBuilder sb = new StringBuilder();
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
}

class BackTrack {
    void Mknum(int start, String str) {
        if(start == algo_15649.M) {
            algo_15649.sb.append(str).append("\n");
        } else {
            start++;
            for(int i = 1; i <= algo_15649.N; i++) {
                if(!algo_15649.S.contains(i)) {
                    algo_15649.S.push(i);
                    Mknum(start, str + i + " ");
                    algo_15649.S.pop();
                }
            }
        }
        return;
    }
}
