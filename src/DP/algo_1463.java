package DP;

import java.io.*;
import java.util.*;

public class algo_1463 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] An = new int[N + 1];
        Arrays.fill(An, Integer.MAX_VALUE);
        An[N] = 0;

        for(int i = N; i >= 2; i--) {
            if(i % 3 == 0) {
                if(An[i / 3] > An[i] + 1) An[i / 3] = An[i] + 1;
            }
            if(i % 2 == 0) {
                if(An[i / 2] > An[i] + 1) An[i / 2] = An[i] + 1;
            }
            if(An[i - 1] > An[i] + 1) An[i - 1] = An[i] + 1;
        }

        sb.append(An[1]).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}
