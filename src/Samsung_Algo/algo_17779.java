package Samsung_Algo;
import java.io.*;
import java.util.*;

public class algo_17779 {
    static int N;
    static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, ans = Integer.MAX_VALUE;
    static int tot = 0;
    static int[][] Arr;
    static int[][] PartArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        Arr = new int[N + 1][N + 1];
        PartArr = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                Arr[i][j] = Integer.parseInt(stk.nextToken());
                tot += Arr[i][j];
            }
        }
        for(int x = 1; x <= N; x++) {
            for(int y = 1; y <= N; y++) {
                for(int d1 = 1; d1 <= N; d1++) {
                    for(int d2 = 1; d2 <= N; d2++) {
                        if(isPossible(x, y, d1, d2)) {
                            Partition(x, y, d1, d2);
                        }
                    }
                }
            }
        }
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        //x, y에 대해서 d1, d2 유효한지 확인
        //유효하다면 구역을 나눈다
        //나눈 구역들에 대해서 sum1, sum2, sum3, sum5
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static boolean isPossible(int x, int y, int d1, int d2) {
        if(x + d1 > N || x + d1 < 1 || y - d1 < 1 || y - d1 > N) return false;
        if(x + d2 > N || x + d2 < 1 || y + d2 < 1 || y + d2 > N) return false;
        if(x + d1 + d2 > N || x + d1 + d2 < 1 || y - d1 + d2 > N || y - d1 + d2 < 1) return false;
        if(x + d2 + d1 > N || x + d2 + d1 < 1 || y + d2 - d1 > N || y + d2 - d1 < 1) return false;
        return true;
    }
    private static void Partition(int x, int y, int d1, int d2) {
        ////////// partition 5 //////////
        for(int nx = x, ny = y; nx <= x + d1 && ny >= ny - d1; nx++, ny--) {
            PartArr[nx][ny] = 5;
        }
        for(int nx = x, ny = y; nx <= x + d2 && ny <= y + d2; nx++, ny++) {
            PartArr[nx][ny] = 5;
        }
        for(int nx = x + d1, ny = y - d1; nx <= x + d1 + d2 && ny <= y - d1 + d2; nx++, ny++) {
            PartArr[nx][ny] = 5;
        }
        for(int nx = x + d2, ny = y + d2; nx <= x + d2 + d1 && ny >= y + d2 - d1; nx++, ny--) {
            PartArr[nx][ny] = 5;
        }
        ////////// partition 5 //////////

        ////////// partition 1 //////////
        for(int nx = 1; nx < x + d1; nx++) {
            for(int ny = 1; ny <= y; ny++) {
                if(PartArr[nx][ny] == 5) break;
                else PartArr[nx][ny] = 1;
            }
        }
        ////////// partition 1 //////////

        ////////// partition 2 //////////
        for(int nx = 1; nx <= x + d2; nx++) {
            for(int ny = N; ny > y; ny--) {
                if(PartArr[nx][ny] == 5) break;
                else PartArr[nx][ny] = 2;
            }
        }
        ////////// partition 2 //////////

        ////////// partition 3 //////////
        for(int nx = x + d1; nx <= N; nx++) {
            for(int ny = 1; ny < y - d1 + d2; ny++) {
                if(PartArr[nx][ny] == 5) break;
                else PartArr[nx][ny] = 3;
            }
        }
        ////////// partition 3 //////////

        ////////// partition 4 //////////
        for(int nx = x + d2 + 1; nx <= N; nx++) {
            for(int ny = N; ny >= y - d1 + d2; ny--) {
                if(PartArr[nx][ny] == 5) break;
                else PartArr[nx][ny] = 4;
            }
        }
        ////////// partition 4 //////////
//        System.out.println(x + " " + y + " " + d1 + " " + d2);
//        for(int i = 1; i <= N; i++) {
//            System.out.println(Arrays.toString(PartArr[i]));
//        }
//        System.out.println();
        sum();
        Init();
    }
    private static void sum() {
        int sum1 = 0;
        int sum2 = 0;
        int sum3 = 0;
        int sum4 = 0;
        int sum5 = 0;
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(PartArr[i][j] == 1) sum1 += Arr[i][j];
                else if(PartArr[i][j] == 2) sum2 += Arr[i][j];
                else if(PartArr[i][j] == 3) sum3 += Arr[i][j];
                else if(PartArr[i][j] == 4) sum4 += Arr[i][j];
                else;
            }
        }
        sum5 = tot - (sum1 + sum2 + sum3 + sum4);
        if(sum1 < min) min = sum1;
        if(sum1 > max) max = sum1;
        if(sum2 < min) min = sum2;
        if(sum2 > max) max = sum2;
        if(sum3 < min) min = sum3;
        if(sum3 > max) max = sum3;
        if(sum4 < min) min = sum4;
        if(sum4 > max) max = sum4;
        if(sum5 < min) min = sum5;
        if(sum5 > max) max = sum5;
        ans = Math.min(ans, max - min);
    }
    private static void Init() {
        for(int i = 0; i <= N; i++) {
            Arrays.fill(PartArr[i], 0);
        }
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
    }
}

