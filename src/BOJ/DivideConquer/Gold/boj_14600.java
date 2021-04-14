package BOJ.DivideConquer.Gold;

import java.io.*;
import java.util.*;

//Logic
//L-트로미노 문제
//분할 정복으로 해결할 수 있는 문제이다
//4분면으로 나눠서 시작점으로부터 해당 분면의 위치에 무언가가 놓아져있다면 그 분면에는 타일을 한개 덜 놔야하는 것이 요점
//그렇게 시작점을 중점으로해서 가운데부터 채워 나가면되는 문제이다.

//문제 좀 잘 읽자... 여러 답 중에 하나만 나오면 되는 문제이다.

public class boj_14600 {
    private static int num = 0;
    private static int[][] booth;
    private static boolean isPossible(int width, int r, int c) {
        for(int i = r; i < r + width; i++) {
            for(int j = c; j < c + width; j++) {
                if(booth[i][j] != 0) return false;
            }
        }
        return true;
    }

    private static void solution(int size, int r, int c) {
        num++;

        int divSize = size / 2;
        if(isPossible(divSize, r + divSize, c))
            booth[r + divSize][c + divSize - 1] = num;
        if(isPossible(divSize, r + divSize, c + divSize))
            booth[r + divSize][c + divSize] = num;
        if(isPossible(divSize, r, c))
            booth[r + divSize - 1][c + divSize - 1] = num;
        if(isPossible(divSize, r, c + divSize))
            booth[r + divSize - 1][c + divSize] = num;

        if(size == 2) return;

        solution(divSize, r + divSize, c);
        solution(divSize, r + divSize, c + divSize);
        solution(divSize, r, c);
        solution(divSize, r, c + divSize);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int size = (int)Math.pow(2, N);
        StringTokenizer stk = new StringTokenizer(br.readLine());

        booth = new int[size][size];
        int col = Integer.parseInt(stk.nextToken()), row = size - Integer.parseInt(stk.nextToken());

        booth[row][col - 1] = -1;

        solution(size, 0, 0);

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                sb.append(booth[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
