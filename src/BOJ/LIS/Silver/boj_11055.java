package BOJ.LIS.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//Input의 최대가 1000이기에 O(N^2) ver의 LIS알고리즘으로 해결 가능
//이전까지의 dp값에 현재값을 더한게 현재 dp의 값보다 큰 경우 대입 , 또한 arr[j] 가 arr[i]보다 커야만 Increase임
//풀이 시간 : 5분

public class boj_11055 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] dp = new int[n];
        StringTokenizer stk = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        int max = arr[0];
        dp[0] = arr[0];

        for(int j = 1; j < n; j++) {
            dp[j] = arr[j];
            for(int i = 0; i < j; i++) {
                if(arr[i] < arr[j] && dp[j] < dp[i] + arr[j]) {
                    dp[j] = dp[i] + arr[j];
                }
            }
            if(dp[j] > max) max = dp[j];
        }

        System.out.println(max);
    }
}
