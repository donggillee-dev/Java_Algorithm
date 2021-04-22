package BOJ.MATH.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//문제를 제대로 안 읽어서 계속 틀린 문제
//그리고 반례 보는 눈을 좀 더 기르자
//풀이 시간 : 14분

public class boj_4299 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int sum = Integer.parseInt(stk.nextToken()), sub = Integer.parseInt(stk.nextToken());
        int sum2 = sum + sub, sub2 = sum - sub;
        boolean flag = sum2 % 2 != 0 || sub2 % 2 != 0 || sum < sub;

        if(flag) {
            System.out.println(-1);
        } else {
            System.out.print(sum2 / 2);
            System.out.print(" ");
            System.out.print(sub2 / 2);
        }
    }
}
