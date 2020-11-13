package BOJ_BackTracking;

import java.io.*;
import java.util.*;

public class algo_2206 {
    static int N, K;
    static int min = Integer.MAX_VALUE;
    static int[][] visit;  //이미 방문한 정점의 정보를 담을 배열
    static int[][] map;
    static int[] nx = {0, -1, 0, 1};
    static int[] ny = {-1, 0, 1, 0};
    static Queue<Point> Q;

    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][K];
        visit = new int[N][K];

        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            Arrays.fill(visit[i], Integer.MAX_VALUE);
            for (int j = 0; j < K; j++) {
                map[i][j] = tmp.charAt(j) - '0';
            }
        }
        bfsSol(0, 0);

        if (min == Integer.MAX_VALUE) sb.append(-1).append("\n");
        else sb.append(min).append("\n");

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }

    private static void bfsSol(int x, int y) {
        Q = new LinkedList<Point>();
        Q.add(new Point(x, y, 1, 0));
        visit[x][y] = 0;

        while (!Q.isEmpty()) {
            Point p = Q.poll();

            if (p.x == N - 1 && p.y == K - 1) {
                min = p.dis;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int x1 = p.x + nx[i];
                int y1 = p.y + ny[i];

                if (x1 < 0 || y1 < 0 || x1 >= N || y1 >= K) continue;

                if (visit[x1][y1] <= p.metWall) continue;

                if (map[x1][y1] == 0) {
                    visit[x1][y1] = p.metWall;
                    Q.add(new Point(x1, y1, p.dis + 1, p.metWall));
                } else {
                    if (p.metWall == 0) {
                        visit[x1][y1] = p.metWall + 1;
                        Q.add(new Point(x1, y1, p.dis + 1, p.metWall + 1));
                    }
                }
            }
        }
    }
}

class Point {
    int x, y, dis, metWall;

    public Point(int x1, int y1, int d, int w) {
        x = x1;
        y = y1;
        dis = d;
        metWall = w;
    }
}

