package BOJ_Binary_Search;
import java.io.*;
import java.util.*;

public class algo_2100 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stk.nextToken()), C = Integer.parseInt(stk.nextToken());
        int[] Position = new int[N];
        int min, max, ans = 0;

        for(int i = 0; i < N; i++) {
            Position[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(Position);
        min = 1;
        max = Position[N - 1] - Position[0];

        while(min <= max) {
            int mid = (min + max) / 2;
            int cnt = 1;
            int spot = Position[0];
            for(int i = 1; i < N; i++) {
                int d = Position[i] - spot;
                if(d >= mid) {
                    cnt++;
                    spot = Position[i];
                }
            }

            if(cnt < C) {
                max = mid - 1;
            } else {
                if(ans < mid) ans = mid;
                min = mid + 1;
            }
        }

        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}