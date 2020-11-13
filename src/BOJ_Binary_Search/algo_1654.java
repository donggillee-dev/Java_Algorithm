package BOJ_Binary_Search;
import java.io.*;
import java.util.*;

public class algo_1654 {
    static int N, M;
    static long start = 1, end = 0, ans = -1;
    static long[] Cable;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        M = Integer.parseInt(stk.nextToken());
        N = Integer.parseInt(stk.nextToken());
        Cable = new long[M];

        for(int i = 0; i < M; i++) {
            Cable[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(Cable);
        end = Cable[M - 1];

        while(start <= end) {
            Search();
        }
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void Search() {
        long mid = (start + end) / 2;
        int cnt = 0;
        for(int i = 0; i < M; i++) {
            cnt += (Cable[i] / mid);
        }

        if(cnt >= N) {
            start = mid + 1;
            if(ans < mid) ans = mid;
        } else {
            end = mid - 1;
        }
    }
}
