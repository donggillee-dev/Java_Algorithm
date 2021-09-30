package ShinhanCard2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution3 {
    private static int stoi(String str) {
        return Integer.parseInt(str);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()), ans = 0;
        StringTokenizer stk = new StringTokenizer(br.readLine());

        for(int i = 1; i <= n; i++) {
            int num = stoi(stk.nextToken());

            if(num != i) ans++;
        }

        System.out.println(ans);
    }
}
