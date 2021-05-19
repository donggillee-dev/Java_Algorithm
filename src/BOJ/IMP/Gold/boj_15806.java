package BOJ.IMP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//Logic
//Still Sovling...

public class boj_15806 {
    private static int n, t;

    private static boolean isRange(int nx, int ny) {
        if (nx < 1 || ny < 1 || nx > n || ny > n) return false;

        return true;
    }

    private static void bfs(Queue<int[]> q, int[][] room, boolean[][] check) {
        boolean flag = false;
        int[] dir_x = {-2, -1, 2, 1, -2, 1, -1, 2};
        int[] dir_y = {-1, -2, 1, 2, 1, -2, 2, -1};

        while (!q.isEmpty()) {
            int[] elem = q.poll();
            int x = elem[0], y = elem[1], day = elem[2];

            if (day > t) {
                if (check[x][y]) {
                    System.out.println("YES");
                    flag = true;
                    break;
                }
                continue;
            }

            for (int i = 0; i < 8; i++) {
                int nx = x + dir_x[i];
                int ny = y + dir_y[i];
                int nd = day + 1;

                if (!isRange(nx, ny) || room[nx][ny] >= nd) continue;

                room[nx][ny] = nd;
                q.add(new int[]{nx, ny, nd});
            }
        }

        if (!flag)
            System.out.println("NO");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        Queue<int[]> q = new LinkedList<>();

        n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        int k = Integer.parseInt(stk.nextToken());
        t = Integer.parseInt(stk.nextToken());

        int[][] room = new int[n + 1][n + 1];
        boolean[][] check = new boolean[n + 1][n + 1];

        for(int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());

            room[x][y] = 1;
            q.add(new int[]{x, y, 1});
        }

        for(int i = 0; i < k; i++) {
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());

            check[x][y] = true;
        }

        bfs(q, room, check);
    }
}
