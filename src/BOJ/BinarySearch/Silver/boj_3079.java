package BOJ.BinarySearch.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//Logic
//구하려는 최소값에 대해서 이분탐색 하면 되는 문제
//이분 탐색 타겟을 설정하고 해당 시간에 수용할 수 있는 모든 심사대의 인원을 더해서 총 인원과 비교 및 값 도출
//풀이 시간 : 1시간
public class boj_3079 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //걸리는 시간에 대해서 이분 탐색하자!!
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        long[] arr = new long[n];
        long max = Long.MIN_VALUE;

        for(int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
            if(arr[i] > max) max = arr[i];
        }

        long left = 1, right = max * m;
        long result = max * m;

        while(left <= right) {
            long mid = (left + right) / 2;
            long cnt = 0;
            for(long t : arr) {
                cnt += (mid / t);
            }

            if(cnt < m) {
                left = mid + 1;
            } else {
                if(result > mid) {
                    result = mid;
                }
                right = mid - 1;
            }
        }

        System.out.println(result);
    }
}
