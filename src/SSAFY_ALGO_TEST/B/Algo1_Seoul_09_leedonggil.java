package SSAFY_ALGO_TEST.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Algo1_Seoul_09_leedonggil {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;
        int t = Integer.parseInt(br.readLine());
        int[] arr = new int[10];

        for(int tc = 1; tc <= t; tc++) {
            int ans = 0;
            stk = new StringTokenizer(br.readLine());
            for(int i = 0; i < 10; i++) {
                arr[i] = Integer.parseInt(stk.nextToken());
            }

            int height = arr[0];
            for(int i = 0; i < 5; i++) {
                stk = new StringTokenizer(br.readLine());
                int up = Integer.parseInt(stk.nextToken());
                int down = Integer.parseInt(stk.nextToken());
                boolean flag = true;
                for(int j = 1; j < 10; j++) {
                    int diff = Math.abs(arr[j] - height);

                    if(diff > down || diff > up) {
                        flag = false;
                        break;
                    }
                    height = arr[j];
                }

                if(flag) ans++;
            }

            sb.append("#" + tc + " ").append(ans).append("\n");
        }

        System.out.print(sb);
    }
}
