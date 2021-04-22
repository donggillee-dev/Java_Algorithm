package BOJ.MATH.Bronze;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

//Logic
//큰 수 문제, BigInteger를 사용하면 되는 문제
//풀이 시간 : 4분

public class boj_1271 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        BigInteger n = new BigInteger(stk.nextToken());
        BigInteger m = new BigInteger(stk.nextToken());

        System.out.println(n.divide(m).toString());
        System.out.println(n.remainder(m).toString());
    }
}
