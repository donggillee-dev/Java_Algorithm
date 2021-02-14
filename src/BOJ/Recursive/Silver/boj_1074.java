package BOJ.Recursive.Silver;

import java.io.*;
import java.util.StringTokenizer;

public class boj_1074 {
    static StringBuilder sb = new StringBuilder();
    static int answer = 0;
    static int r, c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        r = Integer.parseInt(stk.nextToken());
        c = Integer.parseInt(stk.nextToken());

        int width = (int)Math.pow(2, N);
        recursive(width, 0, 0, width, width);
        sb.append(answer).append("\n");
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
    private static void recursive(int width, int start_x, int start_y, int end_x, int end_y) {
        if(width == 2) {
            for(int i = start_x; i < end_x; i++) {
                for(int j = start_y; j < end_y; j++) {
                    if(i == r && j == c) return;
                    answer++;
                }
            }
            return;
        }


        if((start_x <= r && end_x - width / 2 > r) && (start_y <= c && end_y - width / 2 > c)) {
            recursive(width / 2, start_x, start_y, end_x  - width / 2, end_y - width / 2);
        } else {
            answer += (width / 2) * (width / 2);
            if((start_x <= r && end_x - width / 2 > r) && (end_y - width / 2 <= c && end_y > c)) {
                recursive(width / 2, start_x, end_y - width / 2, end_x - width / 2, end_y);
            } else {
                answer += (width / 2) * (width / 2);
                if((end_x - width / 2 <= r && end_x > r) && (start_y <= c && end_y - width / 2 > c)) {
                    recursive(width / 2, end_x - width / 2, start_y, end_x, end_y - width / 2);
                } else {
                   answer += (width / 2) * (width / 2);
                    if((end_x - width / 2 <= r && end_x > r) && (end_y - width / 2 <= c && end_y > c)) {
                        recursive(width / 2, end_x - width / 2, end_y - width / 2, end_x, end_y);
                    }
                }
            }
        }
        return;
    }
}
