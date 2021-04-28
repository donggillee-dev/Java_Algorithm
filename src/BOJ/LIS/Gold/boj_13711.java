package BOJ.LIS.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//Logic
//골드2가 아닌거같은 문제... 해답을 알기까지 진짜 오래걸렸다...
//a배열의 각 값이 몇번째로 들어왔는지 idx를 저장해준다
//그럼 b배열에 들어오는 값을 가지고 a배열에 저장된 해당값의 들어온 순서를 가지고 b배열에 저장해준다
//즉 b배열에 저장된 값은 a수열의 값이 들어온 순서를 토대로 b배열의 들어온 순서를 재정의 한것 => 이 재정의 된 배열을 가지고 LIS를 돌리면 두 수열의 LCS를 구할 수 있다
//쉽게 설명하자면 b에 들어온 숫자가 a의 해당 숫자가 몇번째에 들어왔는지 매핑이 되므로 들어온 순서대로 가장 긴 부분 증가 수열을 하면 a와 b가 매핑이 되며 가장 긴 부분 문자열 길이를 뽑아낼 수 있음
//풀이 시간 : 90분
public class boj_13711 {
    private static int lower_bound(int end, int val, int[] dp) {
        int start = 1;

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
        int[] a = new int[n + 1], b = new int[n + 1];
        int[] dp = new int[n + 1];

        StringTokenizer stk = new StringTokenizer(br.readLine());

        for(int i = 1; i <= n; i++) {
            a[Integer.parseInt(stk.nextToken())] = i;
        }

        stk = new StringTokenizer(br.readLine());

        for(int i = 1; i <= n; i++) {
            b[i] = a[Integer.parseInt(stk.nextToken())];
        }

        int idx = 0;
        for(int i = 1; i <= n; i++) {
            if(dp[idx] < b[i]) {
                dp[++idx] = b[i];
            } else {
                int ii = lower_bound(idx, b[i], dp);
                dp[ii] = b[i];
            }
        }

        System.out.println(idx);
    }
}
