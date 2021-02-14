package BOJ.IMP.Bronze;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class boj_2920 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int prevNum1 = -1;
        int prevNum2 = 9;
        boolean isAscd = true;
        boolean isDesc = true;
        StringTokenizer stk = new StringTokenizer(br.readLine());

        while(stk.hasMoreTokens()) {
            int num = Integer.parseInt(stk.nextToken());
            if(isAscd) {
                if(prevNum1 < num) prevNum1 = num;
                else {
                    isAscd = false;
                }
            }

            if(isDesc) {
                if(prevNum2 > num) prevNum2 = num;
                else {
                    isDesc = false;
                }
            }
        }

        if(!isDesc && !isAscd) {
            sb.append("mixed").append("\n");
        } else if(isDesc) {
            sb.append("descending").append("\n");
        } else {
            sb.append("ascending").append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
