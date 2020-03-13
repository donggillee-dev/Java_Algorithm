import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk1 = new StringTokenizer(br.readLine());
        StringTokenizer stk2 = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stk1.nextToken()), K = Integer.parseInt(stk1.nextToken());
        int[] arr = new int[N];
        int tmp = 0, ans = 0;

        for(int i = 0; i < N; i++) {
            if(stk2.hasMoreTokens()) {
                arr[i] = Integer.parseInt(stk2.nextToken());
            }

            if(arr[i] == 1) continue;

            tmp++;

            if(tmp == K -1) {
                ans++;
                tmp = 0;
            }
        }
        if(tmp > 0) ans++;
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}