package Algospot;

import java.io.*;
import java.util.*;

public class BoardCover {
    static int[][][] types = {
        {{0, 0}, {1, 0}, {0, 1}},
        {{0, 0}, {0, 1}, {1, 1}},
        {{0, 0}, {1, 0}, {1, 1}},
        {{0, 0}, {1, 0}, {1, -1}}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());
        for(int c = 0; c < C; ++c) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(stk.nextToken());
            int M = Integer.parseInt(stk.nextToken());
            int[][] board = new int[N][M];

            for(int i = 0; i < N; i++) {
                String line = br.readLine();
                for(int j = 0; j < line.length(); j++) {
                    if(line.charAt(j) == '#')
                        board[i][j] = 1;
                }
            }
            sb.append(cover(board)).append("\n");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    static boolean set(int[][] board, int x, int y, int type, int delta) {
        boolean ok = true;
        for(int i = 0; i < 3; ++i) {
            int nx = x + types[type][i][0];
            int ny = y + types[type][i][1];

            if(nx < 0 || ny < 0 || nx >= board.length || ny >= board[0].length)
                ok = false;
            else if((board[nx][ny] += delta) > 1)
                ok = false;
        }
        return ok;
    }
    static int cover(int[][] board) {
        int x = -1, y = -1;
        loop1:for(int i = 0; i < board.length; ++i) {
            loop2:for(int j = 0; j < board[i].length; ++j) {
                if(board[i][j] == 0) {
                    x = i;
                    y = j;
                    break loop1;
                }
            }
        }

        if(x == -1) return 1;
        int ret = 0;
        for(int type = 0; type < 4; ++type) {
            if(set(board, x, y, type, 1))
                ret += cover(board);
            set(board, x, y, type, -1);
        }
        return ret;
    }
}
