package Kakao_Internship_CT;

import java.io.*;
import java.util.*;
public class Problem4 {
    static int ans = Integer.MAX_VALUE;
    static int[] dir_x = {0, 1, -1, 0};
    static int[] dir_y = {1, 0, 0, -1};
    static int[][] DP = new int[25][25];
//    static boolean[][] Visited = new boolean[25][25];
    static Queue<Q_Info> Q = new LinkedList<>();
    static int[][] board = {{0, 0, 1, 0}, {0, 0, 0, 0}, {0, 1, 0, 1}, {1, 0, 0, 0}};
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        solution(board);
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static int solution(int[][] board) {
        N = board.length;
        for(int i = 0; i < N; i++) {
            Arrays.fill(DP[i], Integer.MAX_VALUE);
        }
        DP[0][0] = 100;
        for(int i = 0; i < 4; i++) {
            int nx = dir_x[i];
            int ny = dir_y[i];
            if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            if(board[nx][ny] != 1) {
                Q.offer(new Q_Info(nx, ny, i, 100));
            }
        }
        while(!Q.isEmpty()) {
            Q_Info tmp = Q.poll();
            if(tmp.nx == N - 1 && tmp.ny == N - 1) {
                if(DP[tmp.nx][tmp.ny] > tmp.cost) {
                    DP[tmp.nx][tmp.ny] = tmp.cost;
                }
                continue;
            }
            if(DP[tmp.nx][tmp.ny] >= tmp.cost) {
                DP[tmp.nx][tmp.ny] = tmp.cost;
            }
            for(int i = 0; i < 4; i++) {
                int nx = tmp.nx + dir_x[i];
                int ny = tmp.ny + dir_y[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(board[nx][ny] != 1) {
                    if(tmp.dir == i && DP[nx][ny] >= tmp.cost + 100) {
                        Q.offer(new Q_Info(nx, ny, i, tmp.cost + 100));
                        DP[nx][ny] = tmp.cost + 100;
                    } else if(tmp.dir != i && DP[nx][ny] >= tmp.cost + 600){
                        Q.offer(new Q_Info(nx, ny, i, tmp.cost + 600));
                        DP[nx][ny] = tmp.cost + 600;
                    }
                }
            }
        }
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                System.out.print(DP[i][j] + " ");
            }
            System.out.println();
        }
        return DP[N - 1][N - 1];
    }
}
class Q_Info {
    int nx;
    int ny;
    int dir;
    int cost;

    public Q_Info(int x, int y, int d, int c) {
        this.nx = x;
        this.ny = y;
        this.dir = d;
        this.cost = c;
    }
}
