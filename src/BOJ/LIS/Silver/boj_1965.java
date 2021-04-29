package BOJ.LIS.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//Logic
//어제 풀어봤던 LIS를 쉬운 문제로 하나더 풀어봄
//풀이 시간 : 5분
public class boj_1965 {
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
        StringTokenizer stk;
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n], dp = new int[n];
        stk = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        int idx = 0;
        dp[idx] = arr[0];

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
