package BOJ.BFS.Silver;

import java.io.*;
import java.util.*;
//Logic
//BFS로 풀면 되는 문제
//실수 자제하자...
//풀이 시간 : 30분
public class boj_2178 {
    private static boolean isRange(int x, int y, int n, int m) {
        if(x < 0 || y < 0 || x >= n || y >= m) return false;
        return true;
    }
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken()), m = Integer.parseInt(stk.nextToken());
        int ans = 987654321;

        int[] dir_x = {-1, 0, 1, 0};
        int[] dir_y = {0, -1, 0, 1};
        boolean[][] map = new boolean[n][m];
        boolean[][] visited = new boolean[n][m];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            for(int j = 0; j < m; j++) {
                map[i][j] = (str.charAt(j) == '1');
            }
        }

        pq.add(new int[]{0, 0, 1});
        visited[0][0] = true;

        while(!pq.isEmpty()) {
            int[] elem = pq.poll();

            if(elem[0] == n - 1 && elem[1] == m - 1) {
                if(ans > elem[2]) ans = elem[2];
                break;
            }

            for(int i = 0; i < 4; i++) {
                int nx = elem[0] + dir_x[i];
                int ny = elem[1] + dir_y[i];

                if(!isRange(nx, ny, n, m)) continue;
                if(!map[nx][ny] || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                pq.add(new int[]{nx, ny, elem[2] + 1});
            }
        }
        System.out.println(ans);
    }
}
