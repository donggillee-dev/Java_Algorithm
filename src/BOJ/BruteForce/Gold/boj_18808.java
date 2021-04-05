package BOJ.BruteForce.Gold;

import java.io.*;
import java.util.*;

public class boj_18808 {
    private static int[][] Sticker;
    private static int[][] NoteBook;
    private static boolean attach(int N, int M, int sx, int sy, int length) {
        int[][] copyNote = new int[N][M];

        int attached = 0;
        for(int i = 0; i < N; i++) {
            System.arraycopy(NoteBook[i], 0, copyNote[i], 0, M);
        }

        loop:for(int i = 0; i < Sticker.length; i++) {
            for(int j = 0; j < Sticker[0].length; j++) {
                if((sx + i) >= N || (sy + j) >= M || (sx + i) < 0 || (sy + j) < 0)
                    break loop;
                if(Sticker[i][j] == 1 && copyNote[sx + i][sy + j] == 0) {
                    copyNote[sx + i][sy + j] = 1;
                    attached++;
                } else if(Sticker[i][j] == 1 && copyNote[sx + i][sy + j] == 1) {
                    attached = 0;
                    break loop;
                } else;

            }
        }
        if(attached == length) {
            for(int i = 0; i < N; i++) {
                System.arraycopy(copyNote[i], 0, NoteBook[i], 0, M);
            }
            return true;
        }
        return false;
    }
    private static void rotate() {
        int[][] tmp = new int[Sticker[0].length][Sticker.length];
        for(int i = 0; i < Sticker[0].length; i++) {
            for(int j = 0; j < Sticker.length; j++) {
                tmp[i][j] = Sticker[Sticker.length - j - 1][i];
            }
        }
        Sticker = tmp;
        return;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int StickerNum = Integer.parseInt(stk.nextToken());
        int ans = 0;

        NoteBook = new int[N][M];

        for(int i = 0; i < StickerNum; i++) {//스티커 개수만큼 for문
            stk = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(stk.nextToken());//스티커의 행
            int sy = Integer.parseInt(stk.nextToken());//스티커의 열
            int length = 0;//스티커의 총 길이가 몇인지
            Sticker = new int[sx][sy];//스티커 배열 생성

            for(int j = 0; j < sx; j++) {//각 스티커에 대해서 입력을 받음
                stk = new StringTokenizer(br.readLine());
                for(int k = 0; k < sy; k++) {
                    Sticker[j][k] = Integer.parseInt(stk.nextToken());
                    if(Sticker[j][k] == 1) length++;
                }
            }
            loop1:for(int cnt = 0; cnt < 4; cnt++) {
                for(int nx = 0; nx < N; nx++) {
                    for(int ny = 0; ny < M; ny++) {
                        if(attach(N, M, nx, ny, length)) {
                            ans += length;
                            break loop1;
                        }
                    }
                }
                rotate();
            }
        }
        System.out.println(ans);
    }
}
