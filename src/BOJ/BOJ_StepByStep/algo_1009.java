package BOJ.BOJ_StepByStep;
import java.io.*;
import java.util.*;

public class algo_1009 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        StringTokenizer stk;
        for(int i = 0; i < T; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int cnt = 1;
            for(int j = 1; j <= b; j++) {
                cnt *= a;
                cnt %= 10;
            }
            if(cnt == 0) sb.append(10).append("\n");
            else sb.append(cnt).append("\n");
        }
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}
