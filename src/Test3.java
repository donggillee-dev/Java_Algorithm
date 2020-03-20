import java.io.*;
import java.util.*;

public class Test3 {
    static int N;
    static int K;
    static int ans;
    static int[] nums;
    static StringTokenizer stk;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken());

        nums = new int[N];
        stk = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(stk.nextToken());
        }
        ans = nums[N - 4] - nums[0];
        if(N == K) ans = 0;
        if(K == 1) {
            ans = nums[N - 1] - nums[0];
        }
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}
