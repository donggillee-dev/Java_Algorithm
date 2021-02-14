package BOJ.IMP.Gold;
import java.io.*;
import java.util.*;

public class boj_17822 {
    static int[][] Plate;
    static int[][] RotateArr;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[][] Removed;
    static int N, M, T;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        T = Integer.parseInt(stk.nextToken());
        Plate = new int[N + 1][M + 1];
        Removed = new boolean[N + 1][M + 1];
        RotateArr = new int[T][3];

        for(int i = 1; i <= N; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                Plate[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        for(int i = 0; i < T; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                RotateArr[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        for(int i = 0; i < T; i++) {
            Rotate(RotateArr[i]);
        }
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                if(Plate[i][j] != 0) {
                    ans += Plate[i][j];
                }
            }
        }
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void Rotate(int[] arr) {
        int x = arr[0];
        int d = arr[1];
        int k = arr[2];
        int sum = 0, cnt = 0;
        float avg = 0;
        boolean flag = true;
        boolean[] TmpRemoved = new boolean[M + 1];
        int[] TmpPlate = new int[M + 1];

        for(int i = 1; i <= N; i++) {
            if(i % x == 0) {
                for(int j = 1; j <= M; j++) {
                    TmpPlate[j] = Plate[i][j];
                    TmpRemoved[j] = Removed[i][j];
                }

                if(d == 0) {//시계 방향
                    int idx = 1;
                    int copy_idx = idx + (k % M);

                    TmpPlate[copy_idx] = Plate[i][idx];
                    TmpRemoved[copy_idx] = Removed[i][idx];
                    copy_idx++;

                    for(int j = idx + 1; j <= M; j++) {
                        if(copy_idx > M) copy_idx = 1;
                        TmpPlate[copy_idx] = Plate[i][j];
                        TmpRemoved[copy_idx] = Removed[i][j];
                        copy_idx++;
                    }
                    for(int j = 1; j <= M; j++) {
                        Plate[i][j] = TmpPlate[j];
                        Removed[i][j] = TmpRemoved[j];
                    }
                } else if(d == 1){//반시계 방향
                    int idx = M;
                    int copy_idx = idx - (k % M);

                    TmpPlate[copy_idx] = Plate[i][idx];
                    TmpRemoved[copy_idx] = Removed[i][idx];
                    copy_idx--;

                    for(int j = idx - 1; j > 0; j--) {
                        if(copy_idx < 1) copy_idx = M;
                        TmpPlate[copy_idx] = Plate[i][j];
                        TmpRemoved[copy_idx] = Removed[i][j];
                        copy_idx--;
                    }
                    for(int j = 1; j <= M; j++) {
                        Plate[i][j] = TmpPlate[j];
                        Removed[i][j] = TmpRemoved[j];
                    }
                }
            }
        }
        flag = Remove();
        if(!flag) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= M; j++) {
                    if(Plate[i][j] != 0) {
                        sum += Plate[i][j];
                        cnt++;
                    }
                }
            }
            if(cnt > 0) {
                avg = (sum / (float)cnt);
                for(int i = 1; i <= N; i++) {
                    for(int j = 1; j <= M; j++) {
                        if(Plate[i][j] != 0 && Plate[i][j] < avg) {
                            Plate[i][j]++;
                        } else if(Plate[i][j] != 0 && Plate[i][j] > avg)
                            Plate[i][j]--;
                        else;
                    }
                }
            }
        }
    }
    private static boolean Remove() {
        int dir_x;
        int dir_y;
        boolean flag = false;

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                if(Removed[i][j] || Plate[i][j] == 0) continue;

                for(int dir = 0; dir < 4; dir++) {
                    dir_x = i + dx[dir];
                    dir_y = j + dy[dir];

                    if(dir_x < 1 || dir_x > N) continue;

                    if(dir_y == 0) dir_y = M;
                    else if(dir_y == M + 1) dir_y = 1;
                    else;

                    if(Plate[i][j] == Plate[dir_x][dir_y]) {
                        DFS(i, j, Plate[i][j]);

                        flag = true;
                    }
                }
            }
        }
        return flag;//지워진게 있으면 true반환 없으면 false반
    }
    private static void DFS(int ith_plate, int jth_number, int number) {
        if(Removed[ith_plate][jth_number] || Plate[ith_plate][jth_number] == 0) return;
        if(Plate[ith_plate][jth_number] == number) {
            Removed[ith_plate][jth_number] = true;
            Plate[ith_plate][jth_number] = 0;
        }
        for(int dir = 0; dir < 4; dir++) {
            int dir_x = dx[dir] + ith_plate;
            int dir_y = dy[dir] + jth_number;
            if(dir_x < 1 || dir_x > N) continue;

            if(dir_y == 0) dir_y = M;
            else if(dir_y == M + 1) dir_y = 1;
            else;
            if(Plate[dir_x][dir_y] == 0) continue;

            if(Plate[dir_x][dir_y] == number) {
                if(!Removed[dir_x][dir_y])
                    DFS(dir_x, dir_y, number);
            }
        }
    }
}

