package BOJ.IMP.Gold;

//Logic
//각 좌표는 x, y로 나타냄 ( x : 행, y : 열 )
//모든 물고기는 1~16까지의 번호를 가지고 물고기는 같은 번호를 가질 수 있다, 물고기들은 8개의 방향을 가짐
//상어는 (0, 0)에 들어가서 해당 물고기를 먹고 그 물고의 방향을 가짐
//이후 물고기들이 무빙 시작
//번호가 작은 물고기들부터 움직인다
//이동하려는 곳에 다른 물고기가 있으면 위치를 서로 뒤바꿈
//이동하려는 곳이 벽이나 상어가 존재하는 곳이면 반시계로 45도 회전해서 이동 시도 ( 아무리 돌려도 못움직이면 그냥 안움직임)

//이 후 상어가 움직이는데 방향에 있는 칸으로 여러개 이동 가능
//이동하는 도중에 있는 칸에 있는 물고기는 먹지 않음
//이동할 수 있는 모든 칸에 물고기 존재 안하면 상어는 떠난다

//상어가 먹을 수 있는 최대 물고기번호의 합을 구해라

//브루트 포스 + 구현 + 백트레킹

//물고기 움직이고 , 상어 움직일 수 있는 경우의 수 움직이고 -> 재귀로

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_19236 {
    //8개의 방향 나타내는 배열
    private static int ans;
    private static final int[] dir_x = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] dir_y = {0, 0, -1, -1, -1, 0, 1, 1, 1};

    private static class Fish {
        int x, y, num, dir;

        public Fish(int x, int y, int num, int dir) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.dir = dir;
        }
    }
    private static class Pos implements Comparable<Pos> {
        int x, y, num;

        public Pos(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }

        @Override
        public int compareTo(Pos p) {
            return this.num - p.num;
        }
    }
    private static boolean isPossible1(int nx, int ny) {
        if(nx < 0 || ny < 0 || nx >= 4 || ny >= 4) return false;

        return true;
    }
    private static boolean isPossible2(int nx, int ny, int sharkX, int sharkY) {
        if(nx == sharkX && ny == sharkY) return false;

        return true;
    }
    private static void copyMap(Fish[][] source, Fish[][] copy) {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(source[i][j] == null) {
                    continue;
                }
                copy[i][j] = new Fish(source[i][j].x, source[i][j].y, source[i][j].num, source[i][j].dir);
            }
        }
    }
    private static void simulation(int sharkX, int sharkY, int sharkDir, int sum, Fish[][] oriMap) {
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        Fish[][] cpyMap = new Fish[4][4];

        copyMap(oriMap, cpyMap);

        //물고기 번호 우선순위 큐
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if((i == sharkX && j == sharkY) || cpyMap[i][j] == null) continue;

                pq.add(new Pos(i, j, cpyMap[i][j].num));
            }
        }

        //재귀 호출시마다 정답 갱신
        if(ans < sum) ans = sum;

        //물고기 이동
        while(!pq.isEmpty()) {
            Pos elem = pq.poll();
            int dir = cpyMap[elem.x][elem.y].dir;
            int num = cpyMap[elem.x][elem.y].num;

            for(int i = 0; i <= 8; i++) {
                int nd = (dir + i) % 8;
                int nx = elem.x + dir_x[nd];
                int ny = elem.y + dir_y[nd];

                if(!isPossible1(nx, ny)) continue; //벗어나는 경우

                if(cpyMap[nx][ny] == null) { //해당 위치에 아무것도 없는 경우
                    if(!isPossible2(nx, ny, sharkX, sharkY)) continue; //상어 있는 경우

                    //없는 경우
                    cpyMap[nx][ny] = new Fish(nx, ny, num, nd);
                    cpyMap[elem.x][elem.y] = null;
                    break;
                } else {
                    //두개를 바꿔줘야힘 좌표 바꿔줄 필요없고 그냥 번호랑 방향만 바꿔주면 됨
                    int tmp = nd;
                    cpyMap[elem.x][elem.y].dir = cpyMap[nx][ny].dir;
                    cpyMap[nx][ny].dir = tmp;

                    tmp = cpyMap[elem.x][elem.y].num;
                    cpyMap[elem.x][elem.y].num = cpyMap[nx][ny].num;
                    cpyMap[nx][ny].num = tmp;
                }
            }
        }

        for(int mov = 1; mov <= 3; mov++) {//칸 수
                int nx = sharkX + (dir_x[sharkDir] * mov);
                int ny = sharkY + (dir_y[sharkDir] * mov);

                if(!isPossible1(nx, ny) || cpyMap[nx][ny] == null) continue;

                Fish tmp = new Fish(cpyMap[nx][ny].x, cpyMap[nx][ny].y, cpyMap[nx][ny].num, cpyMap[nx][ny].dir);
                int tmpSum = (sum + cpyMap[nx][ny].num);
                cpyMap[nx][ny] = null;
                simulation(nx, ny, tmp.dir, tmpSum, cpyMap);
                cpyMap[nx][ny] = new Fish(tmp.x, tmp.y, tmp.num, tmp.dir);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        Fish[][] map = new Fish[4][4];
        int sharkX = 0, sharkY = 0, sharkDir = 0;

        for (int i = 0; i < 4; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int a = Integer.parseInt(stk.nextToken());
                int b = Integer.parseInt(stk.nextToken());
                if(i == 0 && j == 0) {
                    ans += a;
                    sharkDir = b;
                    continue;
                }

                map[i][j] = new Fish(i, j, a, b);
            }
        }

        simulation(sharkX, sharkY, sharkDir, ans, map);

        System.out.println(ans);
    }
}
