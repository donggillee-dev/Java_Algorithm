package BOJ.TwoPointer.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//Logic
//값을 일단 정렬하고
//오른쪽 포인터를 이동 => 왼쪽 포인터와의 값의 차이가 m보다 크게 되면, 왼쪽 포인터 이동
//값의 차이가 m보다 작게 되면 오른쪽 이동
//차이가 m과 동일하다? 바로 종료

//풀이 시간 : 25분

public class boj_2230 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        int[] arr = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int ans = Integer.MAX_VALUE;
        int left = 0, right = 0;

        while(right < n) {
            if(arr[right] - arr[left] < m) { //두 수의 차이가 m보다 작아지면 오른쪽 늘려주고
                right++;
                continue;
            }

            if(arr[right] - arr[left] == m) { //차이값이 m과 동일해지는 경우 탈출
                ans = m;
                break;
            }

            ans = Math.min(ans, arr[right] - arr[left]); //그 외의 경우에는 왼쪽을 좁혀와본다
            left++;
        }

        System.out.println(ans);
    }
}
