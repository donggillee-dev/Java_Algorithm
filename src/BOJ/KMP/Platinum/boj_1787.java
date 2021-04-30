package BOJ.KMP.Platinum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//Logic
//KMP의 특성을 이용해서 재귀적으로 prefix와 suffix가 같으며 0이 아닌 가장 작은 길이를 구해야함
//길이가 n인 부분 문자열에 대해서 pi[n]이 5이면 1 .... 5는 n - 4 ... n과 동일, 여기서 pi[5]의 값이 3이라고 하면 x1 = x3, x2 = x4, x3 = x5이다
//여기서 x3 = x7, x4 = x8, x5 = x9이다 x1 = x7, x2 = x8, x3 = xn이므로 재귀적으로 이 과정을 반복해 최소의 prefix = suffix값을 찾아낼 수 있음
//정답을 보게 되었지만 이해하는데 오래걸린 문제...
//풀이 시간 : 5시간
public class boj_1787 {
    private static int rec(int idx, int[] pi, int[] dp) {
        if(idx < 0 || pi[idx] == 0) return 987654321;

        if(dp[idx] != -1)
            return dp[idx];

        return dp[idx] = Math.min(pi[idx], rec(pi[idx] - 1, pi, dp));
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] pi = new int[n];
        int[] dp = new int[n];
        char[] arr = br.readLine().toCharArray();

        dp[0] = -1;
        for(int i = 1, j = 0; i < n; ++i) {
            dp[i] = -1;
            while(j != 0 && arr[i] != arr[j]) {
                j = pi[j - 1];
            }
            if(arr[i] == arr[j]) {
                ++j;
                pi[i] = j;
            }
        }

        long answer = 0;

        for(int i = 0; i < n; i++) {
            int ret = rec(i, pi, dp);

            if(ret != 987654321) {
                answer += (long)(i - ret + 1);
            }
        }

        System.out.print(answer);
    }
}
