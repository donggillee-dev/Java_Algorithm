package BOJ.Greedy.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_11501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while(t --> 0) {
            int n = Integer.parseInt(br.readLine());
            int cnt = 1, cur = 0;
            long sum = 0;
            StringTokenizer stk = new StringTokenizer(br.readLine());

            cur = Integer.parseInt(stk.nextToken());

            for(int i = 1; i < n; i++) {
                int next = Integer.parseInt(stk.nextToken());

                if(cur < next) {
                    if(i == n - 1 && cnt != 1) {
                        sum += (long)cur * cnt;
                    } else {
                        cnt++;
                    }
                } else {
                    if(cnt != 1)
                        sum += (long)cur * cnt;
                    cnt = 1;
                }
                cur = next;
            }
            sb.append(sum).append("\n");
        }

        System.out.print(sb);
    }
}
