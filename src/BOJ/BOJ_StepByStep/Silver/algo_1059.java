package BOJ.BOJ_StepByStep.Silver;

import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class algo_1059 {
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] S = new int[N];
        int n;
        StringTokenizer stk = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(stk.nextToken());
        }
        Arrays.sort(S);
        n = Integer.parseInt(br.readLine());

        int start = 0;
        int end = S.length - 1;
        int mid = 0;

        while(start <= end) {
            mid = (start + end) / 2;

            if(S[mid] > n) {
                end = mid - 1;
            } else if(S[mid] < n){
                start = mid + 1;
            } else {
                break;
            }
        }

        int start_idx = 0;
        int end_idx = 0;

        if(n < S[mid]) {
            if(mid - 1 < 0) {
                start_idx = 0;
                end_idx = 1;
            } else {
                start_idx = mid - 1;
                end_idx = start_idx + 1;
            }
        } else if(n > S[mid]) {
            start_idx = mid;
            end_idx = start_idx + 1;
        }

        if(n == S[mid]) {
            sb.append(0).append("\n");
        } else {
            if(n < S[0]) {
                for(int i = 1; i <= S[0] - 2; i++) {
                    for(int j = i + 1; j <= S[0] - 1; j++) {
                        if(n >= i && n <= j) answer++;
                    }
                }
            } else {
                for(int i = S[start_idx] + 1; i <= S[end_idx] - 2; i++) {
                    for(int j = i + 1; j <= S[end_idx] - 1; j++) {
                        if(n >= i && n <= j) answer++;
                    }
                }
            }

            sb.append(answer).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
