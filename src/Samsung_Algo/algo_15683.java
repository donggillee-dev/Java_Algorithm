package Samsung_Algo;
import java.io.*;
import java.util.*;

public class algo_15683 {
    static int ans = Integer.MAX_VALUE, N, M, Cnt;
    static int[] dir_x = {0, 1, 0, -1};
    static int[] dir_y = {1, 0, -1, 0};
    static int[][] OfficeMap;
    static CCInfo[] CctvArr = new CCInfo[8];
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        OfficeMap = new int[N][M];
        int[][] CopyMap = new int[N][M];
        Cnt = 0;
        for(int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                int data = Integer.parseInt(stk.nextToken());

                if(data == 5) {
                    CctvArr[Cnt] = new CCInfo(i, j, data, 1, 4);
                    CctvArr[Cnt].dir[0] = 0;
                    CctvArr[Cnt].dir[1] = 1;
                    CctvArr[Cnt].dir[2] = 2;
                    CctvArr[Cnt].dir[3] = 3;
                } else if(data == 4) {
                    CctvArr[Cnt] = new CCInfo(i, j, data, 4, 3);
                    CctvArr[Cnt].dir[0] = 0;
                    CctvArr[Cnt].dir[1] = 2;
                    CctvArr[Cnt].dir[2] = 3;
                } else if(data == 3) {
                    CctvArr[Cnt] = new CCInfo(i, j, data, 4, 2);
                    CctvArr[Cnt].dir[0] = 3;
                    CctvArr[Cnt].dir[1] = 0;
                } else if(data == 2) {
                    CctvArr[Cnt] = new CCInfo(i, j, data, 2, 2);
                    CctvArr[Cnt].dir[0] = 0;
                    CctvArr[Cnt].dir[1] = 2;
                } else if(data == 1) {
                    CctvArr[Cnt] = new CCInfo(i, j, data, 4, 1);
                    CctvArr[Cnt].dir[0] = 0;
                } else;

                if(data <= 5 && data >= 1) Cnt++;
                CopyMap[i][j] = data;
                OfficeMap[i][j] = data;
            }
        }
