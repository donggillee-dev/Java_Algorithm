package BOJ.FloydWarshall.Silver;

import java.io.*;
import java.util.*;

//Logic
//그냥 Floyd 사용하면 되는 문제이지만 안에 조건만 다르게 해주면됨 최소값이 아닌 경로 존재 여부니까 여부에 대해서만
//풀이 시간 : 5분

public class boj_11403 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if(i == k) continue;
                for (int j = 0; j < n; j++) {
                    if (arr[i][j] != 0 || arr[i][k] == 0 || arr[k][j] == 0) continue;
                    arr[i][j] = 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}