package BOJ.BFS.Silver;

import java.io.*;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_2583 {
    private static int[] dirX = {-1, 1, 0, 0};
    private static int[] dirY = {0, 0, -1, 1};
    private static boolean isRange(int row, int col, int M, int N) {
        if(row < 0 || col < 0 || row >= M || col >= N) return false;
        return true;
    }
    private static PriorityQueue<Integer> bfs(int M, int N, boolean[][] map) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Queue<int[]> q = new LinkedList<>();

        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                if(!map[i][j]) {
                    int size = 1;
                    q.add(new int[]{i, j});
                    map[i][j] = true;

                    while(!q.isEmpty()) {
                        int[] pos = q.poll();

                        for(int idx = 0 ; idx < 4; idx++) {
                            int nx = pos[0] + dirX[idx];
                            int ny = pos[1] + dirY[idx];

                            if(!isRange(nx, ny, M, N)) continue;

                            if(!map[nx][ny]) {
                                q.add(new int[]{nx, ny});

                                map[nx][ny] = true;
                                size++;
                            }
                        }
                    }

                    pq.add(size);
                }
            }
        }

        return pq;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer stk = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(stk.nextToken());
        int N = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());
        boolean[][] map = new boolean[M][N];

        while(K --> 0) {
            stk = new StringTokenizer(br.readLine());
            int lc = Integer.parseInt(stk.nextToken());
            int lr = M - Integer.parseInt(stk.nextToken());
            int rc = Integer.parseInt(stk.nextToken());
            int rr = M - Integer.parseInt(stk.nextToken());

            for(int row = rr; row < lr; row++) {
                for(int col = lc; col < rc; col++) {
                    map[row][col] = true;
                }
            }
        }


        PriorityQueue<Integer> pq = bfs(M, N, map);

        while(!pq.isEmpty()) {
            sb.append(pq.poll()).append(" ");
        }

        System.out.println(sb);
    }
}
