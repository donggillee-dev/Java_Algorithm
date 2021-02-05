package SWEA.D3;

import java.io.*;
import java.util.StringTokenizer;

public class PerfectShuffle {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int TestCase = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= TestCase; tc++) {
            sb.append("#" + tc + " ");
            int N = Integer.parseInt(br.readLine());
            String[] arr = new String[N];
            StringTokenizer stk = new StringTokenizer(br.readLine());

            for(int i = 0; i < N; i++) {
                arr[i] = stk.nextToken();
            }

            int left_idx = 0;
            int right_idx = (N + 1) / 2;
            while(true) {
                if(right_idx == N) break;

                sb.append(arr[left_idx]).append(" ");
                sb.append(arr[right_idx]).append(" ");
                left_idx++;
                right_idx++;
            }

            for(int i = left_idx; i < (N + 1) / 2; i++) {
                sb.append(arr[i]).append(" ");
            }

            sb.delete(sb.length() - 1, sb.length());
            sb.append("\n");
        }

        bw.write(String.valueOf(sb));
        bw.close();
        br.close();
    }
}
