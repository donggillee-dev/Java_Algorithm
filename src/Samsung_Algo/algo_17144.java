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
    private static class Compare implements Comparator<Info> {
        @Override
        public int compare(Info o1, Info o2) {
            if(o1.x > o2.x) {
                return 1;
            } else if(o1.x == o2.x) {
                if(o1.y > o2.y) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        }
    }
    static int R, C, T, ans = 0;
    static int UpperX, UpperY, DownX, DownY;
    static int[][] Arr;
    static int[] dir_x = {0, 0, -1, 1};
    static int[] dir_y = {-1, 1, 0, 0};
    static LinkedList<Info> List = new LinkedList<>();
    //Spread -> LinkedList -> just x, y
    //Move -> Array -> value
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
//            System.out.println("======" + time + "=======");
//            System.out.println("====== Before Spread =======");
//            for(int i = 1; i <= R; i++) {
//                System.out.println(Arrays.toString(Arr[i]));
//            }
            Spread();
//            System.out.println("====== After Spread =======");
//            for(int i = 1; i <= R; i++) {
//                System.out.println(Arrays.toString(Arr[i]));
//            }
            Move();
            time++;
//            System.out.println("====== After Move =======");
//            for(int i = 1; i <= R; i++) {
//                System.out.println(Arrays.toString(Arr[i]));
//            }
//            System.out.println();
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
        Info tmp;
        Collections.sort(List, new Compare());//List Information
//        for(int i = 0; i < List.size(); i++) {
//            int x = List.get(i).x;
//            int y = List.get(i).y;
//            System.out.println(x + " " + y + " " + Arr[x][y]);
//        }
        for(Iterator<Info> it = List.iterator(); it.hasNext();) {
            tmp = it.next();
            if(tmp.y == 1 && tmp.x < UpperX) {
                if(tmp.x == UpperX - 1) it.remove();
                else tmp.x++;
            } else if(tmp.y == 1 && tmp.x > DownX) {
                if(tmp.x == DownX + 1) it.remove();
                else tmp.x--;
            } else if(tmp.x == 1 && tmp.y >= 2) {
                tmp.y--;
            } else if(tmp.x == R && tmp.y >= 2) {
                tmp.y--;
            } else if(tmp.x == UpperX && tmp.y <= C - 1) {
                tmp.y++;
            } else if(tmp.x == DownX && tmp.y <= C - 1){
                tmp.y++;
            } else if(tmp.y == C && tmp.x >= DownX && tmp.x <= R - 1) {
                tmp.x++;
            } else if(tmp.y == C && tmp.x <= UpperX && tmp.x >= 1) {
                tmp.x--;
            } else;
        }

        //Upper


        Arr[UpperX - 1][UpperY] = 0;//공기 청정기 바로 위 열에 대해
        for(int i = UpperX - 1; i >= 2; i--) {
            Arr[i][UpperY] = Arr[i - 1][UpperY];
            Arr[i - 1][UpperY] = 0;
        }
        for(int i = UpperY; i <= C - 1; i++) {
            Arr[1][i] = Arr[1][i + 1];
            Arr[1][i + 1] = 0;
        }//공기 청정기 위 행에 대해
        for(int i = 1; i <= UpperX - 1; i++) {

            Arr[i][C] = Arr[i + 1][C];
//            System.out.println(i + " " + Arr[i][C]);
            Arr[i + 1][C] = 0;
        }//공기 청정기 반대 끝쪽 열에 대해
        for(int i = C; i >= UpperY + 2; i--) {
            Arr[UpperX][i] = Arr[UpperX][i - 1];
            Arr[UpperX][i - 1] = 0;
        }//공기 청정기 같은 행에 대해

        //Down
        Arr[DownX + 1][DownY] = 0;
        for(int i = DownX + 1; i <= R - 1; i++) {//공기 청정기 바로 아래 열에 대해
            Arr[i][DownY] = Arr[i + 1][DownY];
            Arr[i + 1][DownY] = 0;
        }
        for(int i = DownY; i <= C - 1; i++) {//공기 청정기 아래 행에 대해
            Arr[R][i] = Arr[R][i + 1];
            Arr[R][i + 1] = 0;
        }
        for(int i = R; i >= DownX + 1; i--) {//공기 청정기 반대편 열에 대해
            Arr[i][C] = Arr[i - 1][C];
            Arr[i - 1][C] = 0;
        }
        for(int i = C; i >= DownY + 2; i--) {
            Arr[DownX][i] = Arr[DownX][i - 1];
            Arr[DownX][i - 1] = 0;
        }

//        Collections.sort(List, new Compare());//List Information
//        System.out.println("After");
//        for(int i = 0; i < List.size(); i++) {
//            int x = List.get(i).x;
//            int y = List.get(i).y;
//            System.out.println(x + " " + y + " " + Arr[x][y]);
//        }
    }
    private static void solve() {
        Info tmp;
        for(Iterator<Info> it = List.iterator(); it.hasNext();) {
            tmp = it.next();
            ans += Arr[tmp.x][tmp.y];
        }
    }
}
