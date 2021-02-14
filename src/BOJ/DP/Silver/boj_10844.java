package BOJ.DP.Silver;

import java.io.*;
import java.util.*;

public class boj_10844 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        long[][] An = new long[N + 1][10];
        long ans = 0;

        Arrays.fill(An[1], 1);

        if(N >= 2) {
            Arrays.fill(An[2], 2);
            An[2][9] = 1;
            for(int i = 3; i <= N; i++) {
                for(int j = 1; j <= 9; j++) {
                    if(j == 1) {
                        An[i][j] = (An[i - 2][j] + An[i - 1][j + 1]) % 1000000000;
                    } else if(j == 9) {
                        An[i][j] = An[i - 1][j - 1] % 1000000000;
                    } else {
                        An[i][j] = (An[i - 1][j - 1] + An[i - 1][j + 1]) % 1000000000;
                    }
                }
            }
        }

        for(int i = 1; i <= 9; i++) {
            ans += An[N][i];
        }
        sb.append(ans % 1000000000).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}
