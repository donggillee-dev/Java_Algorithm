package BOJ.IMP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//Logic
//이동과정을 제대로 이해하지 않아서 애먹은 문제
//범위를 초과하여 파이어볼이 이동하게 되면 다시 돌아옴 => 원형큐처럼
//풀이시간 : 45분

public class boj_20056 {
    private static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    private static class Info {
        int row, col;
        int mass, velo, dir;

        public Info(int row, int col, int mass, int velo, int dir) {
            this.row = row;
            this.col = col;
            this.mass = mass;
            this.velo = velo;
            this.dir = dir;
        }
    }

    private static void move(int n, ArrayList<Info> list, Queue<Info>[][] map) {
        int size = list.size();

        for (int i = 0; i < size; i++) {
            Info inf = list.get(i);

            int move = inf.velo % n;
            int nr = inf.row + (dr[inf.dir] * move);
            int nc = inf.col + (dc[inf.dir] * move);

            if (nr < 1) nr += n;
            else if (nr > n) nr -= n;
            if (nc < 1) nc += n;
            else if (nc > n) nc -= n;

            map[nr][nc].add(new Info(nr, nc, inf.mass, inf.velo, inf.dir));
        }
    }

    private static void split(int row, int col, Queue<Info> q, ArrayList<Info> list) {
        int size = q.size();
        int sumVelo = 0, sumMass = 0;
        int modVelo = 0, modMass = 0, modDir = 0;
        int dirCnt = 0;


        while (!q.isEmpty()) {
            Info inf = q.poll();

            sumVelo += inf.velo;
            sumMass += inf.mass;
            if (inf.dir % 2 == 0) dirCnt++;
        }

        if(!(dirCnt == size || dirCnt == 0)) {
            modDir = 1;
        }

        modVelo = (int)Math.floor((double)sumVelo / size);
        modMass = (int)Math.floor((double)sumMass / 5);

        if(modMass == 0) return;

        for(int i = 0; i < 4; i++, modDir += 2) {
            list.add(new Info(row, col, modMass, modVelo, modDir));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        ArrayList<Info> list = new ArrayList<>();
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        int k = Integer.parseInt(stk.nextToken());

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(stk.nextToken());
            int col = Integer.parseInt(stk.nextToken());
            int mass = Integer.parseInt(stk.nextToken());
            int velo = Integer.parseInt(stk.nextToken());
            int dir = Integer.parseInt(stk.nextToken());

            list.add(new Info(row, col, mass, velo, dir));
        }

        while (k-- > 0) {
            Queue<Info>[][] map = new LinkedList[n + 1][n + 1];

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    map[i][j] = new LinkedList<>();
                }
            }

            move(n, list, map);

            list.clear();

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (map[i][j].size() >= 2) {
                        split(i, j, map[i][j], list);
                    } else if(!map[i][j].isEmpty()){
                        Info inf = map[i][j].poll();
                        list.add(new Info(inf.row, inf.col, inf.mass, inf.velo, inf.dir));
                    }
                }
            }
        }

        int ans = 0;

        for(Info inf : list) {
            ans += inf.mass;
        }

        System.out.println(ans);
    }
}
