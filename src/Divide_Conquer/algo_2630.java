package Divide_Conquer;
import java.io.*;
import java.util.*;

public class algo_2630 {
    static int N;
    static int[][] arr;
    static int white = 0, blue = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        Merge(0, 0, N);
        sb.append(white).append("\n").append(blue).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }

    private static void Merge(int start_x ,int start_y, int width) {
        int ret = -1;
        if((ret = Confirm(start_x, start_y, width)) >= 0) {
            if(ret == 1) blue++;
            else white++;
        } else {
            int mid = width / 2;
            Merge(start_x, start_y, mid);
            Merge(start_x + mid, start_y, mid);
            Merge(start_x, start_y + mid, mid);
            Merge(start_x + mid, start_y + mid, mid);
        }
    }

    private static int Confirm(int start_x, int start_y, int width) {
        int end_x = start_x + width;
        int end_y = start_y + width;
        int tmp = arr[start_x][start_y];
        boolean flag = true;
        for(int i = start_x; i < end_x; i++) {
            if(!flag) break;
            for(int j = start_y; j < end_y; j++) {
                if(!flag) break;
                if(tmp != arr[i][j]) flag = false;
            }
        }
        if(flag) return tmp;
        else return -1;
    }
}
