package BOJ.KMP.Platinum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj_1787 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] table = new int[n][n];
        StringBuilder sb = new StringBuilder(br.readLine());
        sb.reverse();

        int answer = 0;

        for(int i = 0; i < n; i++) {
            sb.setLength(n - i);
            int length = (n - i);

            for(int k = 1, j = 0; k < length; k++) {
                while(j > 0 && sb.charAt(j) != sb.charAt(k)) {
                    j = table[i][j - 1];
                }
                if(sb.charAt(j) == sb.charAt(k)) {
                    j += 1;
                    table[i][k] = j;
                }
            }
        }


    }
}
