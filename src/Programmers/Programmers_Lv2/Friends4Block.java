package Programmers.Programmers_Lv2;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Friends4Block {
    public static void main(String[] args) throws IOException {
        int m = 4;
        int n = 5;
        String[] board = {
                "CCBDE", "AAADE", "AAABF", "CCBBF"
        };
        System.out.println( solution(m, n, board) );
    }
    private static int solution(int m, int n, String[] board) {
        int[] dir_row = {1, 1, 0};
        int[] dir_col = {0, 1, 1};
        int answer = 0;
        char[][] chBoard = new char[m][n];
        Queue<Data> Q = new LinkedList<>();
        Queue<Character> pollQ = new LinkedList<>();

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length(); j++) {
                chBoard[i][j] = board[i].charAt(j);
            }
        }

        while(true) {
            for(int i = 0; i < m - 1; i++) {
                for(int j = 0; j < n - 1; j++) {
                    char ch = chBoard[i][j];
                    int sameCnt = 1;

                    if(ch == '\0') continue;
                    for(int k = 0; k < dir_col.length; k++) {
                        int nr = i + dir_row[k];
                        int nc = j + dir_col[k];

                        if(chBoard[nr][nc] == ch) sameCnt++;
                    }

                    if(sameCnt == 4) {
                        Q.offer(new Data(i, j));

                        for(int k = 0; k < dir_col.length; k++) {
                            int nr = i + dir_row[k];
                            int nc = j + dir_col[k];

                            Q.offer(new Data(nr, nc));
                        }
                    }
                }
            }

            if(Q.isEmpty()) break;
            else {
                while(!Q.isEmpty()) {
                    Data tmp = Q.poll();
                    if(chBoard[tmp.row][tmp.col] != '\0') {
                        answer++;
                        chBoard[tmp.row][tmp.col] = '\0';
                    }
                }
            }
            for(int i = 0; i < n; i++) {
                for(int j = m - 1; j >= 0; j--) {
                    if(chBoard[j][i] == '\0') {
                        for(int k = j - 1; k >= 0; k--) {
                            if(chBoard[k][i] != '\0') {
                                pollQ.offer(chBoard[k][i]);
                                chBoard[k][i] = '\0';
                            }
                        }
                        while(!pollQ.isEmpty()) {
                            chBoard[j][i] = pollQ.poll();
                            j--;
                        }
                        j = 0;
                    }
                }
            }
        }

        return answer;
    }
    private static class Data {
        int row;
        int col;

        public Data(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
