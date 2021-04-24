package BOJ.DP.Gold;

import java.io.*;
import java.util.*;

//Logic
//이전에 SW마에스트로에서 풀었던 전자시계 문제와 비슷
//최대값을 구하는 방식은 자릿수를 최대한 늘리기만 하는 그리디 알고리즘을 적용하고
//최소값은 점화식을 구해서 =>  dp[i] = min(dp[i - 2] + min[2], dp[i - 3] + min[3], .... dp[i - 7] + min[7])

public class boj_3687 {
    private static long[] min = new long[101];
    private static void initMin() {
        //1 : 2
        //2 : 5
        //3 : 5
        //4 : 4
        //5 : 5
        //6 : 6
        //7 : 3
        //8 : 7
        //9 : 6
        //0 : 6
        Arrays.fill(min, Long.MAX_VALUE);
        min[2] = 1;
        min[3] = 7;
        min[4] = 4;
        min[5] = 2;
        min[6] = 6;
        min[7] = 8;
        min[8] = 10;
        String[] add = {"1", "7", "4", "2", "0", "8"};

        for(int i = 9; i <= 100; i++) {
            for(int j = 2; j <= 7; j++) {
                String tmp = min[i - j] + add[j - 2];
                min[i] = Math.min(min[i], Long.parseLong(tmp));
            }
        }
    }
    private static String getMax(int num) {
        StringBuilder max = new StringBuilder();
        int length = num / 2;

        if((num & 1) == 0) {
            max.append("1");
        } else {
            max.append("7");
        }

        for(int i = 0; i < length - 1; i++) {
            max.append("1");
        }

        return max.toString();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        initMin();
        while(t --> 0) {
            int num = Integer.parseInt(br.readLine());

            sb.append(min[num]).append(" ").append(getMax(num)).append("\n");
        }
        System.out.print(sb);
    }
}
