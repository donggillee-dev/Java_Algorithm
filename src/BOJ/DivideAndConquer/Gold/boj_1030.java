package BOJ.DivideAndConquer.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1030 {
    private static int s, n, k, r1, r2, c1, c2;
    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        s = Integer.parseInt(stk.nextToken());
        n = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());
        r1 = Integer.parseInt(stk.nextToken());
        r2 = Integer.parseInt(stk.nextToken());
        c1 = Integer.parseInt(stk.nextToken());
        c2 = Integer.parseInt(stk.nextToken());
    }
    public static void main(String[] args) throws IOException {
        input();
    }
}
