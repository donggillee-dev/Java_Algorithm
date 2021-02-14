package BOJ.IMP.Bronze;

import java.io.*;
import java.util.StringTokenizer;

public class boj_10951 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;

        String input = "";
        while((input = br.readLine()) != null){
            stk = new StringTokenizer(input);
            int A = Integer.parseInt(stk.nextToken());
            int B = Integer.parseInt(stk.nextToken());

            sb.append(A+B).append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
