package BOJ.BackTracking.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
//Logic
//그냥 백트레킹으로 풀면 되는 문제
//일일히 하나씩 넣어보면서 조건 체크 => 백트레킹
//풀이 시간 : 40분
public class boj_2239 {
    private static boolean possRow(int r) {
        boolean[] visited = new boolean[10];

        for(int i = 0; i < 9; i++) {
            if(puzzle[r][i] == 0) continue;

            if(visited[puzzle[r][i]]) return false;
            visited[puzzle[r][i]] = true;
        }

        return true;
    }
    private static boolean possCol(int c) {
        boolean[] visited = new boolean[10];

        for(int i = 0; i < 9; i++) {
            if(puzzle[i][c] == 0) continue;

            if(visited[puzzle[i][c]]) return false;
            visited[puzzle[i][c]] = true;
        }

        return true;
    }
    private static boolean possArea(int r, int c) {
        int start_row = (r / 3) * 3, start_col = (c / 3) * 3;
        boolean[] visited = new boolean[10];

        for(int i = start_row; i < start_row + 3; i++) {
            for(int j = start_col; j < start_col + 3; j++) {
                if(puzzle[i][j] == 0) continue;

                if(visited[puzzle[i][j]]) return false;
                visited[puzzle[i][j]] = true;
            }
        }

        return true;
    }
    private static void rec(int idx, int length, ArrayList<int[]> list) {
        if(flag) return;
        if(idx == length) {
            if(!flag) {
                for(int i = 0; i < 9; i++) {
                    for(int j = 0; j < 9; j++) {
                        ans[i][j] = puzzle[i][j];
                    }
                }
                flag = true;
            }
            return;
        }

        int[] elem = list.get(idx);
        int row = elem[0], col = elem[1];
        int tmp = puzzle[row][col];
        for(int num = 1; num <= 9; num++) {
            puzzle[row][col] = num;
            if(possArea(row, col) && possCol(col) && possRow(row)) {
                rec(idx + 1, length, list);
            }
        }
        puzzle[row][col] = tmp;
        return;
    }
    private static boolean flag = false;
    private static int[][] puzzle = new int[9][9];
    private static int[][] ans = new int[9][9];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        ArrayList<int[]> list = new ArrayList<>();

        for(int i = 0; i < 9; i++) {
            String str = br.readLine();
            for(int j = 0; j < 9; j++) {
                puzzle[i][j] = str.charAt(j) - '0';

                if(puzzle[i][j] == 0) {
                    list.add(new int[]{i, j});
                }
            }
        }
        rec(0, list.size(), list);
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                sb.append(ans[i][j]);
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
