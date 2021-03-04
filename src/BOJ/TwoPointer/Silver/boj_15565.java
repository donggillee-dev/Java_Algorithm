package BOJ.TwoPointer.Silver;

import java.io.*;
import java.util.StringTokenizer;

public class boj_15565 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken()), K = Integer.parseInt(stk.nextToken());
        int start = 0, end = 0;
        String[] arr = br.readLine().split(" ");

        int cnt = 0;
        final int max = 987654321;
        int answer = max;

        if(arr[end].equals("1")) cnt++;

        while(end <= N) {
            if(cnt == K) {
                int width = end - start + 1;
                if(max > width) answer = width;
            }
            if(cnt < K) {
                if(end + 1 == N) break;
                if(end < N && arr[++end].equals("1")) cnt++;
            } else {
                if(arr[start].equals("1")) cnt--;
                start++;
            }
        }
        if(answer == max) {
            answer = -1;
        }
        sb.append(answer);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
