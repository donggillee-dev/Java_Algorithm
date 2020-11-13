package BOJ_Binary_Search;
import java.io.*;
import java.util.*;

public class algo_2805 {
    static int N;
    static long M, start, end, ans = -1;
    static long[] Trees;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        Trees = new long[N];

        stk = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            Trees[i] = Integer.parseInt(stk.nextToken());
        }

        Arrays.sort(Trees);
        start = 0;
        end = Trees[N - 1];
        Search();
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void Search() {
        while(start <= end) {
            long mid = (start + end) / 2;
            long ret = Cutting(mid);
            if(ret >= M) {
                start = mid + 1;
                if(mid > ans) ans = mid;
            } else {
                end = mid - 1;
            }
        }
    }
    private static long Cutting(long h) {
        long ret = 0;
        for(int i = 0; i < N; i++) {
            long tmp = Trees[i] - h;
            if(tmp >= 0)
                ret += tmp;
        }
        return ret;
    }
}
