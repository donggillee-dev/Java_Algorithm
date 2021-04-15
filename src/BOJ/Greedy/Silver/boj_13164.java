package BOJ.Greedy.Silver;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
//Logic
//백준 센서 문제와 동일
//원생들을 키 순으로 정렬해서 인접한 원생들의 키 차이를 구해놓고 키 차이를 정렬해서
//n - k개 만큼 차이를 다 더해준다 => 그루핑

public class boj_13164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());
        int sum = 0;
        int[] arr = new int[N];
        int[] diff = new int[N - 1];

        stk = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        Arrays.sort(arr);
        for(int i = 0; i < N - 1; i++) {
            diff[i] = arr[i + 1] - arr[i];
        }

        Arrays.sort(diff);
        for(int i = 0; i < N - K; i++) {
            sum += diff[i];
        }

        System.out.print(sum);
    }
}
