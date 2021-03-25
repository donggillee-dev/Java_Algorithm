package BOJ.KMP.Platinum;

import java.io.*;
import java.util.*;

public class boj_10266 {
    private static void makeTable (int N, int[] patnArr, int[] table) {
        int j = 0;

        for(int i = 1; i < N; i++) {
            while(j > 0 && patnArr[j] != patnArr[i]) {
                j = table[j - 1];
            }
            if(patnArr[j] == patnArr[i]) {
                j += 1;
                table[i] = j;
            }
        }
    }
    private static void KMP(int N, int[] compArr, int[] patnArr, int[] table) {
        int length = compArr.length, j = 0;

        for(int i = 0; i < length; i++) {
            while(j > 0 && compArr[i] != patnArr[j]) {
                j = table[j - 1];
            }

            if(compArr[i] == patnArr[j]) {
                if(j == N - 1) {
                    System.out.print("possile");
                    return;
                } else {
                    j++;
                }
            }
        }

        System.out.print("impossible");
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] compArr = new int[N * 2], patnArr = new int[N], table = new int[N];

        StringTokenizer stk = new StringTokenizer(br.readLine());
        for(int i = 0, num = 0; i < N; i++) {
            num = Integer.parseInt(stk.nextToken());
            compArr[i] = num;
            compArr[i + N] = num;
        }

        stk = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            patnArr[i] = Integer.parseInt(stk.nextToken());
        }

        makeTable(N, patnArr, table);
        KMP(N, compArr, patnArr, table);
    }
}
