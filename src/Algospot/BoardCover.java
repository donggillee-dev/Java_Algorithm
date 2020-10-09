package Algospot;

import java.io.*;
import java.util.*;

public class BoardCover {
    static int[][][] types = {
            {{0, 0}, {1, 0}, {1, 1}},
            {{0, 0}, {0, 1}, {1, 0}},
            {{0, 0}, {0, 1}, {1, 1}},
            {{0, 0}, {1, 0}, {1, -1}}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();


        StringTokenizer stk;
        String row;
        int T = 0, H = 0, W = 0;
        int[][] board;

        T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {
            stk = new StringTokenizer(br.readLine());
            H = Integer.parseInt(stk.nextToken());
            W = Integer.parseInt(stk.nextToken());
            board = new int[H][W];

            for(int h = 0; h < H; h++) {
                row = br.readLine();
                for(int w = 0; w < W; w++) {
                    board[h][w] = ((row.charAt(w) == '.') ? 0 : 1);
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
    public static int cover(int[][] board) {
        int x = -1, y = -1;

        loop1:for(int pos_x = 0; pos_x < board.length; pos_x++) {
            for(int pos_y = 0; pos_y < board[pos_x].length; pos_y++) {
                if(board[pos_x][pos_y] == 0) {
                    x = pos_x;
                    y = pos_y;
                    break loop1;
                }
            }
        }

        if(y == -1 && x == -1) return 1;

        int ret = 0;
        for(int type = 0; type < types.length; type++) {
            if(set(board, x, y, type, 1))   //Can place type(x) tile
               ret += cover(board);
            set(board, x, y, type, -1);  //remove placed tile
        }
        return ret;
    }
    public static boolean set(int[][] board, int x, int y, int type, int delta) {
        boolean possible = true;

        for(int i = 0; i < 3; i++) {
            int nx = x + types[type][i][0];
            int ny = y + types[type][i][1];

            if(nx < 0 || ny < 0 || nx >= board.length || ny >= board[0].length)
                possible = false;

            else if((board[nx][ny] += delta) > 1)
                possible = false;
        }
        return possible;
    }
}
