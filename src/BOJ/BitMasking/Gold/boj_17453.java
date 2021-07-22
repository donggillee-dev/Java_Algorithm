package BOJ.BitMasking.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class boj_17453 {
    private static int m;
    private static boolean[] visited;
    private static BigInteger[] button;
    //dfs로 모든 스위치 경우를 비트마스킹 해보면 될까? => 시간초과 날듯?

    private static void dfs(int depth) {
        for(int i = depth; i < m; i++) {
            dfs(i + 1);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

//        dfs()
    }
}
