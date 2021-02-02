package SWEA.D4;

import java.io.*;
import java.util.StringTokenizer;

public class Ladder {
    private static final int TestCase = 10;
    private static int[][] map = new int[100][100];
    private static final int[] dir_row = {0, 0, -1}; //도착지점부터 역으로 올라가기에 좌, 우 먼저 탐색하고 갈 수 있으면 해당 방향으로
    private static final int[] dir_col = {-1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;

        for(int tc = 0; tc < TestCase; tc++) {
            int targetRow = 99;
            int targetCol = 99;
            stk = new StringTokenizer(br.readLine());//테케 번호 추출

            for(int row = 0; row < map.length; row++) {
                stk = new StringTokenizer(br.readLine());//사다리 정보 한줄씩 추출
                for(int col = 0; col < map[row].length; col++) {
                    if(stk.hasMoreTokens())
                        map[row][col] = Integer.parseInt(stk.nextToken());
                    else {
                        stk = new StringTokenizer(br.readLine());
                        map[row][col] = Integer.parseInt(stk.nextToken());
                    }
                    if(map[row][col] == 2) {
                        targetCol = col;
                    }
                }
            }
            map[targetRow][targetCol] = 0;
            int answer = reverseTracking(targetRow, targetCol);
            sb.append("#" + (tc + 1) + " ").append(answer).append("\n");
        }

        bw.write(String.valueOf(sb));
        bw.close();
        br.close();
    }
    private static int reverseTracking(int startRow, int startCol) {
        int targetRow = 0;
        int targetCol;

        loop:while(true) {
            for(int i = 0; i < dir_col.length; i++) {
                int nr = startRow + dir_row[i];
                int nc = startCol + dir_col[i];

                // 배열 범위 넘어가면 스킵
                if(nr < 0 || nc < 0 || nr >= map.length || nc >= map.length) continue;
                //해당 위치에 사다리가 없으면 스킵
                if(map[nr][nc] == 0) continue;

                //시작점에 도달하면 while loop 종료
                if(nr == targetRow) {
                    targetCol = nc;
                    break loop;
                } else if(map[nr][nc] == 1){
                    //현재 위치에서 좌, 우 => 우선적으로 선택, 상은 차선책으로 선택해서 갈 수 있는 경로 존재하면
                    //이전에 있던 곳은 0으로 바꿔주고 ( 중복 탐색 방지 ) 해당 방향으로 진행
                    map[startRow][startCol] = 0;
                    startCol = nc;
                    startRow = nr;
                    break;
                }
            }
        }
        return targetCol;
    }
}
