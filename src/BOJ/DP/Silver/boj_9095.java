package BOJ.DP.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//Logic
//점화식을 세우는게 다인 문제
//dp(i) = dp(i - 1) + dp(i - 2) + dp(i - 3) => 이 점화식 생각났었는데 무시함...
//위 같은 점화식의 경우에는 재귀가 필수
//dp(i - 1) = dp(i - 2) + dp(i - 3) + dp(i - 4) 로 뻗어나가기 때문

//풀이 시간 : 30분
public class boj_9095 {
    public static int rec(int num, int[] arr) {
        if(num == 0) return 0;
        if(num == 1) return 1;
        if(num == 2) return 2;
        if(num == 3) return 4;
        if(arr[num] != 0) return arr[num];
        else {
            arr[num] = rec(num - 1, arr) + rec(num - 2, arr) + rec(num - 3, arr);
            return arr[num];
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int[] dp = new int[12];

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for(int i = 4; i <= 11; i++) {
            rec(i, dp);
        }

        while(T --> 0) {
            sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");
        }
        System.out.print(sb);
    }
}
