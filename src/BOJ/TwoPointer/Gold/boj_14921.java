package BOJ.TwoPointer.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//두 포인터를 이용하여 풀 수 있다
//왼쪽 가장 작은 값부터 시작하는 포인터와
//오른쪽 가장 큰 값부터 시작하는 포인터 두개로 시작해서
//값이 크면 오른쪽꺼 이동
//값이 너무 작으면 왼쪽꺼 이동

//풀이 시간 : 12분

public class boj_14921 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer stk = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        int idx1 = 0, idx2 = n - 1;
        int ans = arr[idx1] + arr[idx2], num = 0;

        while(idx1 < idx2) {
            num = arr[idx1] + arr[idx2];

            if(Math.abs(ans) > Math.abs(num)) ans = num;

            if(num > 0) {
                idx2--;
            } else if(num < 0) {
                idx1++;
            } else {
                break;
            }
        }

        System.out.println(ans);
    }
}
