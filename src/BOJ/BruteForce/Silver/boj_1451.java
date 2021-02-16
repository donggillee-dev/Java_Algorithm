package BOJ.BruteForce.Silver;

import java.io.*;
import java.util.StringTokenizer;

public class boj_1451 {
    private static int N, M;
    private static long answer = Long.MIN_VALUE;
    private static int[][] arr;
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        arr = new int[N][M];

        for(int i = 0; i < N; i++) {
            sb.setLength(0);
            sb.append(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[i][j] = sb.charAt(j) - '0';
            }
        }

        solution1();
        solution2();
        solution3();
        solution4();
        solution5();
        solution6();

        sb.setLength(0);
        sb.append(answer);
        bw.write(String.valueOf(sb));
        bw.close();
        br.close();
    }
    private static void solution1() {
        //ㅓ

        int col = 1;

        while(col <= M - 1) {
            int row = 1;
            while(row <= N - 1) {
                long sum1 = 0;
                for(int i = 0; i < col; i++) {
                    for(int j = 0; j < row; j++) {
                        sum1 += arr[j][i];
                    }
                }
                long sum2 = 0;
                for(int i = 0; i < col; i++) {
                    for(int j = row; j < N; j++) {
                        sum2 += arr[j][i];
                    }
                }
                long sum3 = 0;
                for(int i = col; i < M; i++) {
                    for(int j = 0; j < N; j++) {
                        sum3 += arr[j][i];
                    }
                }
                answer = Math.max(answer, sum1 * sum2 * sum3);
                row++;
            }
            col++;
        }
    }
    private static void solution2() {
        //ㅗ

        int row = 1;
        while(row <= N - 1) {
            int col = 1;
            while(col <= M - 1) {
                long sum1 = 0;
                for(int i = 0; i < col; i++) {
                    for(int j = 0; j < row; j++) {
                        sum1 += arr[j][i];
                    }
                }
                long sum2 = 0;
                for(int i = col; i < M; i++) {
                    for(int j = 0; j < row; j++) {
                        sum2 += arr[j][i];
                    }
                }
                long sum3 = 0;
                for(int i = 0; i < M; i++) {
                    for(int j = row; j < N; j++) {
                        sum3 += arr[j][i];
                    }
                }
                answer = Math.max(answer, sum1 * sum2 * sum3);
                col++;
            }
            row++;
        }
    }
    private static void solution3() {
        //ㅏ
        int col = 1;
        while(col <= M - 1) {
            int row = 1;
            while(row <= N - 1) {
                long sum1 = 0;
                for(int i = 0; i < N; i++) {
                    for(int j = 0; j < col; j++) {
                        sum1 += arr[i][j];
                    }
                }
                long sum2 = 0;
                for(int i = 0; i < row; i++) {
                    for(int j = col; j < M; j++) {
                        sum2 += arr[i][j];
                    }
                }
                long sum3 = 0;
                for(int i = row; i < N; i++) {
                    for(int j = col; j < M; j++) {
                        sum3 += arr[i][j];
                    }
                }
                answer = Math.max(answer, sum1 * sum2 * sum3);
                row++;
            }
            col++;
        }

    }
    private static void solution4() {
        //ㅜ

        int row = 1;
        while(row <= N - 1) {
            int col = 1;
            while(col <= M - 1) {
                long sum1 = 0;
                for(int i = row; i < N; i++) {
                    for(int j = 0; j < col; j++) {
                        sum1 += arr[i][j];
                    }
                }
                long sum2 = 0;
                for(int i = row; i < N; i++) {
                    for(int j = col; j < M; j++) {
                        sum2 += arr[i][j];
                    }
                }
                long sum3 = 0;
                for(int i = 0; i < row; i++) {
                    for(int j = 0; j < M; j++) {
                        sum3 += arr[i][j];
                    }
                }
                answer = Math.max(answer, sum1 * sum2 * sum3);
                col++;
            }
            row++;
        }


    }
    private static void solution5() {
        //||

        int col1 = 1;
        while(col1 <= M - 2) {
            int col2 = col1;
            while(col2 <= M - 1) {
                long sum1 = 0;
                for(int i = 0; i < N; i++) {
                    for(int j = 0; j < col1; j++) {
                        sum1 += arr[i][j];
                    }
                }
                long sum2 = 0;
                for(int i = 0; i < N; i++) {
                    for(int j = col1; j < col2; j++) {
                        sum2 += arr[i][j];
                    }
                }
                long sum3 = 0;
                for(int i = 0; i < N; i++) {
                    for(int j = col2; j < M; j++) {
                        sum3 += arr[i][j];
                    }
                }
                answer = Math.max(answer, sum1 * sum2 * sum3);
                col2++;
            }
            col1++;
        }
    }
    private static void solution6() {
        //ㅡ
        int row1 = 1;
        while(row1 <= N - 2) {
            int row2 = row1;
            while(row2 <= N - 1) {
                long sum1 = 0;
                for(int i = 0; i < row1; i++) {
                    for(int j = 0; j < M; j++) {
                        sum1 += arr[i][j];
                    }
                }
                long sum2 = 0;
                for(int i = row1; i < row2; i++) {
                    for(int j = 0; j < M; j++) {
                        sum2 += arr[i][j];
                    }
                }
                long sum3 = 0;
                for(int i = row2; i < N; i++) {
                    for(int j = 0; j < M; j++) {
                        sum3 += arr[i][j];
                    }
                }
                answer = Math.max(answer, sum1 * sum2 * sum3);
                row2++;
            }
            row1++;
        }
    }
}
