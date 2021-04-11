package Programmers.Programmers_Lv2;

import java.util.*;

public class GameMapDist {
    private static class Info {
        int r, c, cnt;

        public Info(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
    private static int solution(int[][] maps) {
        int answer = 987654321;
        int row = maps.length, col = maps[0].length;
        int[] dir_r = {-1, 0, 1, 0}, dir_c = {0, -1, 0, 1};
        boolean[][] visited = new boolean[row][col];

        PriorityQueue<Info> pq = new PriorityQueue<Info>(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                return o1.cnt - o2.cnt;
            }
        });

        pq.add(new Info(row - 1, col - 1, 1));

        while (!pq.isEmpty()) {
            Info inf = pq.poll();

            if (inf.r == 0 && inf.c == 0) {
                answer = Math.min(answer, inf.cnt);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nr = inf.r + dir_r[i];
                int nc = inf.c + dir_c[i];

                if (nr < 0 || nc < 0 || nr >= row || nc >= col || maps[nr][nc] == 0) continue;

                if (!visited[nr][nc]) {
                    pq.add(new Info(nr, nc, inf.cnt + 1));
                    visited[nr][nc] = true;
                }
            }
        }
        return answer == 987654321 ? -1 : answer;
    }

    public static void main(String[] args) {
        int[][] maps = {
                {1,0,1,1,1},
                {1,0,1,0,1},
                {1,0,1,1,1},
                {1,1,1,0,1},
                {0,0,0,0,1}
        };

        System.out.println(solution(maps));
    }
}
