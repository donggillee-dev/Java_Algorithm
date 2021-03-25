package BOJ.KMP.Platinum;

import java.io.*;
import java.util.*;
//Logic
//KMP 알고리즘을 사용하면 되는 문제이다
//다만 시계의 포지션을 KMP알고리즘 처럼 움직이면서 체크해야하기에 배열의 인덱스 즉 시계의 각에 체크를 해놓고 그 각 정보를 가지고
//KMP처럼 이동하면서 원본 시계의 모양과 비교 => 다만 시계의 특성상 순회하는 모양이기에 끝 각에서 0으로 넘어가는 것을 처리해줘야함
// => 이를 입력으로 주어지는 각정보에 360000만큼 더해줘서 처리해줌

public class boj_10266 {
    private static void makeTable (int N, boolean[] patnArr, int[] table) {
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
    private static void KMP(int length, boolean[] oriArr, boolean[] patArr, int[] table) {
        makeTable(length, patArr, table);

        int j = 0;
        for(int i = 0; i < length * 2; i++) {
            while(j > 0 && oriArr[i] != patArr[j]) {
                j = table[j - 1];
            }

            if(oriArr[i] == patArr[j]) {
                if(j == length - 1) {
                    System.out.print("possible");
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
        StringTokenizer stk;
        int N = Integer.parseInt(br.readLine());
        int length = 360000;
        boolean[] ori = new boolean[length * 2];
        boolean[] pat = new boolean[length];
        int[] table = new int[length];

        stk = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(stk.nextToken());
            ori[num] = true;
            ori[num + length] = true;
        }

        stk = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            pat[Integer.parseInt(stk.nextToken())] = true;
        }

        KMP(length, ori, pat, table);
    }
}
