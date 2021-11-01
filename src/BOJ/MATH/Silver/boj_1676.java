package BOJ.MATH.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Logic
//10이라는 숫자가 5와 2로 이루어져있으며
//2의 개수보다 5의 개수가 적다는 특징으로 해결 가능
//다만 5의 제곱수의 경우에는 5의 개수가 하나씩 더 있으므로 1씩 추가 필요

public class boj_1676 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int ans = 0;

        while(N >= 5) {
            ans += (N / 5);
            N /= 5;
        }

        System.out.println(ans);
    }
}
