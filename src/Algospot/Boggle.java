package Algospot;

import java.io.*;

public class Boggle {
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static char[][] board = new char[5][5];
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int TestCase = Integer.parseInt(br.readLine());

        for(int T = 0; T < TestCase; T++) {
            for(int i = 0; i < 5; i++) {
                String tmp = br.readLine();
                for(int j = 0; j < 5; j++) {
                    board[i][j] = tmp.charAt(j);
                }
            }

            int N = Integer.parseInt(br.readLine());
            for(int i = 0; i < N; i++) {
                String word = br.readLine();
                boolean ans = dfs1(word, 0);
                if(ans)
                    sb.append(word + " ").append("YES\n");
                else
                    sb.append(word + " " ).append("NO\n");
            }
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    public static boolean dfs1(String word, int depth) {
        boolean[][][] visitedMap = new boolean[5][5][word.length()];

        for(int i = 0; i < 5; ++i) {
            for(int j = 0; j < 5; ++j) {
                if(dfs2(i, j, word, depth, visitedMap))
                    return true;
            }
        }
        return false;
    }
    public static boolean dfs2(int x, int y, String word, int depth, boolean[][][] visitedMap) {
        visitedMap[x][y][depth] = true;

        if(word.charAt(depth) != board[x][y])
            return false;
        if(depth == (word.length() - 1))
            return true;

        for(int i = 0; i < 8; ++i) {
            int dir_x = x + dx[i];
            int dir_y = y + dy[i];

            if(dir_x < 0 || dir_y < 0 || dir_x >= 5 || dir_y >= 5) continue;
            if(visitedMap[dir_x][dir_y][depth + 1]) continue;

            if(dfs2(dir_x, dir_y, word, depth + 1, visitedMap)) return true;
        }
        return false;
    }
}
