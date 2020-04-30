package Samsung_Algo;
import java.io.*;
import java.util.*;

public class algo_17825 {
    static int[][] Map = new int[11][11];
    static int[][] Visited = new int[11][11];
    static PointInfo[][] Point = new PointInfo[11][11];
    static HorseInfo[] Horse = new HorseInfo[4];
    static int ans = 0;
    static int[] Dice = new int[10];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        Init();
        int i = 0;
        while(stk.hasMoreTokens()) {
            Dice[i] = Integer.parseInt(stk.nextToken());
            i++;
        }
        DFS(0, 0);
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void DFS(int nth_dice, int sum) {
        if(nth_dice > 9) {
            if(sum > ans) ans = sum;
            return;
        }

        for(int i = 0; i < 4; i++) {
            int before_x = Horse[i].x, after_x = Horse[i].x;
            int before_y = Horse[i].y, after_y = Horse[i].y;
//            System.out.println(before_x + " " + before_y);
            PointInfo tmp = Point[before_x][before_y];

            if(Point[before_x][before_y].Blue) {
                after_x += tmp.move_x2;
                after_y += tmp.move_y2;
            } else {
                after_x += tmp.move_x1;
                after_y += tmp.move_y1;
            }

            for(int j = 1; j < Dice[nth_dice]; j++) {
                tmp = Point[after_x][after_y];
                after_x += tmp.move_x1;
                after_y += tmp.move_y1;
                if(after_x == 1 && after_y == 5) break;
            }
//            System.out.println("============nth_dice : " + nth_dice + ", sum = " + sum);
//            for(int j = 0; j < 11; j++) {
//                for(int k = 0; k < 11; k++) {
//                    System.out.print(Visited[j][k] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println("=========================");
            if(Visited[after_x][after_y] == 0) {//방문 가능한 지점에 대해서
                Visited[after_x][after_y]++;//방문한다고 정의하고
                Horse[i].x = after_x;
                Horse[i].y = after_y;
                DFS(nth_dice + 1, sum + Map[after_x][after_y]);
                Visited[after_x][after_y]--;
                Horse[i].x = before_x;
                Horse[i].y = before_y;
            }
        }
    }
//    private static boolean Play(int DicePoint) {
//        int DisableCnt = 0;
//        int AbleCnt = 0;
//
//        for(int i = 0; i < 4; i++) {
//            int Dice_x = Horse[i].x;
//            int Dice_y = Horse[i].y;
//
//            PointInfo first_tmp = Point[Dice_x][Dice_y];
//
//            if(Dice_x == 1 && Dice_y == 5) {
//                DisableCnt++;
//                Horse[i].isAble = false;
//                continue;
//            }
//
//            if(first_tmp.HorseCnt > 1) {//현재 선택한 말이 이동 가능한 말인지 체크
//                DisableCnt++;
//                Horse[i].isAble = false;
//                continue;
//            }
//
//            if(first_tmp.Blue) { //이동 가능한 말이면 blue칸에서 시작하는지 아닌지 체크
//                Dice_x += first_tmp.move_x2;
//                Dice_y += first_tmp.move_y2;
//            } else {
//                Dice_x += first_tmp.move_x1;
//                Dice_y += first_tmp.move_y1;
//            }
//
//            for(int j = 1; j < DicePoint; j++) { //주사위 개수대로 칸 이동
//                first_tmp = Point[Dice_x][Dice_y];
//                Dice_x += first_tmp.move_x1;
//                Dice_y += first_tmp.move_y1;
//                if(Dice_x == 1 && Dice_y == 5) break;
//            }
//            if(Dice_x == 1 && Dice_y == 5) {//도착칸이면 선택한 말 시나리오대로 이동가능
//                AbleCnt++;
//                Horse[i].isAble = true;
//            } else {//도착칸이 아닐경우
//                if(Point[Dice_x][Dice_y].HorseCnt >= 1) {//도착칸에 이미 말이 존재하는 경우
//                    DisableCnt++;
//                    Horse[i].isAble = false;
//                } else {//존재하지 않는 경우
//                    AbleCnt++;
//                    Horse[i].isAble = true;
//                }
//            }
//        }
//
//        if(DisableCnt == 4) return false;
//
//        if(AbleCnt == 1) {
//            int nth_horse = 0;
//            int Dice_x = 0, Dice_y = 0;
//            for(int i = 0; i < 4; i++) {
//                if(Horse[i].isAble) {
//                    nth_horse = i;
//                    Dice_x = Horse[i].x;
//                    Dice_y = Horse[i].y;
//                    break;
//                }
//            }
//            PointInfo first_tmp = Point[Dice_x][Dice_y];
//            Point[Dice_x][Dice_y].HorseCnt--;
//
//            if(Point[Dice_x][Dice_y].Blue) {
//                Dice_x += first_tmp.move_x2;
//                Dice_y += first_tmp.move_y2;
//            } else {
//                Dice_x += first_tmp.move_x1;
//                Dice_y += first_tmp.move_y1;
//            }
//            for(int i = 1; i < DicePoint; i++) {
//                first_tmp = Point[Dice_x][Dice_y];
//                Dice_x += first_tmp.move_x1;
//                Dice_y += first_tmp.move_y1;
//                if(Dice_x == 1 && Dice_y == 5) break;
//            }
//            Horse[nth_horse].x = Dice_x;
//            Horse[nth_horse].y = Dice_y;
//            Point[Dice_x][Dice_y].HorseCnt++;
//
//            if(Dice_x != 1 && Dice_y != 5) {
//                ans += Map[Dice_x][Dice_y];
//            }
//            return true;
//        } else if(AbleCnt == 0) return false;
//        else {
//            System.out.println("all can do");
//            int max = Integer.MIN_VALUE;
//            int Dice_x = 0;
//            int Dice_y = 0;//최대 도착값을 가지는 말의 도착 후 좌표
//            int nth_horse = 0; //몇번째 말이 최대값을 가지는지 저장하기 위함
//            for(int i = 0; i < 4; i++) {
//                if(Horse[i].isAble) {//움직일수 있는 말들에 대해서 이동 -> 이동후의 최대값을 가지는 말을 최종적으로 이동
//                    int tmp_x = Horse[i].x;
//                    int tmp_y = Horse[i].y;
//                    PointInfo tmp_first = Point[tmp_x][tmp_y];
//
//                    if(Point[tmp_x][tmp_y].Blue) {
//                        tmp_x += tmp_first.move_x2;
//                        tmp_y += tmp_first.move_y2;
//                    } else {
//                        tmp_x += tmp_first.move_x1;
//                        tmp_y += tmp_first.move_y1;
//                    }
//                    for(int j = 1; j < DicePoint; j++) {
//                        tmp_first = Point[tmp_x][tmp_y];
//                        tmp_x += tmp_first.move_x1;
//                        tmp_y += tmp_first.move_y1;
//                    }
//                    if(Map[tmp_x][tmp_y] > max) {
//                        nth_horse = i;
//                        Dice_x = tmp_x;
//                        Dice_y = tmp_y;
//                        max = Map[tmp_x][tmp_y];
//                    }
//                }
//            }
//            //결과적으로 nth_horse의 x, y칸에 cnt -- 해주고
//            //이동시켜주고 해당 x, y 를 horse에 대입해주고
//            //도착지점의 값을 ans에 더해주고
//            //도착지점의 cnt++
////            System.out.println(Dice_x + " " + Dice_y + " " + max);
//            ans += max;
//            Point[Horse[nth_horse].x][Horse[nth_horse].y].HorseCnt--;
//            Horse[nth_horse].x = Dice_x;
//            Horse[nth_horse].y = Dice_y;
//            Point[Dice_x][Dice_y].HorseCnt++;
//            return true;
//        }
//    }
    private static void Init() {
        for(int i = 0; i < 10; i++) {
            Arrays.fill(Map[i], -1);
        }
        for(int i = 0; i < 4; i++) {
            Horse[i] = new HorseInfo(0, 0);
        }

        Map[0][0] = 0;
        Point[0][0] = new PointInfo(false, 1, 0, -1, -1);

        for(int i = 1; i <= 5; i++) {
            Map[i][0] = i * 2;
            if(i != 5) Point[i][0] = new PointInfo(false, 1, 0, -1, -1);
            else Point[i][0] = new PointInfo(true, 1, 1, 0, 1);
        }

        Map[5][1] = 13;
        Point[5][1] = new PointInfo(false, 0, 1, -1 , -1);

        for(int j = 2; j <= 3; j++) {
            Map[5][j] = Map[5][j - 1] + 3;
            if(j == 3) Point[5][j] = new PointInfo(false, 0, 2, -1, -1);
            else Point[5][j] = new PointInfo(false, 0, 1, -1, -1);

        }

        Map[6][1] = 12;
        Point[6][1] = new PointInfo(false, 1, 1, -1, -1);

        Map[7][2] = 14;
        Point[7][2] = new PointInfo(false, 1, 1, -1, -1);

        Map[8][3] = 16;
        Point[8][3] = new PointInfo(false, 0, 1, -1, -1);

        Map[8][4] = 18;
        Point[8][4] = new PointInfo(false, 0, 1, -1, -1);

        Map[8][5] = 20;
        Point[8][5] = new PointInfo(true, 0, 1, -1, 0);

        for(int i = 7; i >= 6; i--) {
            Map[i][5] = Map[i + 1][5] + 2;
            Point[i][5] = new PointInfo(false, -1, 0, -1, -1);
        }

        Map[5][5] = 25;
        Point[5][5] = new PointInfo(false, -1, 0, -1, -1);

        for(int i = 4; i >= 2; i--) {
            Map[i][5] = Map[i + 1][5] + 5;
            Point[i][5] = new PointInfo(false, -1, 0, -1, -1);
        }

        Map[8][6] = 22;
        Point[8][6] = new PointInfo(false, 0, 1, -1, -1);

        Map[8][7] = 24;
        Point[8][7] = new PointInfo(false, -1, 1, -1, -1);

        Map[7][8] = 26;
        Point[7][8] = new PointInfo(false, -1, 1, -1, -1);

        Map[6][9] = 28;
        Point[6][9] = new PointInfo(false, -1, 1, -1, -1);

        Map[5][10] = 30;
        Point[5][10] = new PointInfo(true, -1, -1, 0, -1);

        Map[5][9] = 28;
        Point[5][9] = new PointInfo(false, 0, -1, -1, -1);

        Map[5][8] = 27;
        Point[5][8] = new PointInfo(false, 0, -1, -1, -1);

        Map[5][7] = 26;
        Point[5][7] = new PointInfo(false, 0, -2, -1, -1);

        Map[4][9] = 32;
        Point[4][9] = new PointInfo(false, -1, -1, -1 ,-1);

        Map[3][8] = 34;
        Point[3][8] = new PointInfo(false, -1, -1, -1, -1);

        Map[2][7] = 36;
        Point[2][7] = new PointInfo(false, 0, -1, -1, -1);

        Map[2][6] = 38;
        Point[2][6] = new PointInfo(false, 0, -1, -1, -1);

        Map[1][5] = 0;
        Point[1][5] = new PointInfo(false, 0, 0, 0, 0);
        return;
    }

}
class PointInfo {//현재 윺놓이 포인트에 대한 정보 객체
    boolean Blue;//현재 칸이 분기하는 파란색 칸인지 여부
    int move_x1, move_x2;//파란색 칸이 아닐 경우 사용되는 이동용 좌표
    int move_y1, move_y2; //파란색 칸일 경우 사용하는 정보들 파란색 칸이 아닐 경우 -1로 초기화 되어있다
    public PointInfo(boolean B, int x1, int y1, int x2, int y2) {
        this.Blue = B;
        this.move_x1 = x1;
        this.move_x2 = x2;
        this.move_y1 = y1;
        this.move_y2 = y2;
    }
}
class HorseInfo {
    int x;
    int y;

    public HorseInfo(int x1, int y1) {
        this.x = x1;
        this.y = y1;
    }
}