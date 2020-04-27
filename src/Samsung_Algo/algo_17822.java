package Samsung_Algo;
import java.io.*;
import java.util.*;

public class algo_17822 {
    static int[][] Plate;
    static int[][] RotateArr;
    static int N, M, T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        T = Integer.parseInt(stk.nextToken());
        Plate = new int[N + 1][M + 1];
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
//        for(int i = 1; i <= N; i++) {
//            System.out.println(Arrays.toString(Plate[i]));
//        }
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void Rotate(int[] arr) {
        int x = arr[0];
        int d = arr[1];
        int k = arr[2];

        int[] tmp = new int[M + 1];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                tmp[j] = Plate[i][j];
            }
            if(d == 0 && i % x == 0) {//시계 방향
                int idx = 0;
                Remove(i);
            } else if(d == 1 && i % x == 0){//반시계 방향
                int tmp_k = 0;
                while(tmp_k < k) {
                    for(int j = M; j > 0; j--) {
                        Plate[i][j - 1] = Plate[i][j];
                    }
                    tmp_k++;
                }
                Plate[i][M] = tmp[1];
                Remove(i);
            }
        }
    }
    private static void Remove(int ith_plate) {
        int sum = 0;
        int cnt = 0;
        long avg = 0;
        for(int i = 1; i <= N; i++) {
            System.out.println(Arrays.toString(Plate[i]));
        }

//        for(int i = 1; i <= M; i++) {
//            if(Plate[ith_plate][i] != 0) {
//                if (ith_plate == 1) {
//                    if (Plate[ith_plate][i] == Plate[ith_plate + 1][i]) {
//                        Plate[ith_plate+ 1][i] = 0;
//                    }
//                } else if (ith_plate == N) {
//                    if (Plate[ith_plate][i] == Plate[ith_plate - 1][i]) {
//                        Plate[ith_plate - 1][i] = 0;
//                    }
//                } else {
//                    if (Plate[ith_plate][i] == Plate[ith_plate - 1][i]) {
//                        Plate[ith_plate - 1][i] = 0;
//                    }
//                    if (Plate[ith_plate][i] == Plate[ith_plate + 1][i]) {
//                        Plate[ith_plate + 1][i] = 0;
//                    }
//                }
//                Plate[ith_plate][i] = 0;
//            }
//        }

//        for(int i = 1; i <= M; i++) {
//            if(Plate[ith_plate][i] != 0) {
//                sum += Plate[ith_plate][i];
//                cnt++;
//            }
//        }

//        avg = (long)(sum / cnt);
//        for(int i = 1; i <= M; i++) {
//            if(Plate[ith_plate][i] > avg) {
//                Plate[ith_plate][i]--;
//            }
//            if(Plate[ith_plate][i] < avg) {
//                Plate[ith_plate][i]++;
//            }
//        }
    }
}

