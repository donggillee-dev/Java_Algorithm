package BOJ.TwoPointer.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//Logic
//투 포인터로 풀 수 있는 문제 2467문제와 매우 동일하다
public class boj_2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer stk = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        Arrays.sort(arr);
        int left_idx = 0, right_idx = N - 1;
        int left_ans = arr[left_idx], right_ans = arr[right_idx];
        int ans = Integer.MAX_VALUE;

        while(left_idx != right_idx) {
            int sum = arr[right_idx] + arr[left_idx];

            if(Math.abs(sum) < ans) {
                ans = Math.abs(sum);
                left_ans = arr[left_idx];
                right_ans = arr[right_idx];
            }

            if(sum < 0) {
                left_idx++;
            } else if(sum > 0) {
                right_idx--;
            } else {
                right_ans = arr[right_idx];
                left_ans = arr[left_idx];
                break;
            }
        }
        System.out.printf("%d %d", left_ans, right_ans);
    }
}
