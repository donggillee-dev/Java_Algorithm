package BOJ.BruteForce.Gold;

import java.io.*;
import java.util.*;

public class boj_18808 {
    static int[][] Sticker;
    static int[][] NoteBook;
    static int N, M, StickerNum;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        StickerNum = Integer.parseInt(stk.nextToken());

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
                        boolean ret = false;
                        ret = attach(nx, ny, length);
                        if(ret) {
                            ans += length;
                            break loop1;
                        }
                    }
                }
                rotate();
            }
        }
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    public static boolean attach(int nx, int ny, int length) {
        int[][] copyNote = new int[N][M];

        boolean flag = false;

        int attached = 0;
        for(int i = 0; i < N; i++) {
            System.arraycopy(NoteBook[i], 0, copyNote[i], 0, M);
        }
        loop2:for(int i = 0; i < Sticker.length; i++) {
            for(int j = 0; j < Sticker[0].length; j++) {
                if((nx + i) >= N || (ny + j) >= M || (nx + i) < 0 || (ny + j) < 0)
                    break loop2;
                if(Sticker[i][j] == 1 && copyNote[nx + i][ny + j] == 0) {
                    copyNote[nx + i][ny + j] = 1;
                    attached++;
                } else if(Sticker[i][j] == 1 && copyNote[nx + i][ny + j] == 1) {
                    attached = 0;
                    break loop2;
                } else;

            }
        }
        if(attached == length) {
            flag = true;
            for(int i = 0; i < N; i++) {
                System.arraycopy(copyNote[i], 0, NoteBook[i], 0, M);
            }
        }
        return flag;
    }
    public static void rotate() {
        int[][] tmp = new int[Sticker[0].length][Sticker.length];
        for(int i = 0; i < Sticker[0].length; i++) {
            for(int j = 0; j < Sticker.length; j++) {
                tmp[i][j] = Sticker[Sticker.length - j - 1][i];
            }
        }
        Sticker = tmp;
        return;
    }
}
