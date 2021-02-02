package Programmers.Programmers_Lv2;

public class BiggestSquare {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] board = {{0,1,1,1},{1,1,1,1},{1,1,1,1},{0,0,1,0}};
        int answer = sol.solution(board);

        System.out.println(answer);

        return;
    }
    private static class Solution {
        public int solution(int [][]board) {
            int answer = Integer.MIN_VALUE;

            for(int i = 0; i < board[0].length; i++) {
                if(board[0][i] == 1) answer = 1;
            }
            for(int i = 1; i < board.length; i++) {
                for(int j = 0; j < board[i].length; j++) {
                    if(j - 1 < 0) continue;
                    if(board[i][j] != 0) {
                        board[i][j] = Math.min(board[i][j - 1], Math.min(board[i-1][j], board[i - 1][j - 1])) + 1;
                        answer = answer < board[i][j] ? board[i][j] : answer;
                    }
                }
            }
            return answer * answer;
        }
    }
}
