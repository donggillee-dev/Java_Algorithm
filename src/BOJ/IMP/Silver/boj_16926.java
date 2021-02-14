package BOJ.IMP.Silver;

import java.io.*;
import java.util.StringTokenizer;

public class boj_16926 {
    static int N, M, R;
    static int min;
    static int[][] arr;
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        R = Integer.parseInt(stk.nextToken());
        arr = new int[N][M];
        min = Math.min(N, M) / 2;

        for(int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        rotate();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.setLength(sb.length() - 1);
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
    private static void rotate() {
        for(int r = 0; r < R; r++) {
            for(int depth = 0; depth < min; depth++) {
                //모서리를 위한 tmp, row, col 변수
                int tmp = 0;
                int left = depth;
                int right = M - 1 - depth;
                int top = depth;
                int bottom = N - depth - 1;
                //윗라인
                tmp = arr[top][left];
                for(int i = left; i < right; i++) {
                    arr[top][i] = arr[top][i + 1];
                }

                for(int i = top; i < bottom; i++) {
                    arr[i][right] = arr[i + 1][right];
                }

                for(int i = right; i > left; i--) {
                    arr[bottom][i] = arr[bottom][i - 1];
                }

                for(int i = bottom; i > top; i--) {
                    arr[i][left] = arr[i - 1][left];
                }
                arr[depth + 1][depth] = tmp;
            }
        }
    }
}
