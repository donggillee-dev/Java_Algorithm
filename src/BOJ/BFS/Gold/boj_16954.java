package BOJ.BFS.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//Logic
//다시 또 풀어봐야할거같다

public class boj_16954 {
    private static int row = 8, col = 8;
    private static int[] dr = {-1, 0, 1, 0, 0, -1, 1, -1, 1};
    private static int[] dc = {0, -1, 0, 1, 0, 1, -1, -1, 1};

    private static void moveWall(boolean[][] map, Queue<Integer> wall) {
        Queue<Integer> tmpQ = new LinkedList<>();

        while (!wall.isEmpty()) {
            int cur = wall.poll();
            int cr = cur / row;
            int cc = cur % row;

            map[cr][cc] = false;

            if (cr < 7) {
                tmpQ.add((cr + 1) * row + cc);
            }
        }

        while (!tmpQ.isEmpty()) {
            int pos = tmpQ.poll();
            int cr = pos / row;
            int cc = pos % row;

            map[cr][cc] = true;

            wall.add(pos);
        }
    }

    private static int move(boolean[][] map, Queue<Integer> wall) {
        Queue<Integer> q = new LinkedList<>();

        q.add(56);

        while (!q.isEmpty()) {
            int size = q.size();
            boolean[][] visited = new boolean[row][col];

            for (int i = 0; i < size; i++) {
                int cur = q.poll();

                int cr = cur / row;
                int cc = cur % col;

                if (map[cr][cc]) continue;

                if (cr == 0) return 1;

                if (wall.size() == 0) return 1;

                for (int dir = 0; dir < 9; dir++) {
                    int nr = cr + dr[dir];
                    int nc = cc + dc[dir];

                    if (nr < 0 || nc < 0 || nr >= row || nc >= col) continue;

                    if (!map[nr][nc] && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        q.add(nr * row + nc);
                    }
                }
            }

            if (wall.size() > 0) moveWall(map, wall);
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> wall = new LinkedList<>();
        boolean[][] map = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            String str = br.readLine();
            for (int j = 0; j < col; j++) {
                if (str.charAt(j) == '#') {
                    wall.add(row * i + j);
                    map[i][j] = true;
                }
            }
        }

        System.out.println(move(map, wall));
    }
}