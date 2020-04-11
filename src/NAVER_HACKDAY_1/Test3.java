package NAVER_HACKDAY_1;
        import java.io.*;
        import java.util.*;

public class Test3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Solution sol = new Solution();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        int k = Integer.parseInt(stk.nextToken());

        int ans = sol.solution(n,m,k);

        System.out.println(ans);
    }
}

class Solution {
    static int NUM = 1000000007;
    static int[][]DP;
    public int solution(int n, int m, int k) {
        int answer = -1;
        DP = new int[n + 1][m + 1];
        for(int i = 0; i <=n; i++) {
            Arrays.fill(DP[i], -1);
        }

        answer = dfs(0, 0, n, m, k);
        return answer;
    }
    public int dfs(int d, int sum, int n, int m, int k) {
        if(d == n) {
            if(sum < m) return 0;
            else if(sum == m) return 1;
            else return 0;
        }
        if(DP[d][sum] > 0) return DP[d][sum] % NUM;

        int depth = d + 1;
        DP[d][sum] = 0;
        for(int i = 1; i <= k; i++) {

            if(depth < n && sum + i < m) {
                DP[d][sum] += (dfs(depth, sum, n, m, k));
            }
        }
        System.out.println(d + " " + sum);
        return DP[d][sum] % NUM;
    }
}
