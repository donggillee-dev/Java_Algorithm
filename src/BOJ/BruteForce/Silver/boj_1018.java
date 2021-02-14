package BOJ.BruteForce.Silver;

import java.io.*;
import java.util.*;

public class boj_1018 {
    public static void main(String[] args) throws IOException {
        Brute brute = new Brute();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int Paint = Integer.MAX_VALUE;
        char[][] ChessBoard = new char[N][M];

        for(int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for(int j = 0; j < M; j++) {
                ChessBoard[i][j] = tmp.charAt(j);
            }
        }

        for(int i = 0; i <= N - 8; i++) {
            for(int j = 0; j <= M - 8; j++) {
                char[][] ChessBoardTmp = brute.MakeChessBoard(ChessBoard, i, j);
                int res = brute.CntRePaint(ChessBoardTmp);
                if(res < Paint) Paint = res;
            }
        }

        sb.append(Paint).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static class Brute {
        int CntRePaint(char[][] ChessBoardTmp) {
            int MIN = Integer.MAX_VALUE;
            int cnt = 0;
            char[][] tmp1 = new char[8][8];
            char[][] tmp2 = new char[8][8];
            char[][] tmp3 = new char[8][8];
            char[][] tmp4 = new char[8][8];
            for(int i = 0; i < 8; i++) {
                tmp1[i] = ChessBoardTmp[i].clone();
                tmp2[i] = ChessBoardTmp[i].clone();
                tmp3[i] = ChessBoardTmp[i].clone();
                tmp4[i] = ChessBoardTmp[i].clone();
            }

            for(int i = 0; i < 7; i++) {
                for(int j = 0; j < 7; j++) {
                    if(tmp1[i][j] == tmp1[i][j + 1]){
                        tmp1[i][j + 1] = tmp1[i][j] == 'W' ? 'B' : 'W';
                        cnt++;
                    }
                    if(tmp1[i][j] == tmp1[i + 1][j]){
                        tmp1[i + 1][j] = tmp1[i][j] == 'W' ? 'B' : 'W';
                        cnt++;
                    }
                    if(tmp1[i][j] != tmp1[i + 1][j + 1]) {
                        tmp1[i + 1][j + 1] = tmp1[i][j] == 'W' ? 'W' : 'B';
                        cnt++;
                    }
                }
            }

            if(cnt < MIN) MIN = cnt;
            cnt = 0;

            for(int i = 0; i < 7; i++) {
                for(int j = 7; j > 0; j--) {
                    if(tmp2[i][j] == tmp2[i][j - 1]){
                        tmp2[i][j - 1] = tmp2[i][j] == 'W' ? 'B' : 'W';
                        cnt++;
                    }
                    if(tmp2[i][j] == tmp2[i + 1][j]){
                        tmp2[i + 1][j] = tmp2[i][j] == 'W' ? 'B' : 'W';
                        cnt++;
                    }
                    if(tmp2[i][j] != tmp2[i + 1][j - 1]) {
                        tmp2[i + 1][j - 1] = tmp2[i][j] == 'W' ? 'W' : 'B';
                        cnt++;
                    }
                }
            }

            if(cnt < MIN) MIN = cnt;
            cnt = 0;

            for(int i = 7; i > 0; i--) {
                for(int j = 0; j < 7; j++) {
                    if(tmp3[i][j] == tmp3[i][j + 1]){
                        tmp3[i][j + 1] = tmp3[i][j] == 'W' ? 'B' : 'W';
                        cnt++;
                    }
                    if(tmp3[i][j] == tmp3[i - 1][j]){
                        tmp3[i - 1][j] = tmp3[i][j] == 'W' ? 'B' : 'W';
                        cnt++;
                    }
                    if(tmp3[i][j] != tmp3[i - 1][j + 1]) {
                        tmp3[i - 1][j + 1] = tmp3[i][j] == 'W' ? 'W' : 'B';
                        cnt++;
                    }
                }
            }

            if(cnt < MIN) MIN = cnt;
            cnt = 0;

            for(int i = 7; i > 0; i--) {
                for(int j = 7; j > 0; j--) {
                    if(tmp4[i][j] == tmp4[i][j - 1]){
                        tmp4[i][j - 1] = tmp4[i][j] == 'W' ? 'B' : 'W';
                        cnt++;
                    }
                    if(tmp4[i][j] == tmp4[i - 1][j]){
                        tmp4[i - 1][j] = tmp4[i][j] == 'W' ? 'B' : 'W';
                        cnt++;
                    }
                    if(tmp4[i][j] != tmp4[i - 1][j - 1]) {
                        tmp4[i - 1][j - 1] = tmp4[i][j] == 'W' ? 'W' : 'B';
                        cnt++;
                    }
                }
            }

            if(cnt < MIN) MIN = cnt;
            return MIN;
        }

        char[][] MakeChessBoard(char[][] ChessBoard, int x, int y) {
            char[][] tmp = new char[8][8];

            for(int i = 0; i < 8; i++) {
                for(int j = 0; j < 8; j++){
                    tmp[i][j] = ChessBoard[i + x][j + y];
                }
            }

            return tmp;
        }
    }
}