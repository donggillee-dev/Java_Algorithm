package BOJ.BitMasking.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//Logic
//비트마스킹을 이용해서 풀 수 있는 문제
//비트연산할때 계속 실수하는거!!! => |(and)연산을 하면 특정 비트에 대해서 true(1), false(0)이 출력되는게 아니라 값이 나옴
    // ex) 1111 | 1000 => 8출력

//풀이 시간 : 10분
public class boj_11723 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer stk;

        int num = 0;
        int tmp = 0;
        while(N --> 0) {
            stk = new StringTokenizer(br.readLine());
            String cmd = stk.nextToken();

            switch(cmd) {
                case "add":
                    tmp = Integer.parseInt(stk.nextToken());
                    num |= (1 << (tmp - 1));
                    break;
                case "remove":
                    tmp = Integer.parseInt(stk.nextToken());
                    if((num & (1 << (tmp - 1))) != 0) {
                        num ^= (1 << (tmp - 1));
                    }
                    break;
                case "check":
                    tmp = Integer.parseInt(stk.nextToken());
                    if((num & (1 << (tmp - 1))) != 0) sb.append("1\n");
                    else sb.append("0\n");
                    break;
                case "toggle":
                    tmp = Integer.parseInt(stk.nextToken());
                    num ^= (1 << (tmp - 1));
                    break;
                case "all":
                    num = (1 << 20) - 1;
                    break;
                case "empty":
                    num = 0;
                    break;
            }
        }
        System.out.print(sb);
    }
}
