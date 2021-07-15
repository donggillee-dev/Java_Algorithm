package BOJ.DivideAndConquer.Gold;

import java.io.*;
import java.util.*;

//Logic
//L-트로미노 문제
//분할 정복으로 해결할 수 있는 문제이다
//4분면으로 나눠서 시작점으로부터 해당 분면의 위치에 무언가가 놓아져있다면 그 분면에는 타일을 한개 덜 놔야하는 것이 요점
//그렇게 시작점을 중점으로해서 가운데부터 채워 나가면되는 문제이다.

//문제 좀 잘 읽자... 여러 답 중에 하나만 나오면 되는 문제이다.

//다시 풀어봄 풀이 시간 : 25분

public class boj_14600 {
    private static int num;
    private static boolean isPossible(int row, int col, int size, int[][] booth) {
        for(int i = row; i < row + size; i++) {
            for(int j = col; j < col + size; j++) {
                if(booth[i][j] != 0) return false;
            }
        }

        return true;
    }
    private static void recur(int row, int col, int size, int[][] booth) {
        int divSize = size / 2;

        num++;

        if(isPossible(row, col, divSize, booth)) //왼쪽 위
            booth[row + divSize - 1][col + divSize - 1] = num;
        if(isPossible(row + divSize, col, divSize, booth)) //왼쪽 아래
            booth[row + divSize][col + divSize - 1] = num;
        if(isPossible(row, col + divSize, divSize, booth)) //오른쪽 위
            booth[row + divSize - 1][col + divSize] = num;
        if(isPossible(row + divSize, col + divSize, divSize, booth)) //오른쪽 아래
            booth[row + divSize][col + divSize] = num;

        if(divSize == 1) return;

        recur(row, col, divSize, booth);
        recur(row + divSize, col, divSize, booth);
        recur(row, col + divSize, divSize, booth);
        recur(row + divSize, col + divSize, divSize, booth);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int k = Integer.parseInt(br.readLine());
        int size = (int)Math.pow(2, k);
        int[][] booth = new int[size][size];
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(stk.nextToken());
        int y = Integer.parseInt(stk.nextToken());

        booth[size - y][x - 1] = -1;

        recur(0, 0, size, booth);

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                sb.append(booth[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
