package BOJ.BruteForce.Gold;
import java.io.*;
import java.util.*;

public class boj_15684 {
    static int N, M, H, Width = 3;
    static int[][] Ladder;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        H = Integer.parseInt(stk.nextToken());
        Ladder = new int[H + 1][(N + 2) * Width];
        if(M == 0) sb.append(0).append("\n");
        else {
            for(int i = 0; i < M; i++) {
                stk = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(stk.nextToken());
                int y1 = Integer.parseInt(stk.nextToken()) * Width;
                int y2 = y1 + 1;
                Ladder[x][y1] = 1;
                Ladder[x][y2] = 1;
            }
            Play(0);
            Build(1, 1, 0);
            if(ans == Integer.MAX_VALUE || ans > 3) sb.append(-1).append("\n");
            else sb.append(ans).append("\n");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void Build(int x, int y, int BuiltCnt) {
        if(BuiltCnt >= 1) {
            Play(BuiltCnt);
        }

        if(BuiltCnt <= 2) {
            for(int i = x; i <= H; i++) {
                for(int j = y; j < N; j++) {
                    if(CurrentPossible(i, j) && RightPossible(i, j)) {
                        int idx = j * 3;
                        Ladder[i][idx] = 1;
                        Ladder[i][idx + 1] = 1;
                        Build(i, j, BuiltCnt + 1);
                        Ladder[i][idx] = 0;
                        Ladder[i][idx + 1] = 0;
                    }
                }
                y = 1;
            }
        }
    }
    private static void Play(int BuiltCnt) {
        int cnt = 0;
        for(int i = 1; i <= N; i++) {
            int x = i * Width - 1;
            int y = 0;
            while(y <= H) {
                if(Ladder[y][x - 1] == 1) {//내려오다가 왼쪽에 사다리가 놓아져있으면
                    x -= 3;
                } else if(Ladder[y][x + 1] == 1) {//내려오다가 오른쪽에 사다리가 놓아져있으면
                    x += 3;
                } else;
                y += 1;
            }
            if(((x / Width) + 1) == i) cnt++;
            else break;
        }
        if(cnt == N && BuiltCnt < ans)
            ans = BuiltCnt;
    }
    private static boolean RightPossible(int x, int y) {
        boolean ret = true;
        int y1 = (y + 1) * 3;

        if(Ladder[x][y1] == 1) ret = false;
        return ret;
    }
    private static boolean CurrentPossible(int x, int y) {
        boolean ret = true;
        int y1 = y * 3;
        int y2 = y1 - 1;
        int y3 = y2 - 1;

        if(Ladder[x][y1] == 1 || Ladder[x][y3] == 1) ret = false;

        return ret;
    }
}

