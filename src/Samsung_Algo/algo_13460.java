package Samsung_Algo;
import java.io.*;
import java.util.*;

public class algo_13460 {
    private static class BallInfo {
        int Rx, Ry, Bx, By;
        int cnt;
        public BallInfo(int Red_x, int Red_y, int Blue_x, int Blue_y, int c) {
            this.Rx = Red_x;
            this.Ry = Red_y;
            this.Bx = Blue_x;
            this.By = Blue_y;
            this.cnt = c;
        }
    }
    static int N, M;
    static int[] dir_x = {1, -1, 0, 0};
    static int[] dir_y = {0, 0, 1, -1};
    static char[][] Map;
    static boolean[][][][] Visit;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Red_x = 0, Red_y = 0, Blue_x = 0, Blue_y = 0;
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        Map = new char[N][M];
        Visit = new boolean[10][10][10][10];

        for(int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for(int j = 0; j < M; j++) {
                Map[i][j] = tmp.charAt(j);
                if(Map[i][j] == 'R') {
                    Red_x = i;
                    Red_y = j;
                }
                if(Map[i][j] == 'B') {
                    Blue_x = i;
                    Blue_y = j;
                }
            }
        }
        BallInfo info = new BallInfo(Red_x, Red_y, Blue_x, Blue_y, 0);
        Start(info);

        br.close();
        return;
    }
    private static void Start(BallInfo info) {
        Queue<BallInfo> q = new LinkedList<>();
        q.offer(info);
        while(!q.isEmpty()) {
            BallInfo tmp = q.poll();
            Visit[tmp.Rx][tmp.Ry][tmp.Bx][tmp.By] = true;

            if(tmp.cnt >= 10) {
                System.out.println("-1");
                return;
            }
            for(int dir = 0; dir < 4; dir++) {
                int Bx = tmp.Bx;
                int By = tmp.By;
                int Rx = tmp.Rx;
                int Ry = tmp.Ry;

                while(Map[Bx + dir_x[dir]][By + dir_y[dir]] != '#') {
                    Bx += dir_x[dir];
                    By += dir_y[dir];
                    if(Map[Bx][By] == 'O') {
                        break;
                    }

                }

                while(Map[Rx + dir_x[dir]][Ry + dir_y[dir]] != '#') {
                    Rx += dir_x[dir];
                    Ry += dir_y[dir];
                    if(Map[Rx][Ry] == 'O') {
                        break;
                    }
                }
                if(Map[Bx][By] == 'O') continue;

                if(Map[Rx][Ry] == 'O') {
                    System.out.println(tmp.cnt + 1);
                    return;
                }

                if(Rx == Bx && Ry == By) {
                    if(dir == 0) {//남쪽
                        if(tmp.Rx < tmp.Bx)
                            Rx -= 1;
                        else
                            Bx -= 1;
                    } else if(dir == 1) {//북쪽
                        if(tmp.Rx < tmp.Bx)
                            Bx += 1;
                        else
                            Rx += 1;
                    } else if(dir == 2) {//동쪽
                        if(tmp.Ry < tmp.By)
                            Ry -= 1;
                        else
                            By -= 1;
                    } else {//서쪽
                        if(tmp.Ry < tmp.By)
                            By += 1;
                        else
                            Ry += 1;
                    }
                }
                if(!Visit[Rx][Ry][Bx][By]) {
                    q.offer(new BallInfo(Rx, Ry, Bx, By, tmp.cnt + 1));
                }
            }
        }
        System.out.println("-1");
    }
}
