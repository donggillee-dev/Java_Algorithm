package BOJ.DP.Gold;

import java.io.*;
import java.util.*;
//Logic

//DP를 이용하여 풀 수 있는 문제이다
//각 시작점과 각 종료지점을 가지고 펠린드림인지 아닌지 DP배열을 만들어주고
//결과적으로 질문에 대한 펠린드롬 여부를 출력

public class boj_10942 {
    private static int[][] DP = new int[2001][2001];
    private static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk;

        int N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];

        stk = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                palin(i, j);
            }
        }
        int M = Integer.parseInt(br.readLine());

        while(M --> 0) {
            stk = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(stk.nextToken()), e = Integer.parseInt(stk.nextToken());

            if(DP[s][e] == 1) bw.write('1');
            else bw.write('0');
            bw.newLine();
        }
        bw.flush();
    }
    private static int palin(int s, int e) {
        if(DP[s][e] != 0) return DP[s][e];
        if(s > e) {
            DP[s][e] = -1;
            return -1;
        }

        if(arr[s] == arr[e]) {
            if(s == e || s + 1 == e) {
                DP[s][e] = 1;
                return 1;
            }

            return DP[s][e] = palin(s + 1, e - 1);
        } else {
            DP[s][e] = -1;
            return -1;
        }
    }
}
