package Samsung_Algo;
import java.io.*;
import java.util.*;

public class algo_14890 {
    static int N, L, ans = 0;
    static int[][] Map;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        L = Integer.parseInt(stk.nextToken());
        Map = new int[N][N];
        for(int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                Map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        for(int i = 0; i < N; i++) {//행에 대해서
            int UpStair = 0;//올라가는 경사로 설치?
            int DownStair = 0;//내려가는 경사로 설치
            int start_idx = 0;//기준점 되는 idx
            for(int j = 1; j < N; j++) {
                if(Map[i][start_idx] != Map[i][j]) {
                    int diff = Map[i][start_idx] - Map[i][j];

                    if(diff == -1) {
                        if(DownStair != 0) {

                        }
                        DownStair++;
                    } else if(diff == 1) {

                    } else break;

                    start_idx = j;
                }
            }
        }

        for(int i = 0; i < N; i++) {//열에 대해서
            for(int j = 0; j < N - 1; j++) {//위 -> 아래

            }

            for(int j = N - 1; j > 0; j--) {//아래 -> 위

            }
        }
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}