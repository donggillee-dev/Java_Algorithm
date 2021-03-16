package SWEA;

import java.io.*;
import java.util.StringTokenizer;
//Logic
//month에 대해서 값을 입력받아 이전의 최소값에 현재 일, 1개월, 3개월, 1년권을 고려하여 최소값을 넣어주어
//Month[12]에 있는 최소값을 출력하여 주면 되는 DP문제 O(N)
public class SwimmingPool {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int[] price = new int[4];

        StringTokenizer stk;

        for(int tc = 1; tc <= T; tc++) {
            stk = new StringTokenizer(br.readLine());

            price[0] = Integer.parseInt(stk.nextToken());
            price[1] = Integer.parseInt(stk.nextToken());
            price[2] = Integer.parseInt(stk.nextToken());
            price[3] = Integer.parseInt(stk.nextToken());

            int[] month = new int[13];
            stk = new StringTokenizer(br.readLine());
            for(int i = 1; i <= 12; i++) {
                month[i] = Integer.parseInt(stk.nextToken());
                int p1 = month[i] * price[0] + month[i - 1];
                int p2 = month[i - 1] + price[1];
                int p3 = Integer.MAX_VALUE, p4 = Integer.MAX_VALUE;

                if(i >= 3) p3 = month[i - 3] + price[2];
                if(i == 12) p4 = month[i - 12] + price[3];

                month[i] = Math.min(Math.min(p1, p2), Math.min(p3, p4));
            }
            sb.append("#" + tc + " ").append(month[12]).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
