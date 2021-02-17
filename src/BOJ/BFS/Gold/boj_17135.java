package BOJ.BFS.Gold;

import java.io.*;
import java.util.*;

public class boj_17135 {
    private static class PosInfo {
        int row, col, dis;
        public PosInfo(int row, int col, int dis) {
            this.row = row;
            this.col = col;
            this.dis = dis;
        }
    }
    private static int N, M, D, answer;
    private static PosInfo[] ArcherPos;
    private static char[][] Map, cpMap;
    private static ArrayList<PosInfo> ArcherList = new ArrayList<>();
    private static int[] dir_x = {0, -1, 0};
    private static int[] dir_y = {-1, 0, 1};
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        D = Integer.parseInt(stk.nextToken());
        ArcherPos = new PosInfo[3];
        Map = new char[N + 1][M];
        cpMap = new char[N + 1][M];
        for(int i = 0; i < M; i++) {
            ArcherList.add(new PosInfo(N, i, 0));
        }

        for(int i = 0; i < N; i++) {
            sb.append(br.readLine());
            for(int j = 0, idx = 0; j < M; j++, idx += 2) {
                Map[i][j] = sb.charAt(idx);
            }
            sb.setLength(0);
        }
        for(int i = 0; i < M - 2; i++) {
            for(int j = i + 1; j < M - 1; j++) {
                for(int k = j + 1; k < M; k++) {
                    attack(i, j, k);
                }
            }
        }

        System.out.println(answer);
    }
    private static int BFS() {
        Queue<PosInfo> Q = new LinkedList<>();
        ArrayList<PosInfo> enemy = new ArrayList<>();
        int ret = 0;
        for(int i = 0; i < ArcherPos.length; i++) {
            PosInfo tmp = ArcherPos[i];

            Q.add(new PosInfo(tmp.row, tmp.col, 0));

            loop:while(!Q.isEmpty()) {
                tmp = Q.poll();

                for(int dir = 0; dir < 3; dir++) {
                    int nr = tmp.row + dir_x[dir];
                    int nc = tmp.col + dir_y[dir];
                    if(nr < 0 || nc < 0 || nc >= M || tmp.dis >= D) continue;
                    if(cpMap[nr][nc] == '1') {
                        enemy.add(new PosInfo(nr, nc, 0));
                        break loop;
                    }
                    Q.add(new PosInfo(nr, nc, tmp.dis + 1));
                }
            }
            Q.clear();
        }

        for(int i = 0; i < enemy.size(); i++) {
            if(cpMap[enemy.get(i).row][enemy.get(i).col] == '1') {
                ret++;
                cpMap[enemy.get(i).row][enemy.get(i).col] = '\0';
            }
        }
        return ret;
    }
    private static void attack(int a1, int a2, int a3) {
        reset();
        ArcherPos[0] = ArcherList.get(a1);
        ArcherPos[1] = ArcherList.get(a2);
        ArcherPos[2] = ArcherList.get(a3);
        int sum = 0;
        for(int i = 0; i < N; i++) {
            sum += BFS();
            int row = ArcherPos[0].row;
            Arrays.fill(cpMap[row - 1], '\0');
            ArcherPos[0].row--;
            ArcherPos[1].row--;
            ArcherPos[2].row--;
        }

        if(sum > answer) answer = sum;
    }
    private static void reset() {
        for(int i = 0; i < M; i++) {
            ArcherList.get(i).row = N;
        }
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                cpMap[i][j] = Map[i][j];
            }
        }
    }
}
