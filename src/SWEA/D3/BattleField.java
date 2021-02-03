package SWEA.D3;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BattleField {
    private static HashMap<Character, dirInfo> moveHash = new HashMap<>();
    private static HashMap<Character, Integer> dirHash = new HashMap<>();
    private static final int[] dir_x = {-1, 1, 0, 0};
    private static final int[] dir_y = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringBuilder answerSb = new StringBuilder();
        int TestCase = Integer.parseInt(br.readLine());

        //전차의 방향과 행동을 저장한 HashMap에 상하좌우 값 세팅
        moveHash.put('U', new dirInfo('^', 0));
        moveHash.put('D', new dirInfo('v', 1));
        moveHash.put('L', new dirInfo('<', 2));
        moveHash.put('R', new dirInfo('>', 3));
        dirHash.put('^', 0);
        dirHash.put('v', 1);
        dirHash.put('<', 2);
        dirHash.put('>', 3);

        for(int tc = 0; tc < TestCase; tc++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int tank_row = 0, tank_col = 0;
            int h = Integer.parseInt(stk.nextToken());
            int w = Integer.parseInt(stk.nextToken());
            char[][] map = new char[h][w];
            int N = 0;
            int cur_dir = 0;

            //데이터 입력
            for(int row = 0; row < h; row++) {
                sb.append(br.readLine());
                for(int col = 0; col < w; col++) {
                    map[row][col] = sb.charAt(col);
                    if(map[row][col] == '<' || map[row][col] == '^' || map[row][col] == '>' || map[row][col] == 'v') {
                        tank_row = row;
                        tank_col = col;
                        cur_dir = dirHash.get(map[row][col]);
                    }
                }
                sb.delete(0, sb.length());
            }

            //탱크 행동 정보 입력
            N = Integer.parseInt(br.readLine());
            sb.append(br.readLine());

            for(int i = 0; i < N; i++) {
                char ch = sb.charAt(i);

                //슈팅이 아닌경우
                if(ch != 'S') {
                    dirInfo inf = moveHash.get(ch);

                    //탱크 방향 변경
                    map[tank_row][tank_col] = inf.dir;
                    cur_dir = inf.dir_idx;

                    int nx = tank_row + dir_x[inf.dir_idx];
                    int ny = tank_col + dir_y[inf.dir_idx];

                    //장외 이거나 장애물이 있는 경우는 이동 불가
                    if(nx < 0 || ny < 0 || nx >= h || ny >= w) continue;
                    if(map[nx][ny] == '*' || map[nx][ny] == '#' || map[nx][ny] == '-') continue;

                    //이동 가능한 경우 변경
                    map[nx][ny] = map[tank_row][tank_col];
                    map[tank_row][tank_col] = '.';
                    tank_row = nx;
                    tank_col = ny;
                } else {
                    int mi_row = tank_row;
                    int mi_col = tank_col;
                    while(true) {
                        int nx = mi_row + dir_x[cur_dir];
                        int ny = mi_col + dir_y[cur_dir];

                        //포탄이 장외되거나 강철을 만나면 종료
                        if(nx < 0 || ny < 0 || nx >= h || ny >= w || map[nx][ny] == '#') break;

                        //포탄이 벽돌을 만나면 파괴하고 평지로 만들고 종료
                        if(map[nx][ny] == '*') {
                            map[nx][ny] = '.';
                            break;
                        }
                        mi_row = nx;
                        mi_col = ny;
                    }
                }
            }
            sb.delete(0, sb.length());

            answerSb.append("#" + (tc + 1) + " ");
            for(int r = 0; r < h; r++) {
                for(int c = 0; c < w; c++) {
                    answerSb.append(map[r][c]);
                }
                answerSb.append("\n");
            }
        }

        bw.write(String.valueOf(answerSb));
        bw.close();
        br.close();
    }
    private static class dirInfo {
        char dir;
        int dir_idx;

        public dirInfo(char dir, int dir_idx) {
            this.dir = dir;
            this.dir_idx = dir_idx;
        }
    }
}
