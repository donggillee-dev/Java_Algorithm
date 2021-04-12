package Programmers.Programmers_Lv2;

import java.util.HashMap;
//Logic
//좌표로 문제가 설정되어있기에 배열로 방문처리를 하기가 까다로웠다
//출발 위치 도착위치를 가지고 배열을 만들면 되겠다고 간단히 생각해서 풀 수 있었던 문제
//하지만 양방향이기에 도착지에 대해서 출발지에 대한 방문처리도 해주어야함
public class VisitedLength {
    private static class Solution {
        private static int solution(String dirs) {
            int answer = 0, length = dirs.length();
            int start_row = 5, start_col = 5;
            int[] dir_r = {-1, 0, 1, 0}, dir_c = {0, -1, 0, 1};
            boolean[][][][] visited = new boolean[11][11][11][11];
            HashMap<Character, Integer> dirHash = new HashMap<>();
            dirHash.put('U', 0);
            dirHash.put('L', 1);
            dirHash.put('D', 2);
            dirHash.put('R', 3);

            for(int i = 0; i < length; i++) {
                int dir = dirHash.get(dirs.charAt(i));
                int nr = start_row + dir_r[dir];
                int nc = start_col + dir_c[dir];

                if(nr < 0 || nc < 0 || nr > 10 || nc > 10) continue;


                if(!visited[start_row][start_col][nr][nc]) {
                    visited[start_row][start_col][nr][nc] = true;
                    visited[nr][nc][start_row][start_col] = true;
                    answer++;
                }

                start_row = nr;
                start_col = nc;
            }
            return answer;
        }
    }
    public static void main(String[] args) {
        String dirs = "ULURRDLLU";
        System.out.println(Solution.solution(dirs));
    }
}
