package BOJ.IMP.Bronze;

import java.io.*;
import java.util.StringTokenizer;

public class boj_2475 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer stk = new StringTokenizer(br.readLine());
        int sum = 0;
        while(stk.hasMoreTokens()) {
            int num = Integer.parseInt(stk.nextToken());
            num *= num;
            sum += num;
        }

        sb.append(sum%10).append("\n");
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
