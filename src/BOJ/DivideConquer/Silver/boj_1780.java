package BOJ.DivideConquer.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//분할 정복 알고리즘으로 풀 수 있는 문제이다
//너비에 대해서 3씩 나눠가면서 재귀를 들어가면됨
//풀이 시간 : 12분

public class boj_1780 {
    private static int n;
    private static int[] ans = new int[3];
    private static int[][] arr;
    private static boolean isSame(int sx, int sy, int ex, int ey) {
        int num = arr[sx][sy];

        for(int i = sx; i < ex; i++) {
            for(int j = sy; j < ey; j++) {
                if(arr[i][j] != num) return false;
            }
        }

        return true;
    }
    private static void recur(int width, int sx, int sy) {
        if(isSame(sx, sy, sx + width, sy + width)) {
            ans[arr[sx][sy] + 1]++;
        } else {
            int tmp = width / 3;

            for(int i = sx; i < sx + width; i += tmp) {
                for(int j = sy; j < sy + width; j += tmp) {
                    recur(tmp, i, j);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        for(int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        recur(n, 0, 0);

        for(int i = 0; i < 3; i++) {
            sb.append(ans[i]).append("\n");
        }
        System.out.print(sb);
    }
}
