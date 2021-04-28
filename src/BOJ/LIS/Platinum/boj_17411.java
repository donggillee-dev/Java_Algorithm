package BOJ.LIS.Platinum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//Logic
//N이 100만이기에 세그먼트 트리 배우고 다시 도전...

public class boj_17411 {
    private static class Info {
        int idx, value;
        public Info(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }
    }
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
        Info[] tracking = new Info[n];


        StringTokenizer stk = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        dp[0] = arr[0];

        for(int i = 1, idx = 0; i < n; i++) {
            if(dp[idx] < arr[i]) {
                dp[++idx] = arr[i];
                tracking[i] = new Info(idx, arr[i]);
            } else {
                int ii = lower_bound(idx, arr[i], dp);
                dp[ii] = arr[i];
                tracking[i] = new Info(ii, arr[i]);
            }
        }

        return;
    }
}
