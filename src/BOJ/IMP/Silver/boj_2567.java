package BOJ.IMP.Silver;

import java.io.*;
import java.util.StringTokenizer;

public class boj_2567 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        boolean[][] map = new boolean[101][101];
        int answer = 0;
        int[] dir_x = {-1, 0, 1, 0};
        int[] dir_y = {0, -1, 0, 1};
        int N = Integer.parseInt(br.readLine());
        StringTokenizer stk;
        for(int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            int col = Integer.parseInt(stk.nextToken());
            int row = Integer.parseInt(stk.nextToken());

            for(int r = row; r < row + 10; r++) {
                for(int c = col; c < col + 10; c++) {
                    map[r][c] = true;
                }
            }
        }

        for(int i = 0; i <= 100; i++) {
            for(int j = 0; j <= 100; j++) {
                if(map[i][j]) {
                    for(int dir = 0; dir < 4; dir++) {
                        int nx = i + dir_x[dir];
                        int ny = j + dir_y[dir];

                        if(nx >= 1 && ny >= 1 && nx <= 100 && ny <= 100 && !map[nx][ny]) {
                            answer++;
                        } else if(nx < 1 || ny < 1 || nx > 100 || ny > 100) {
                            answer++;
                        }
                    }
                }
            }
        }
        bw.write(answer+"");
        bw.flush();
    }
}