//        System.out.println(Cnt);
        Rotate(0, CopyMap);
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void Rotate(int depth, int[][] arr) {
        if(depth == Cnt) {
            Check(arr);
//            Init();
            //여기서 사각지대 체크
        } else {
            CCInfo tmp = CctvArr[depth];

            if(tmp.num == 1) {
                int dir = tmp.dir[0];
                int x = tmp.x;
                int y = tmp.y;

                for(int i = 0; i < tmp.max; i++) {
                    int[][] copyarr = new int[N][M];
                    for(int j = 0; j < N; j++) {
                        System.arraycopy(arr[j], 0, copyarr[j], 0, M);
                    }
                    int nx = x + dir_x[dir];
                    int ny = y + dir_y[dir];
                    while(true) {
                        if (nx < 0 || ny < 0 || nx >= N || ny >= M) break;
                        if (copyarr[nx][ny] == 6) break;
                        copyarr[nx][ny] = -1;
                        nx += dir_x[dir];
                        ny += dir_y[dir];
                    }
                    dir++;
                    Rotate(depth + 1, copyarr);
                }
            } else if(tmp.num == 2) {
                int dir1 = tmp.dir[0];
                int dir2 = tmp.dir[1];
                int x = tmp.x;
                int y = tmp.y;

                for(int i = 0; i < tmp.max; i++) {
                    int nx = x + dir_x[dir1];
                    int ny = y + dir_y[dir1];
                    int[][] copyarr = new int[N][M];
                    for(int j = 0; j < N; j++) {
                        System.arraycopy(arr[j], 0, copyarr[j], 0, M);
                    }
                    while(true) {
                        if (nx < 0 || ny < 0 || nx >= N || ny >= M) break;
                        if (copyarr[nx][ny] == 6) break;
                        copyarr[nx][ny] = -1;
                        nx += dir_x[dir1];
                        ny += dir_y[dir1];
                    }

                    nx = x + dir_x[dir2];
                    ny = y + dir_y[dir2];

                    while(true) {
                        if (nx < 0 || ny < 0 || nx >= N || ny >= M) break;
                        if (copyarr[nx][ny] == 6) break;
                        copyarr[nx][ny] = -1;
                        nx += dir_x[dir2];
                        ny += dir_y[dir2];
                    }

                    dir1++;
                    dir2++;
                    Rotate(depth + 1, copyarr);
                }
            } else if(tmp.num == 3) {
                int dir1 = tmp.dir[0];
                int dir2 = tmp.dir[1];
                int x = tmp.x;
                int y = tmp.y;

                for(int i = 0; i < tmp.max; i++) {
                    int nx = x + dir_x[dir1];
                    int ny = y + dir_y[dir1];
                    int[][] copyarr = new int[N][M];
                    for(int j = 0; j < N; j++) {
                        System.arraycopy(arr[j], 0, copyarr[j], 0, M);
                    }
                    while(true) {
                        if (nx < 0 || ny < 0 || nx >= N || ny >= M) break;
                        if (copyarr[nx][ny] == 6) break;
                        copyarr[nx][ny] = -1;
                        nx += dir_x[dir1];
                        ny += dir_y[dir1];
                    }

                    nx = x + dir_x[dir2];
                    ny = y + dir_y[dir2];

                    while(true) {
                        if (nx < 0 || ny < 0 || nx >= N || ny >= M) break;
                        if (copyarr[nx][ny] == 6) break;
                        copyarr[nx][ny] = -1;
                        nx += dir_x[dir2];
                        ny += dir_y[dir2];
                    }

                    dir1++;
                    dir2++;
                    if(dir1 > 3) dir1 = 0;
                    if(dir2 > 3) dir2 = 0;
                    Rotate(depth + 1, copyarr);
                }
            } else if(tmp.num == 4) {
                int dir1 = tmp.dir[0];
                int dir2 = tmp.dir[1];
                int dir3 = tmp.dir[2];
                int x = tmp.x;
                int y = tmp.y;
                for(int i = 0; i < tmp.max; i++) {
                    int nx = x + dir_x[dir1];
                    int ny = y + dir_y[dir1];
                    int[][] copyarr = new int[N][M];
                    for(int j = 0; j < N; j++) {
                        System.arraycopy(arr[j], 0, copyarr[j], 0, M);
                    }
                    while(true) {
                        if (nx < 0 || ny < 0 || nx >= N || ny >= M) break;
                        if (copyarr[nx][ny] == 6) break;
                        copyarr[nx][ny] = -1;
                        nx += dir_x[dir1];
                        ny += dir_y[dir1];
                    }

                    nx = x + dir_x[dir2];
                    ny = y + dir_y[dir2];

                    while(true) {
                        if (nx < 0 || ny < 0 || nx >= N || ny >= M) break;
                        if (copyarr[nx][ny] == 6) break;
                        copyarr[nx][ny] = -1;
                        nx += dir_x[dir2];
                        ny += dir_y[dir2];
                    }

                    nx = x + dir_x[dir3];
                    ny = y + dir_y[dir3];

                    while(true) {
                        if (nx < 0 || ny < 0 || nx >= N || ny >= M) break;
                        if (copyarr[nx][ny] == 6) break;
                        copyarr[nx][ny] = -1;
                        nx += dir_x[dir3];
                        ny += dir_y[dir3];
                    }

                    dir1++;
                    dir2++;
                    dir3++;

                    if(dir1 > 3) dir1 = 0;
                    if(dir2 > 3) dir2 = 0;
                    if(dir3 > 3) dir3 = 0;
                    Rotate(depth + 1, copyarr);
                }
            } else if(tmp.num == 5) {
                int dir1 = tmp.dir[0];
                int dir2 = tmp.dir[1];
                int dir3 = tmp.dir[2];
                int dir4 = tmp.dir[3];
                int x = tmp.x;
                int y = tmp.y;
                int nx = x + dir_x[dir1];
                int ny = y + dir_y[dir1];
                int[][] copyarr = new int[N][M];
                for(int j = 0; j < N; j++) {
                    System.arraycopy(arr[j], 0, copyarr[j], 0, M);
                }
                while(true) {
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M) break;
                    if (copyarr[nx][ny] == 6) break;
                    copyarr[nx][ny] = -1;
                    nx += dir_x[dir1];
                    ny += dir_y[dir1];
                }

                nx = x + dir_x[dir2];
                ny = y + dir_y[dir2];

                while(true) {
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M) break;
                    if (copyarr[nx][ny] == 6) break;
                    copyarr[nx][ny] = -1;
                    nx += dir_x[dir2];
                    ny += dir_y[dir2];
                }

                nx = x + dir_x[dir3];
                ny = y + dir_y[dir3];

                while(true) {
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M) break;
                    if (copyarr[nx][ny] == 6) break;
                    copyarr[nx][ny] = -1;
                    nx += dir_x[dir3];
                    ny += dir_y[dir3];
                }

                nx = x + dir_x[dir4];
                ny = y + dir_y[dir4];

                while(true) {
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M) break;
                    if (copyarr[nx][ny] == 6) break;
                    copyarr[nx][ny] = -1;
                    nx += dir_x[dir4];
                    ny += dir_y[dir4];
                }

                Rotate(depth + 1, copyarr);
            } else;
        }
    }
    private static void Check(int[][] arr) {
        int cnt = 0;
        for(int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(arr[i]));
            for(int j = 0; j < M; j++) {
                if(arr[i][j] == 0) cnt++;
            }
        }
//        System.out.println();
        if(cnt < ans) ans = cnt;
    }
//    private static void Init() {
//        for(int i = 0; i < N; i++) {
//            System.arraycopy(OfficeMap[i], 0, CopyMap[i], 0, M);
//        }
//    }
}
class CCInfo {
    int x;
    int y;
    int num;
    int max;
    int[] dir;
    public CCInfo(int nx, int ny, int N, int M, int way) {
        this.x = nx;
        this.y = ny;
        this.num = N;
        this.max = M;
        this.dir = new int[way];
    }
}