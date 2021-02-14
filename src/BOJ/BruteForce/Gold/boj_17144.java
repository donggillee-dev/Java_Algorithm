package BOJ.BruteForce.Gold;
import java.io.*;
import java.util.*;

public class boj_17144 {
    static int R, C, T, ans = 0;
    static int UpperX, UpperY, DownX, DownY;
    static int[][] Arr;
    static int[] dir_x = {0, 0, -1, 1};
    static int[] dir_y = {-1, 1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        boolean isUpper = false;
        int time = 0;
        R = Integer.parseInt(stk.nextToken());
        C = Integer.parseInt(stk.nextToken());
        T = Integer.parseInt(stk.nextToken());
        Arr = new int[R + 1][C + 1];
        for(int i = 1; i <= R; i++) {
            int val;
            stk = new StringTokenizer(br.readLine());
            for(int j = 1; j <= C; j++) {
                val = Integer.parseInt(stk.nextToken());
                if(val == -1 && !isUpper) {
                    isUpper = true;
                    UpperX = i;
                    UpperY = j;
                } else if(val == -1 && isUpper) {
                    DownX = i;
                    DownY = j;
                } else;
                Arr[i][j] = val;
            }
        }
        while(time < T) {
            Spread();
            Move();
            time++;
        }
        solve();
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }

    private static void Spread() {
        int[][] sum = new int[R + 1][C + 1];
        for(int i = 1; i <= R; i++) {
            for(int j = 1; j <= C; j++) {
                int cnt = 0;
                int val = 0;
                if(Arr[i][j] > 4) {
                    int nx;
                    int ny;
                    val = Arr[i][j] / 5;
                    for(int k = 0; k < 4; k++) {
                        nx = i + dir_x[k];
                        ny = j + dir_y[k];
                        if(nx < 1 || ny < 1 || nx > R || ny > C || (nx == UpperX && ny == UpperY) || (nx == DownX && ny == DownY)) continue;
                        sum[nx][ny] += val;
                        cnt++;
                    }
                    Arr[i][j] -= (val * cnt);
                }
            }
        }
        for(int i = 1; i <= R; i++) {
            for(int j = 1; j <= C; j++) {
                if(sum[i][j] != 0) {
                    Arr[i][j] += sum[i][j];
                }
            }
        }
    }
    private static void Move() {
        Arr[UpperX - 1][UpperY] = 0;
        for(int i = UpperX - 1; i >= 2; i--) {
            Arr[i][1] = Arr[i - 1][1];
        }
        for(int i = 1; i <= C - 1; i++) {
            Arr[1][i] = Arr[1][i + 1];
        }
        for(int i = 1; i <= UpperX - 1; i++) {
            Arr[i][C] = Arr[i + 1][C];
        }
        for(int i = C; i > UpperY + 1; i--) {
            Arr[UpperX][i] = Arr[UpperX][i - 1];
        }
        Arr[UpperX][UpperY + 1] = 0;

        Arr[DownX + 1][DownY] = 0;
        for(int i = DownX + 1; i <= R - 1; i++) {
            Arr[i][1] = Arr[i + 1][1];
        }
        for(int i = 1; i <= C - 1; i++) {
            Arr[R][i] = Arr[R][i + 1];
        }
        for(int i = R; i >= DownX + 1; i--) {
            Arr[i][C] = Arr[i - 1][C];
        }
        for(int i = C; i > DownY + 1; i--) {
            Arr[DownX][i] = Arr[DownX][i - 1];
        }
        Arr[DownX][DownY + 1] = 0;

    }
    private static void solve() {
        for(int i = 1; i <= R; i++) {
            for(int j = 1; j <= C; j++) {
                if(Arr[i][j] > 0) {
                    ans += Arr[i][j];
                }
            }
        }
    }
}
