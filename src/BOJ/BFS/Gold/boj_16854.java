package BOJ.BFS.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//Logic
//다시 풀어볼것

public class boj_16854 {
    private static int row = 8, col = 8;
    private static int[] dr = {-1, 0, 1, 0, 0, -1, 1, -1, 1};
    private static int[] dc = {0, -1, 0, 1, 0, 1, -1, -1, 1};

    private static void moveWall(boolean[][] map, Queue<Integer> wall) {
        Queue<Integer> tmpQ = new LinkedList<>();

        while(!wall.isEmpty()) {
            int pos = wall.poll();
            int row = pos / 8;
            int col = pos % 8;

            map[row][col] = false;

            if(row < 7) {
                tmpQ.add((row + 1) * 8 + col);
            }
        }

        while(!tmpQ.isEmpty()) {
            int pos = tmpQ.poll();
            int row = pos / 8;
            int col = pos % 8;

            map[row][col] = true;
            wall.add(pos);
        }
    }
    private static int bfs(boolean[][] map, Queue<Integer> wall) {
        Queue<Integer> q = new LinkedList<>();

        q.add(56);

        while(!q.isEmpty()) {
            int size = q.size();
            boolean visited[][] = new boolean[8][8];

            while(size --> 0) {
                int pos = q.poll();
                int row = pos / 8;
                int col = pos % 8;

                if(map[row][col]) continue;

                if(row == 0) return 1;

                if(wall.size() == 0) return 1;

                for(int i = 0; i < 9; i++) {
                    int nr = row + dr[i];
                    int nc = col + dc[i];

                    if(nr < 0 || nc < 0 || nr >= 8 || nc >= 8) continue;

                    if(!map[nr][nc] && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        q.add(nr * 8 + nc);
                    }
                }
            }

            if(wall.size() > 0) moveWall(map, wall);
        }

        return 0;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> wall = new LinkedList<>();
        boolean[][] map = new boolean[8][8];

        for(int i = 0; i < 8; i++) {
            String str = br.readLine();
            for(int j = 0; j < 8; j++) {
                if(str.charAt(j) == '#') {
                    wall.add(i * 8 + j);
                    map[i][j] = true;
                }
            }
        }

        System.out.println(bfs(map, wall));
    }
}
