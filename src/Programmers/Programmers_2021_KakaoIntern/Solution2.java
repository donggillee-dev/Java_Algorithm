package Programmers.Programmers_2021_KakaoIntern;

import java.util.*;

public class Solution2 {
    public static void main(String[] args) {
        solution(new String[][]{
                {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPXX", "OXXXP", "POOXX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
        });
    }
    private static int[] solution(String[][] places) {
        int[] answer = new int[5];
        int[] dir_x = {-1, 0, 1, 0};
        int[] dir_y = {0, -1, 0, 1};

        for(int room = 0; room < 5; room++) { //각 대기실에 대해서 반복문
            answer[room] = 1; //일단 해당 방의 거리두기가 되어있다고 가정
            loop:for(int i = 0; i < 5; i++) { //자리가 놓여져있는 방향대로 2중 for문
                for(int j = 0; j < 5; j++) {
                    if(places[room][i].charAt(j) == 'P') { //사람이 앉아있는 곳이라면, 해당 위치부터 다른 사람 사이의 거리를 구하기 위해 BFS
                        //각 사람에 대해 모든 사람끼리의 최소거리를 구했을때 거리두기 여부를 구해야한다
                        //그러므로 사람 확인될때마다 BFS
                        Queue<int[]> q = new LinkedList<>();
                        boolean[][] visited = new boolean[5][5];
                        q.add(new int[]{i, j, 0});
                        visited[i][j] = true;

                        while(!q.isEmpty()) {
                            int[] elem = q.poll();

                            for(int dir = 0; dir < 4; dir++) {
                                int nr = elem[0] + dir_x[dir];
                                int nc = elem[1] + dir_y[dir];

                                if(nr < 0 || nc < 0 || nr >= 5 || nc >= 5) continue; //배열 범위 초과
                                if(places[room][nr].charAt(nc) == 'X') continue; //해당 위치 파티션 놓여져 있으면 스킵

                                if(!visited[nr][nc]) {//방문 되어있지 않다면은 방문
                                    visited[nr][nc] = true;

                                    if(places[room][nr].charAt(nc) == 'P' && elem[2] + 1 <= 2) {
                                        //방문하러 들어갔는데 사람이 앉아있으며, 거리두기 안되어 있는 경우에는 반복문 탈출, answer배열 0으로 세팅
                                        answer[room] = 0;
                                        break loop;
                                    }
                                    //그렇지 않은 경우에는 계속 BFS돈다
                                    q.add(new int[]{nr, nc, elem[2] + 1});
                                }
                            }
                        }
                    }
                }
            }
        }
        return answer;
    }
}
