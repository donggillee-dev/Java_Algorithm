package BOJ.BFS.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//Logic
//3차원 배열을 이용한 BFS문제
//풀이 시간 : 18분
public class boj_7569 {
    private static boolean isRange(int nh, int nx, int ny, int h, int x, int y) {
        if(nh < 0 || nx < 0 || ny < 0 || nh >= h || nx >= x || ny >= y) return false;
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(stk.nextToken()), n = Integer.parseInt(stk.nextToken()), h = Integer.parseInt(stk.nextToken());
        int cnt = 0;
        int[] dir_x = {-1, 0, 1, 0, 0, 0};
        int[] dir_y = {0, -1, 0, 1, 0, 0};
        int[] dir_h = {0, 0, 0, 0, -1, 1};

        int[][][] map = new int[h][n][m];
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                stk = new StringTokenizer(br.readLine());
                for (int k = 0; k < m; k++) {
                    map[i][j][k] = Integer.parseInt(stk.nextToken());
                    if (map[i][j][k] == 0) cnt++;
                    else if(map[i][j][k] == 1) {
                        q.add(new int[]{i, j, k, 0});
                    }
                }
            }
        }

        int ans = 0;

        while(!q.isEmpty()) {
            int[] elem = q.poll();
            ans = elem[3];

            for(int i = 0; i < 6; i++) {
                int nh = elem[0] + dir_h[i];
                int nx = elem[1] + dir_x[i];
                int ny = elem[2] + dir_y[i];

                if(!isRange(nh, nx, ny, h, n, m)) continue;

                if(map[nh][nx][ny] == 0) {
                    map[nh][nx][ny] = 1;
                    q.add(new int[]{nh, nx, ny, elem[3] + 1});
                    cnt--;
                }
            }
        }

        if(cnt == 0) {
            System.out.println(ans);
        } else {
            System.out.println(-1);
        }
    }
}
