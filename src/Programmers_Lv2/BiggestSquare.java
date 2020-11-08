package Programmers_Lv2;

public class BiggestSquare {
    public static void main(String[] args) {

    }
    private static class Solution {
        static int answer = Integer.MIN_VALUE;
        // static boolean[][] visited;
        public int solution(int [][]board) {
            // visited = new boolean[board.length][board[0].length];

            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[i].length; j++) {
                    if(board[i][j] == 1) {
                        int length = getLength(i, j, board);
                        if(length != -1) {
                            // changeVisit(i, j, length);
                            answer = Math.max(answer, length * length);
                        }
                    }
                }
            }
            return answer;
        }
//     public void changeVisit(int x, int y, int length) {
//         for(int i = x; i < x + length; i++) {
//             for(int j = y; j < y + length; j++) {
//                 visited[i][j] = true;
//             }
//         }

        //         return;
//     }
        public int getLength(int x, int y, int[][] board) {
            int lengthX = 0;
            int lengthY = 0;

            boolean isFullWith = true;

            for(int i = x; i < board.length; i++) {
                if(board[i][y] != 1) break;
                lengthX++;
            }
            for(int i = y; i < board[x].length; i++) {
                if(board[x][i] != 1) break;
                lengthY++;
            }

            if(lengthX == lengthY && lengthX != 0 && lengthY != 0) {
                loop:for(int i = x; i < x + lengthX; i++) {
                    for(int j = y; j < y + lengthY; j++) {
                        if(board[i][j] != 1) {
                            isFullWith = false;
                            break loop;
                        }
                    }
                }
                if(isFullWith)
                    return lengthX;
                else return -1;
            } else {
                return -1;
            }
        }
    }
}
