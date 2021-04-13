package BOJ.BackTracking.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
//Logic
//그냥 백트레킹으로 풀면 되는 문제
//일일히 하나씩 넣어보면서 조건 체크 => 백트레킹
//풀이 시간 : 40분

//더 빠른 코드가 있으니까 참고해보자

//public class Main {
//    private static int[][] sudoku;
//    static List<Pair2239> list;
//    static int zero = 0;
//    private static int[] row;
//    private static int[] col;
//    private static int[][] box;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        list = new ArrayList<Pair2239>();
//
//        sudoku = new int[9][9];
//        row = new int[9];
//        col = new int[9];
//        box = new int[3][3];
//
//        for (int i = 0; i < 9; i++) {
//            String str = br.readLine();
//            for (int j = 0; j < 9; j++) {
//                int tmp = (int) (str.charAt(j) - '0');
//                sudoku[i][j] = tmp;
//                if (tmp == 0) {
//                    list.add(new Pair2239(i, j));
//                    zero++;
//                } else {
//                    row[i] |= (1 << tmp);
//                    col[j] |= (1 << tmp);
//                    box[i / 3][j / 3] |= (1 << tmp);
//                }
//            }
//        }
//
//        playSudoku(0);
//
//    }
//
//    private static void playSudoku(int cnt) {
//
//        if (cnt == zero) {
//            for (int i = 0; i < 9; i++) {
//                for (int j = 0; j < 9; j++) {
//                    System.out.print(sudoku[i][j]);
//                }
//                System.out.println();
//            }
//            System.exit(0);
//        }
//
//        Pair2239 pair = list.get(cnt);
//
//        for (int i = 1; i <= 9; i++) {
//            if ((row[pair.x] & (1 << i)) == 0 && (col[pair.y] & (1 << i)) == 0
//                    && (box[pair.x / 3][pair.y / 3] & (1 << i)) == 0) {
//                row[pair.x] |= (1 << i);
//                col[pair.y] |= (1 << i);
//                box[pair.x / 3][pair.y / 3] |= (1 << i);
//                sudoku[pair.x][pair.y] = i;
//                playSudoku(cnt + 1);
//                row[pair.x] ^= (1 << i);
//                col[pair.y] ^= (1 << i);
//                box[pair.x / 3][pair.y / 3] ^= (1 << i);
//            }
//        }
//    }
//
//    static class Pair2239 {
//        int x, y;
//
//        public Pair2239(int x, int y) {
//            super();
//            this.x = x;
//            this.y = y;
//        }
//
//    }
//}
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
