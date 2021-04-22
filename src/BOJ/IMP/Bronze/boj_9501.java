package BOJ.IMP.Bronze;

import java.io.*;
import java.util.*;

//Logic
//그냥 사칙연산 + 구현 문제
//풀이 시간 : 5분

public class boj_9501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            stk = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(stk.nextToken());
            int D = Integer.parseInt(stk.nextToken());
            int ans = 0;

            while (N-- > 0) {
                stk = new StringTokenizer(br.readLine());
                int v = Integer.parseInt(stk.nextToken());
                int f = Integer.parseInt(stk.nextToken());
                int c = Integer.parseInt(stk.nextToken());

                if(D / (double)v <= f / (double)c) ans++;
            }

            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
