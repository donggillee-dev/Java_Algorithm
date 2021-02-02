package SWEA.D2;

import java.io.*;

public class SnailNumber {
    private static int[] dir_x = {0, 1, 0, -1};
    private static int[] dir_y = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int TestCase = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < TestCase; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N][N];
            int x = 0, y = -1, dir = 0, n = 1;

            while(n <= N*N) {
                int nx = x + dir_x[dir];
                int ny = y + dir_y[dir];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N || arr[nx][ny] != 0) {
                    dir++;
                    dir %= 4;
                    continue;
                }
                arr[nx][ny] = n;
                x = nx;
                y = ny;
                n++;
            }
            sb.append("#" + (tc + 1) + " ").append("\n");
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    sb.append(arr[i][j]).append(" ");
                }
                sb.delete(sb.length() - 1, sb.length());
                sb.append("\n");
            }
        }

        bw.write(String.valueOf(sb));
        bw.close();
        br.close();
    }
}
