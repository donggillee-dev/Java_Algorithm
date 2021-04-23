package BOJ.Sort.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.Arrays;

//Logic
//정렬을 해서 최대값에 cnt만큼 곱해줘봐서 최대의 값이 나오는지 아닌지를 체크해주면서 정답을 도출
//풀이 시간 : 12분

public class boj_1246 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        Integer[] arr = new Integer[m];

        for(int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr, Collections.reverseOrder());
        int idx = n, ans = Integer.MIN_VALUE, last = 0;

        if(n >= m) idx = m;

        for(int i = 0, cnt = 1; i < idx; i++, cnt++) {
            if(cnt * arr[i] > ans) {
                ans = cnt * arr[i];
                last = arr[i];
            }
        }

        System.out.println(last + " " + ans);
    }
}
