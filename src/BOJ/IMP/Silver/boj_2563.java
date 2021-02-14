package BOJ.IMP.Silver;

import java.io.*;
import java.util.StringTokenizer;

public class boj_2563 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        boolean[][] Paper = new boolean[101][101];
        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        StringTokenizer stk;
        for(int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(stk.nextToken());
            int down = Integer.parseInt(stk.nextToken());

            for(int x = left; x < left + 10; x++) {
                for(int y = down; y < down + 10; y++) {
                    if(Paper[y][x]) continue;
                    Paper[y][x] = true;
                    answer++;
                }
            }
        }


        sb.append(answer).append("\n");
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
