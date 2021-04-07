package BOJ.IMP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
//Logic
//그냥 시뮬레이션 문제
//풀이 시간 : 45분
public class boj_2174 {
    private static class Info {
        int r, c, dir;
        public Info(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }
    private static int row = 0, col = 0, n = 0, m = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        HashMap<Integer, Info> hash = new HashMap<>();
        HashMap<Character, Integer> dirHash = new HashMap<>();

        col = Integer.parseInt(stk.nextToken());
        row = Integer.parseInt(stk.nextToken());
        stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        int[][] map = new int[row + 1][col + 1];
        dirHash.put('N', 0);
        dirHash.put('E', 1);
        dirHash.put('S', 2);
        dirHash.put('W', 3);

        for(int i = 1; i <= n; i++) {
            stk = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(stk.nextToken());
            int r = Integer.parseInt(stk.nextToken());
            int dir = dirHash.get(stk.nextToken().charAt(0));

            map[r][c] = i;
            hash.put(i, new Info(r, c, dir));
        }

        simulation(br, map, hash);
    }
    private static void simulation(BufferedReader br, int[][] map, HashMap<Integer, Info> hash) throws IOException {
        StringTokenizer stk;
        int[] dir_x = {0, 1, 0, -1};
        int[] dir_y = {1, 0, -1, 0};

        for(int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(stk.nextToken());
            Info robot = hash.get(num);
            char cmd = stk.nextToken().charAt(0);
            int cnt = Integer.parseInt(stk.nextToken());

            if(cmd == 'L') {
                int tmp = robot.dir - cnt;
                tmp %= 4;

                if(tmp < 0) {
                    tmp += 4;
                }

                robot.dir = tmp;
                hash.put(num, robot);
            } else if(cmd == 'R') {
                int tmp = robot.dir + cnt;
                tmp %= 4;

                robot.dir = tmp;
                hash.put(num, robot);
            } else {
                map[robot.r][robot.c] = 0;

                int nr = robot.r, nc = robot.c;
                for(int j = 0; j < cnt; j++) {
                    nr = nr + dir_y[robot.dir];
                    nc = nc + dir_x[robot.dir];

                    if(nr <= 0 || nc <= 0 || nr > row || nc > col) {
                        System.out.printf("Robot %d crashes into the wall\n", num);
                        return;
                    }

                    if(map[nr][nc] != 0) {
                        System.out.printf("Robot %d crashes into robot %d\n", num, map[nr][nc]);
                        return;
                    }
                }
                robot.r = nr; robot.c = nc;
                hash.put(num, robot);
                map[nr][nc] = num;
            }
        }
        System.out.println("OK");
    }
}
