package BOJ.DP.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//Logic
//누적합으로 헤결할 수 있는 문제
//i까지의 누적합을 구한다음 i - k에 대한 누적합에 대해서 빼주면 k만큼의 구간합을 구할 수 있다
//그 중 max값을 도출

//풀이 시간 : 10분
public class boj_2559 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int k = Integer.parseInt(stk.nextToken());

        int[] arr = new int[n];
        stk = new StringTokenizer(br.readLine());
        arr[0] = Integer.parseInt(stk.nextToken());

        for(int i = 1; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
            arr[i] += arr[i - 1];
        }

        int max = arr[k - 1];

        for(int i = k; i < n; i++) {
            int sum = arr[i] - arr[i - k];

            if(sum > max) max = sum;
        }

        System.out.println(max);
    }
}
