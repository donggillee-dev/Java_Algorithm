package BOJ.IMP.Gold;
import java.io.*;
import java.util.*;

public class boj_17837 {
    private static class Info {
        int num;
        int dir;
        public Info(int n, int d) {
            this.num = n;
            this.dir = d;
        }
    }
    private static class Info2 {
        int x, y, nth;
        public Info2(int nx, int ny, int th) {
            this.x = nx;
            this.y = ny;
            this.nth = th;
        }
    }
    static int N, K;
    static int[] dir_x = {-1, 0, 0, -1, 1};// 1 right, 2 left, 3 up , 4 down
    static int[] dir_y = {-1, 1, -1, 0, 0};
    static int[][] MapColorInfo; // 0 white , 1 red , 2 blue
    static LinkedList<Info>[][] HorseMap;

    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken());
        MapColorInfo = new int[N + 1][N + 1];
        HorseMap = new LinkedList[N + 1][N + 1];

        for(int i = 1; i <= N; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j =1; j <= N; j++) {
                MapColorInfo[i][j] = Integer.parseInt(stk.nextToken());
                HorseMap[i][j] = new LinkedList<>();
            }
        }
        for(int i = 1; i <= K; i++) {
            stk = new StringTokenizer(br.readLine());
            int num = i;
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            int dir = Integer.parseInt(stk.nextToken());
            HorseMap[x][y].add(new Info(num, dir));
        }
        int time = 1;
        while(time <= 1000) {
            if(!Play()) break;
            time++;
        }

        if(time > 1000) sb.append(-1).append("\n");
        else sb.append(time).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static boolean Play() {
        int idx = 1;
        boolean ret = true;

        while(idx <= K) {
            Info2 tmp = Search(idx);//찾은 말의 위치 및 겹쳐있을 경우 아래에서 몇번째 말인지
            int x = tmp.x;
            int y = tmp.y;
            int nth = tmp.nth;

            Info horse = HorseMap[x][y].get(nth);
            int nx = x + dir_x[horse.dir];
            int ny = y + dir_y[horse.dir];
            int oppo = 0;

            if(horse.dir == 1 || horse.dir == 3) oppo = horse.dir + 1;//get opposite direction
            else if(horse.dir == 2 || horse.dir == 4) oppo = horse.dir - 1;
            else;
            if(HorseMap[x][y].size() == 1) {//찾은 위치에 말 하나만 있는 경우
                if(nx < 1 || ny < 1 || nx > N || ny > N || MapColorInfo[nx][ny] == 2) {//넘어가는 경우
                    nx = x + dir_x[oppo];
                    ny = y + dir_y[oppo];

                    if(nx < 1 || ny < 1 || nx > N || ny > N) {//1개의 말이기 떄문에 뒤집어주는거 신경 안써도됨
                        nx = x;
                        ny = y;
                        HorseMap[x][y].set(0, new Info(idx, oppo));
                    } else {
                        if(MapColorInfo[nx][ny] == 2) {
                            nx = x;
                            ny = y;
                            HorseMap[x][y].set(0, new Info(idx, oppo));
                        } else {
                            HorseMap[x][y].remove(0);
                            HorseMap[nx][ny].add(new Info(idx, oppo));
                        }
                    }
                } else {//1개의 말이기 떄문에 뒤집어주는거 신경 안써도됨
                    HorseMap[x][y].remove(0);
                    HorseMap[nx][ny].add(new Info(idx, horse.dir));
                }
            } else {//찾은 위치에 말이 여러개가 쌓여있는 경우
                if(nx < 1 || ny < 1 || nx > N || ny > N || MapColorInfo[nx][ny] == 2) {//넘어가는 경우
                    LinkedList<Info> Move = new LinkedList<>();
                    nx = x + dir_x[oppo];
                    ny = y + dir_y[oppo];
                    if(nx < 1 || ny < 1 || nx > N || ny > N) {//1개의 말이기 떄문에 뒤집어주는거 신경 안써도됨
                        nx = x;
                        ny = y;
                        HorseMap[x][y].set(nth, new Info(idx, oppo));
                    } else {
                        if(MapColorInfo[nx][ny] == 2) {
                            nx = x;
                            ny = y;
                            HorseMap[x][y].set(nth, new Info(idx, oppo));
                        } else {
                            HorseMap[x][y].set(nth, new Info(idx, oppo));
                            for(int i = nth; i < HorseMap[x][y].size(); i++) {
                                Move.add(HorseMap[x][y].get(i));
                                HorseMap[x][y].remove(i);
                                i--;
                            }
                            if (MapColorInfo[nx][ny] == 1) {
                                for(int i = Move.size() - 1; i >= 0; i--) {
                                    HorseMap[nx][ny].add(Move.get(i));
                                }
                            } else {
                                for(int i = 0; i < Move.size(); i++) {
                                    HorseMap[nx][ny].add(Move.get(i));
                                }
                            }
                        }
                    }
                    Move.clear();
                } else {
                    LinkedList<Info> Move = new LinkedList<>();
                    for(int i = nth; i < HorseMap[x][y].size(); i++) {
                        Move.add(HorseMap[x][y].get(i));
                        HorseMap[x][y].remove(i);
                        i--;
                    }
                    if(MapColorInfo[nx][ny] == 1) {
                        for(int i = Move.size() - 1; i >= 0; i--) {
                            HorseMap[nx][ny].add(Move.get(i));
                        }
                    } else {
                        for(int i = 0; i < Move.size(); i++) {
                            HorseMap[nx][ny].add(Move.get(i));
                        }
                    }
                }
            }
            if(HorseMap[nx][ny].size() >= 4) {
                ret = false;
                break;
            }
            idx++;
        }
        return ret;
    }
    private static Info2 Search(int num) {
        Info2 ret = new Info2(0, 0, 0);
        loop1:for(int i = 1; i <= N; i++) {
            for(int j = 1; j<= N; j++) {
                if(HorseMap[i][j].size() != 0) {
                    Info cur;
                    boolean Found = false;
                    int nth = 0;
                    for(Iterator<Info> it = HorseMap[i][j].iterator(); it.hasNext();) {
                        cur = it.next();
                        if(cur.num == num) {
                            Found = true;
                            break;
                        }
                        nth++;
                    }
                    if(Found) {
                        ret = new Info2(i, j, nth);
                        break loop1;
                    }
                }
            }
        }
        return ret;
    }
}
