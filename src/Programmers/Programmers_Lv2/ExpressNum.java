package Programmers.Programmers_Lv2;

import java.io.*;

public class ExpressNum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int result = 0;
        int start = 1;

        while(start <= N) {
            int sum = 0;
            for(int i = start; i <= N; i++) {
                sum += i;
                if(sum > N || sum == N) break;
            }
            start++;
            if(sum == N) result++;
        }

        sb.append(result).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}
