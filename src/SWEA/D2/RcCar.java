package SWEA.D2;

import java.io.*;
import java.util.StringTokenizer;

public class RcCar {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int TestCase = Integer.parseInt(br.readLine());
        StringTokenizer stk;

        for(int tc = 1; tc <= TestCase; tc++) {
            int cmLine = Integer.parseInt(br.readLine());

            int distance = 0;

            int curAcc = 0;
            for(int i = 0; i < cmLine; i++) {
                stk = new StringTokenizer(br.readLine());
                int isAccel = Integer.parseInt(stk.nextToken());
                int value = 0;

                if(isAccel != 0) {
                    value = Integer.parseInt(stk.nextToken());
                }

                if(isAccel == 1) {
                    curAcc += value;
                } else if(isAccel == 2) {
                    curAcc -= value;
                }
                if(curAcc < 0) curAcc = 0;

                distance += curAcc;
            }
            sb.append("#"+tc+" ").append(distance).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
