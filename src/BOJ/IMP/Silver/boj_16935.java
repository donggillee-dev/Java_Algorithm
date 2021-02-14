package BOJ.IMP.Silver;

import java.io.*;
import java.util.StringTokenizer;

public class boj_16935 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int R = Integer.parseInt(stk.nextToken());
        int[][] arr = new int[N][M];

        for(int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        stk = new StringTokenizer(br.readLine());
        for(int i = 0; i < R; i++) {
            int calNum = Integer.parseInt(stk.nextToken());

            if(calNum == 1) {
                arr = Cal1(arr);
            } else if(calNum == 2) {
                arr = Cal2(arr);
            } else if(calNum == 3) {
                arr = Cal3(arr);
            } else if(calNum == 4) {
                arr = Cal4(arr);
            } else if(calNum == 5) {
                arr = Cal5(arr);
            } else {
                arr = Cal6(arr);
            }
        }

        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
    private static int[][] Cal1(int[][] arr) {
        int[][] new_arr = new int[arr.length][arr[0].length];
        for(int row = 0; row < arr.length; row++) {
            System.arraycopy(arr[row], 0, new_arr[arr.length - 1 - row], 0, new_arr[row].length);
        }

        return new_arr;
    }
    private static int[][] Cal2(int[][] arr) {
        int[][] new_arr = new int[arr.length][arr[0].length];
        for(int col = 0; col < new_arr[0].length; col++) {
            for(int row = 0; row < new_arr.length; row++) {
                new_arr[row][col] = arr[row][arr[row].length - col - 1];
            }
        }
        return new_arr;
    }
    private static int[][] Cal3(int[][] arr) {
        int[][] new_arr = new int[arr[0].length][arr.length];

        int idx = new_arr[0].length - 1;
        for(int row = 0; row < arr.length; row++) {
            for(int col = 0; col < arr[row].length; col++) {
                new_arr[col][idx] = arr[row][col];
            }
            idx--;
        }
        return new_arr;
    }
    private static int[][] Cal4(int[][] arr) {
        int[][] new_arr = new int[arr[0].length][arr.length];

        for(int row = 0; row < arr.length; row++) {
            for(int col = 0; col < arr[row].length; col++) {
                new_arr[new_arr.length - col - 1][row] = arr[row][col];
            }
        }
        return new_arr;
    }
    private static int[][] Cal5(int[][] arr) {
        int[][] new_arr = new int[arr.length][arr[0].length];
        int mid_row = arr.length / 2;
        int mid_col = arr[0].length / 2;

        //1번 구역 이동
        for(int row = 0; row < mid_row; row++) {
            for(int col = 0; col < mid_col; col++) {
                new_arr[row][col + mid_col] = arr[row][col];
            }
        }
        //2번 구역 이동
        for(int row = 0; row < mid_row; row++) {
            for(int col = mid_col; col < arr[row].length; col++) {
                new_arr[row + mid_row][col] = arr[row][col];
            }
        }
        //3번 구역 이동
        for(int row = mid_row; row < arr.length; row++) {
            for(int col = mid_col; col < arr[row].length; col++) {
                new_arr[row][col - mid_col] = arr[row][col];
            }
        }
        //4번 구역 이동
        for(int row = mid_row; row < arr.length; row++) {
            for(int col = 0; col < mid_col; col++) {
                new_arr[row - mid_row][col] = arr[row][col];
            }
        }
        return new_arr;
    }
    private static int[][] Cal6(int[][] arr) {
        int[][] new_arr = new int[arr.length][arr[0].length];

        int mid_row = arr.length / 2;
        int mid_col = arr[0].length / 2;
        //1번 구역 이동
        for(int row = 0; row < mid_row; row++) {
            for(int col = 0; col < mid_col; col++) {
                new_arr[row + mid_row][col] = arr[row][col];
            }
        }
        //4번 구역 이동
        for(int row = mid_row; row < arr.length; row++) {
            for(int col = 0; col < mid_col; col++) {
                new_arr[row][col + mid_col] = arr[row][col];
            }
        }
        //3번 구역 이동
        for(int row = mid_row; row < arr.length; row++) {
            for(int col = mid_col; col < arr[row].length; col++) {
                new_arr[row - mid_row][col] = arr[row][col];
            }
        }
        //2번 구역 이동
        for(int row = 0; row < mid_row; row++) {
            for(int col = mid_col; col < arr[row].length; col++) {
                new_arr[row][col - mid_col] = arr[row][col];
            }
        }
        return new_arr;
    }
}
