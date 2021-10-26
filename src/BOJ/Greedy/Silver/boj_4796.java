package BOJ.Greedy.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//연속된 날짜로 휴가기간을 나누고
//나눈 나머지에서 사용가능한 캠핑일과 연속된 날짜로 % 연산한 값 중 최소값을 더해주면 된다
//실수한 것 : 그냥 % 연산한 값을 넣어주면 안됨 -> 연속 8일 중 1일 사용 가능하면 2 ~ 7일을 더해줄 수 있기 떄문

public class boj_4796 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long tc = 0, L = 0, P = 0, V = 0;
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;

        while(true) {
            tc++;
            stk = new StringTokenizer(br.readLine());

            L = Long.parseLong(stk.nextToken());
            P = Long.parseLong(stk.nextToken());
            V = Long.parseLong(stk.nextToken());

            if(L == 0 && P == 0 && V == 0) break;

            long ans = (V / P) * L + Math.min(V % P, L);

            sb.append("Case " + tc + ": " + ans).append("\n");
        }

        System.out.print(sb);
    }
}
