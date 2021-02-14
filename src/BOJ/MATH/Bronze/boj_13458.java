package BOJ.MATH.Bronze;
import java.io.*;
import java.util.*;

public class boj_13458 {
    static int N, B, C;
    static int[] Room;
    static long ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        StringTokenizer stk = new StringTokenizer(br.readLine());
        Room = new int[N];
        ans = N;
        for(int i = 0; i < N; i++) {
            Room[i] = Integer.parseInt(stk.nextToken());
        }
        stk = new StringTokenizer(br.readLine());
        B = Integer.parseInt(stk.nextToken());
        C = Integer.parseInt(stk.nextToken());
        for(int i = 0; i < N; i++) {
            int sub = Room[i] - B;
            if(sub > 0) {
                if((sub % C) != 0) ans += 1;
                ans += sub / C;
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
