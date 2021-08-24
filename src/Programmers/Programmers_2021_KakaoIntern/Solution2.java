package Programmers.Programmers_2021_KakaoIntern;

import java.util.*;

public class Solution2 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[][]{
                {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPXX", "OXXXP", "POOXX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
        })));
    }

    private static class Pos {
        int row;
        int col;
        int dist;

        public Pos(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }

    private static boolean isPossible(int row, int col) {
        if(row < 0 || col < 0 || row >= 5 || col >= 5) {
            return false;
        }

        return true;
    }

    private static int[] solution(String[][] places) {
        int length = places.length;
        int[] ans = new int[length];
        int[] dir_r = {-1, 0, 1, 0};
        int[] dir_c = {0, -1, 0, 1};

        Arrays.fill(ans, 1);

        for (int place = 0; place < length; place++) {
            boolean[][] visited = new boolean[5][5];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (!visited[i][j] && places[place][i].charAt(j) == 'P') {
                        visited[i][j] = true;
                        Queue<Pos> q = new LinkedList<>();
                        q.add(new Pos(i, j, 0));

                        while(!q.isEmpty()) {
                            Pos p = q.poll();

                            for(int dir = 0; dir < 4; dir++) {
                                int nr = p.row + dir_r[dir];
                                int nc = p.col + dir_c[dir];

                                if(isPossible(nr, nc) && places[place][nr].charAt(nc) != 'X' && !visited[nr][nc]) {
                                    visited[nr][nc] = true;
                                    if(places[place][nr].charAt(nc) == 'P') {
                                        if(p.dist + 1 <= 2) {
                                            ans[place] = 0;
                                        }
                                        q.add(new Pos(nr, nc, 0));
                                    } else {
                                        q.add(new Pos(nr, nc, p.dist + 1));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return ans;
    }
}
