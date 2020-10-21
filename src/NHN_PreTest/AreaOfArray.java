package NHN_PreTest;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class AreaOfArray {
    private static int answer;
    private static List<Integer> sizeOfArea = new ArrayList<>();
    private static int[] dir_x = {0, 1, 0, -1};
    private static int[] dir_y = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        int N = 6;
        int[][] matrix = {
                {0, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 1},
                {0, 0, 0, 0, 1, 1},
                {0, 0, 0, 0, 1, 1},
                {1, 1, 0, 0, 1, 0},
                {1, 1, 1, 0, 0, 0}
        };
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        solution(N, matrix);
        Collections.sort(sizeOfArea);

        sb.append(answer).append("\n");
        for(int i = 0; i < sizeOfArea.size(); i++) {
            sb.append(sizeOfArea.get(i)).append(" ");
        }
        sb.replace(sb.length(), sb.length(), "\n");

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        return;
    }
    private static void solution(int sizeOfMatrix, int[][] matrix) {
        boolean[][] visited = new boolean[sizeOfMatrix][sizeOfMatrix];
        for(int i = 0; i < sizeOfMatrix; i++) {
            Arrays.fill(visited[i], false);
        }

        for(int i = 0; i < sizeOfMatrix; i++) {
            for(int j = 0; j < sizeOfMatrix; j++) {
                if(!visited[i][j] && matrix[i][j] != 0) {
                    answer++;
                    visited[i][j] = true;
                    int size = BFS(i, j, sizeOfMatrix, matrix, visited);
                    sizeOfArea.add(size);
                }
            }
        }

    }
    private static int BFS(int x, int y, int sizeOfMatrix, int[][] matrix, boolean[][] visited) {
        Queue<Info> Q = new LinkedList<>();
        Q.offer(new Info(x, y));
        int size = 0;
        while(!Q.isEmpty()) {
            Info inf = Q.poll();
            size++;
            for(int i = 0; i < 4; i++) {
                int nx = inf.x + dir_x[i];
                int ny = inf.y + dir_y[i];

                if(nx < 0 || ny < 0 || nx >= sizeOfMatrix || ny >= sizeOfMatrix) continue;
                if(visited[nx][ny] || matrix[nx][ny] == 0) continue;

                visited[nx][ny] = true;
                Q.offer(new Info(nx, ny));
            }
        }
        return size;
    }
    private static class Info {
        int x;
        int y;
        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}


