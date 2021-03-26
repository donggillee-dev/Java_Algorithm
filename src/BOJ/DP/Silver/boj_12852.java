package BOJ.DP.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_12852 {
    private static int[] DP;
    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        DP = new int[N + 1];

        for (int i = 2; i <= N; i++) {
            DP[i] = DP[i - 1] + 1;

            boolean isThree = (i % 3 == 0);
            boolean isTwo = (i % 2 == 0);

            if (isTwo && DP[i] > DP[i / 2] + 1) DP[i] = DP[i / 2] + 1;
            if (isThree && DP[i] > DP[i / 3] + 1) DP[i] = DP[i / 3] + 1;
        }
        ans(N);
        System.out.println(DP[N]);
        System.out.println(sb);
    }
    private static void ans(int num) {
        if(num == 0) return;

        sb.append(num + " ");

        if(num % 3 == 0 && DP[num] == DP[num / 3] + 1) ans(num / 3);
        else if(num % 2 == 0 && DP[num] == DP[num / 2] + 1) ans(num / 2);
        else if(num - 1 >= 0 && DP[num] == DP[num - 1] + 1) ans(num - 1);
    }
}
