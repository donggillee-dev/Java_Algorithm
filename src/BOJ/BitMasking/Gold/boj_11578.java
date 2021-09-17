package BOJ.BitMasking.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//각 팀원별로 풀 수 있는 문제를 비트마스킹을 통해 하면 된다
//비트마스킹시 |= 주의, 비트 이동 주의

public class boj_11578 {
    private static int n, m, all;
    private static int ans = 987654321;

    private static void comb(int idx, int cnt, int num, int[] arr) {
        if(idx == m) {
            if (num == all) {
                ans = Math.min(ans, cnt);
            }
            return;
        }

        comb(idx + 1, cnt + 1, num | arr[idx], arr);
        comb(idx + 1, cnt, num, arr);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        int[] arr = new int[m];

        all = (1 << n) - 1;

        for(int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int o = Integer.parseInt(stk.nextToken());

            for(int j = 0; j < o; j++) {
                arr[i] |= (1 << (Integer.parseInt(stk.nextToken()) - 1));
            }
        }

        comb(0, 0, 0, arr);

        if(ans == 987654321) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }
}
