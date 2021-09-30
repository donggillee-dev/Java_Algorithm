package BOJ.BFS.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

//Logic
//true, false 처리때문에 에러를 겪은 문제
//좀 더 확실히 풀도록 하자

public class boj_2667 {
    private static int tot = 0;
    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, -1, 0, 1};
    private static PriorityQueue<Integer> bfs(int n, boolean[][] map) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(map[i][j]) {
                    int cnt = 0;
                    Queue<int[]> q = new LinkedList<>();

                    tot++;
                    q.add(new int[]{i, j});
                    map[i][j] = false;

                    while(!q.isEmpty()) {
                        int[] elem = q.poll();
                        int row = elem[0], col = elem[1];

                        cnt++;

                        for(int dir = 0; dir < 4; dir++) {
                            int nr = row + dr[dir];
                            int nc = col + dc[dir];

                            if(nr < 0 || nc < 0 || nr >= n || nc >= n || !map[nr][nc]) continue;

                            if(map[nr][nc]) {

                                map[nr][nc] = false;
                                q.add(new int[]{nr, nc});
                            }
                        }
                    }
                    pq.add(cnt);
                }
            }
        }

        return pq;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[n][n];

        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            for(int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j) == '1';
            }
        }

        PriorityQueue<Integer> pq = bfs(n, map);

        sb.append(tot).append("\n");

        while(!pq.isEmpty()) {
            sb.append(pq.poll()).append("\n");
        }

        System.out.print(sb);
    }
}
