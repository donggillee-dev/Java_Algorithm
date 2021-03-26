package BOJ.DP.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Logic
//DP를 이용하여 K분만큼에 각 위치에서 넘어올때의 시간을 더해주면되는 문제이다
//K분 후에 도착 위치에 대해서 점화식을 세워주면 됨

public class boj_12849 {
    private static long[] rec(long[] DP) {
        long[] tmp = new long[8];
        tmp[0] = DP[1] + DP[3];
        tmp[1] = DP[0] + DP[2] + DP[3];
        tmp[2] = DP[1] + DP[3] + DP[4] + DP[5];
        tmp[3] = DP[0] + DP[1] + DP[2] + DP[5];
        tmp[4] = DP[2] + DP[5] + DP[6];
        tmp[5] = DP[3] + DP[2] + DP[4] + DP[7];
        tmp[6] = DP[4] + DP[7];
        tmp[7] = DP[5] + DP[6];

        for(int i = 0; i < 8; i++) {
            tmp[i] %= 1000000007;
        }

        return tmp;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] DP = {1, 0, 0, 0, 0, 0, 0, 0};
        int K = Integer.parseInt(br.readLine());

        while(K --> 0) {
            DP = rec(DP);
        }

        System.out.println(DP[0]);
    }
}
