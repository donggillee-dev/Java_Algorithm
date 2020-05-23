package Samsung_Algo;
import java.io.*;
import java.util.*;

public class algo_17144 {
    private static class Info {
        int x, y;
        public Info(int nx, int ny) {
            this.x = nx;
            this.y = ny;
        }
    }
    private static class Info2 {
        int x, y, val;
        boolean isFirst;
        public Info2(int nx, int ny, int v, boolean f) {
            this.x = nx;
            this.y = ny;
            this.val = v;
            this.isFirst = f;
        }
    }
    private static class Info3 {
        int x, y, val;
        public Info3(int nx, int ny, int v) {
            this.x = nx;
            this.y = ny;
            this.val = v;
        }
    }
    static int R, C, T, ans = 0;
    static int UpperX, UpperY, DownX, DownY;
    static int[][] Arr;
    static int[] dir_x = {0, 0, -1, 1};
    static int[] dir_y = {-1, 1, 0, 0};
    static LinkedList<Info> List = new LinkedList<>();
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
                } else if(val > 0) {
                    List.add(new Info(i, j));
                }
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
        Info tmp;
        Info2 tmp2;
        LinkedList<Info2> Sub = new LinkedList<>();
        for(int idx = 0; idx < List.size(); idx++) {
            tmp = List.get(idx);
            int Amount = Arr[tmp.x][tmp.y] / 5;
            int cnt = 0;
            for(int i = 0; i < 4; i++) {
                int nx = tmp.x + dir_x[i];
                int ny = tmp.y + dir_y[i];
                if(nx < 1 || ny < 1 || nx > R || ny > C) continue;
                if((nx == UpperX && ny == UpperY) || (nx == DownX && ny == DownY)) continue;
                if(Arr[nx][ny] == 0 && Amount > 0) {
                    Sub.add(new Info2(nx, ny, Amount, true));
                } else if(Arr[nx][ny] != 0 && Amount > 0) {
                    Sub.add(new Info2(nx, ny, Amount, false));
                }
                cnt++;
            }
            Arr[tmp.x][tmp.y] -= (Amount * cnt);
        }
        for(int i = 0; i < Sub.size(); i++) {
            tmp2 = Sub.get(i);
            if(tmp2.isFirst && Arr[tmp2.x][tmp2.y] == 0) {
                List.add(new Info(tmp2.x, tmp2.y));
            }
            Arr[tmp2.x][tmp2.y] += tmp2.val;
        }
    }
    private static void Move() {
        LinkedList<Info> Rm = new LinkedList<>();
        LinkedList<Info3> Sub = new LinkedList<>();
        Info tmp;
        int val;
        for(int i = 0; i < List.size(); i++) {
            tmp = List.get(i);
            if(tmp.y == 1 && tmp.x < UpperX) {
                if(tmp.x == UpperX - 1) {
                    Rm.add(tmp);
                    List.remove(i);
                    i--;
                } else {
                    val = Arr[tmp.x][tmp.y];
                    Arr[tmp.x][tmp.y] = 0;
                    tmp.x++;
                    List.set(i, tmp);
                    Sub.add(new Info3(tmp.x, tmp.y, val));
                }
            } else if(tmp.y == 1 && tmp.x > DownX) {
                if(tmp.x == DownX + 1) {
                    Rm.add(tmp);
                    List.remove(i);
                    i--;
                } else {
                    val = Arr[tmp.x][tmp.y];
                    Arr[tmp.x][tmp.y] = 0;
                    tmp.x--;
                    List.set(i, tmp);
                    Sub.add(new Info3(tmp.x, tmp.y, val));
                }
            } else if(tmp.x == 1 && tmp.y >= 2) {
                val = Arr[tmp.x][tmp.y];
                Arr[tmp.x][tmp.y] = 0;
                tmp.y--;
                List.set(i, tmp);
                Sub.add(new Info3(tmp.x, tmp.y, val));
            } else if(tmp.x == R && tmp.y >= 2) {
                val = Arr[tmp.x][tmp.y];
                Arr[tmp.x][tmp.y] = 0;
                tmp.y--;
                List.set(i, tmp);
                Sub.add(new Info3(tmp.x, tmp.y, val));
            } else if(tmp.x == UpperX && tmp.y <= C - 1) {
                val = Arr[tmp.x][tmp.y];
                Arr[tmp.x][tmp.y] = 0;
                tmp.y++;
                List.set(i, tmp);
                Sub.add(new Info3(tmp.x, tmp.y, val));
            } else if(tmp.x == DownX && tmp.y <= C - 1){
                val = Arr[tmp.x][tmp.y];
                Arr[tmp.x][tmp.y] = 0;
                tmp.y++;
                List.set(i, tmp);
                Sub.add(new Info3(tmp.x, tmp.y, val));
            } else if(tmp.y == C && tmp.x >= DownX && tmp.x <= R - 1) {
                val = Arr[tmp.x][tmp.y];
                Arr[tmp.x][tmp.y] = 0;
                tmp.x++;
                List.set(i, tmp);
                Sub.add(new Info3(tmp.x, tmp.y, val));
            } else if(tmp.y == C && tmp.x <= UpperX && tmp.x >= 1) {
                val = Arr[tmp.x][tmp.y];
                Arr[tmp.x][tmp.y] = 0;
                tmp.x--;
                List.set(i, tmp);
                Sub.add(new Info3(tmp.x, tmp.y, val));
            } else;
        }
        //Upper
        for(int i = 0; i < Rm.size(); i++) {
            tmp = Rm.get(i);
            Arr[tmp.x][tmp.y] = 0;
        }
        Info3 tmp2;
        for(int i = 0; i < Sub.size(); i++) {
            tmp2 = Sub.get(i);
            Arr[tmp2.x][tmp2.y] = tmp2.val;
        }
    }
    private static void solve() {
        Info tmp;
        for(int i = 0; i < List.size(); i++) {
            tmp = List.get(i);
            ans += Arr[tmp.x][tmp.y];
        }
    }
}
