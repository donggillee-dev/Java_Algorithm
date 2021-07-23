package BOJ.DP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//부분합 => sum[i] - sum[j]
//특정 구간의 나머지합 => ( sum[i] - sum[j] ) % m
//나머지가 0인거 찾아야된다? => ( sum[i] - sum[j] ) % m == 0
// => sum[i] % m == sum[j] % m
//나머지가 같은 것들끼리의 조합을 구하면될듯?
//0인 애들은 그냥 그 자체 하나만 있어도 가능이니까 조합 없이 하나 넣고 그 다음 조합

//풀이 시간 : 50분

public class boj_10986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        int sum = 0;
        long[] rem = new long[m];

        stk = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            sum += Integer.parseInt(stk.nextToken());
            sum %= m;

            rem[sum]++;
        }

        long ans = rem[0];

        for(int i = 0; i < m; i++) {
            ans += (rem[i] * (rem[i] - 1)) / 2;
        }

        System.out.println(ans);
    }
}
