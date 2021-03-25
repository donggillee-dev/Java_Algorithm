package SWEA.D6;

import java.io.*;
import java.util.*;

public class PersonNetwork {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TestCase = Integer.parseInt(br.readLine());
        StringTokenizer stk;

        for(int tc = 1; tc <= TestCase; tc++) {
            stk = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(stk.nextToken());
            int[][] arr = new int[N][N];

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(stk.nextToken());
                    if(i != j && arr[i][j] == 0) arr[i][j] = 987654321;
                }
            }

            for(int k = 0; k < N; k++) {
                for(int i = 0; i < N; i++) {
                    for(int j = 0; j < N; j++) {
                        arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                    }
                }
            }

            int answer = 987654321;

            for(int i = 0; i < N; i++) {
                int cnt = 0;
                for(int j = 0; j < N; j++)
                        cnt += arr[i][j];
                if(cnt < answer) answer = cnt;
            }

            sb.append("#" + tc + " " + answer).append("\n");
        }
        System.out.print(sb);
    }
}
