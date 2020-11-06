package Programmers_Lv2;
import java.util.*;
public class KakFriColoringBook {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[][] picture = {
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                {0, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 3, 3, 3, 1, 1, 1, 1, 1, 1, 3, 3, 3, 1, 0},
                {0, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}
        };
        int m = 13;
        int n = 16;
        int[] ans = sol.solution(m, n, picture);

        System.out.println(Arrays.toString(ans));

        return;
    }
    private static class Solution {
        static List<Integer> list;
        static boolean[][] visited;
        static int[] pos_x = {1, 0, -1, 0};
        static int[] pos_y = {0, 1, 0, -1};
        static int areaSize;
        public int[] solution(int m, int n, int[][] picture) {
            int numberOfArea = 0;
            int maxSizeOfOneArea = 0;
            list = new LinkedList<>();
            areaSize = 1;
            visited = new boolean[m][n];

            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++) {
                    if(!visited[i][j] && picture[i][j] != 0) {
                        areaSize = 1;
                        visited[i][j] = true;
                        BFS(picture, m, n, i, j, picture[i][j]);
                        list.add(areaSize);
                    }
                }
            }

            for(int i = 0; i < list.size(); i++) {
                int num = list.get(i);

                if(num > maxSizeOfOneArea) {
                    maxSizeOfOneArea = num;
                }
            }

            int[] answer = new int[2];
            answer[0] = list.size();
            answer[1] = maxSizeOfOneArea;
            return answer;
        }
        public void BFS(int[][] picture, int m, int n, int x, int y, int areaCate) {
            Queue<posInfo> Q = new LinkedList<>();
            Q.offer(new posInfo(x, y));

            while(!Q.isEmpty()) {
                posInfo inf = Q.poll();

                for(int i = 0; i < 4; i++) {
                    int nx = inf.x + pos_x[i];
                    int ny = inf.y + pos_y[i];
                    if(nx < 0 || ny < 0 || nx >= m || ny >= n || picture[nx][ny] == 0) continue;

                    if(!visited[nx][ny] && picture[nx][ny] == areaCate) {
                        visited[nx][ny] = true;
                        areaSize++;
                        Q.offer(new posInfo(nx, ny));
                    }
                }
            }
            Q.clear();
            return;
        }
        class posInfo {
            int x;
            int y;
            public posInfo(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
    }
}
