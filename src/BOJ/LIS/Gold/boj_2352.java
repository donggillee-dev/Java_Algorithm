package BOJ.LIS.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//Logic
//LIS의 대표문제 , n값으로 인해 nlogn으로 풀어야함
//풀이 시간 : 20분
public class boj_2352 {
    private static int lower_bound(int end, int val, int[] dp) {
        int start = 0;

        while(start < end) {
            int mid = (start + end) / 2;

            if(dp[mid] >= val) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return end;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] dp = new int[n];
        StringTokenizer stk = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        int idx = 0;

        dp[0] = arr[0];

        for(int i = 1; i < n; i++) {
            if(dp[idx] < arr[i]) {
                dp[++idx] = arr[i];
            } else {
                int ii = lower_bound(idx, arr[i], dp);

                dp[ii] = arr[i];
            }
        }

        System.out.println(idx + 1);
    }
}
