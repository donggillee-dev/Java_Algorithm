package Samsung_Algo;
import java.io.*;
import java.util.*;

public class algo_12100 {
    static int[][] OriArr;
    static int N, ans = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        OriArr = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                OriArr[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        for(int i = 0; i < 4; i++) {
            DFS(OriArr, 0, i);
        }
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void DFS(int[][] arr, int depth, int dir) {
        if(depth == 5) return;
        else {
            int[][] tmp = Move(arr, dir);
            getMax(tmp);
            for(int i = 0; i < 4; i++) {
                DFS(tmp, depth + 1, i);
            }
        }
    }
    private static void getMax(int[][] tmp) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(tmp[i][j] > ans) ans = tmp[i][j];
            }
        }
        return;
    }

    private static int[][] Move(int[][] tmp, int dir) {
        int[][] copy = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                copy[i][j] = tmp[i][j];
            }
        }
        if(dir == 0) {//위
            //먼저 같은 열에 있는 숫자들에 대해 하나씩 비교 0일 경우에는 뛰어넘어주고 다를경우에는 다음 거에 대해서 수행
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N - 1; j++) {
                    int k = j + 1;
                    while(k < N) {
                        if(copy[k][i] != 0) {
                            if(copy[k][i] == copy[j][i]) {
                                copy[j][i] += copy[k][i];
                                copy[k][i] = 0;
                                break;
                            } else break;
                        }
                        k++;
                    }
                }
                for(int j = 0; j < N - 1; j++) {
                    if(copy[j][i] == 0) {
                        int k = j + 1;
                        while(k < N) {
                            if(copy[k][i] != 0) {
                                copy[j][i] = copy[k][i];
                                copy[k][i] = 0;
                                break;
                            }
                            k++;
                        }
                    }
                }
            }
        }
        if(dir == 1) {
            for(int i = 0; i < N; i++) {
                for(int j = N - 1; j > 0; j--) {
                    int k = j - 1;
                    while(k >= 0) {
                        if(copy[k][i] != 0) {
                            if(copy[k][i] == copy[j][i]) {
                                copy[j][i] += copy[k][i];
                                copy[k][i] = 0;
                                break;
                            }else break;
                        }
                        k--;
                    }
                }
                for(int j = N - 1; j > 0; j--) {
                    if(copy[j][i] == 0) {
                        int k = j - 1;
                        while(k >= 0) {
                            if(copy[k][i] != 0) {
                                copy[j][i] = copy[k][i];
                                copy[k][i] = 0;
                                break;
                            }
                            k--;
                        }
                    }
                }
            }
        }
        if(dir == 2) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N - 1; j++) {
                    int k = j + 1;
                    while(k < N) {
                        if(copy[i][k] != 0) {
                            if(copy[i][k] == copy[i][j]) {
                                copy[i][j] += copy[i][k];
                                copy[i][k] = 0;
                                break;
                            } else break;
                        }
                        k++;
                    }
                }
                for(int j = 0; j < N - 1; j++) {
                    if(copy[i][j] == 0) {
                        int k = j + 1;
                        while(k < N) {
                            if(copy[i][k] != 0) {
                                copy[i][j] = copy[i][k];
                                copy[i][k] = 0;
                                break;
                            }
                            k++;
                        }
                    }
                }
            }
        }
        if(dir == 3) {
            for(int i = 0; i < N; i++) {
                for(int j = N - 1; j > 0; j--) {
                    int k = j - 1;
                    while(k >= 0) {
                        if(copy[i][k] != 0) {
                            if(copy[i][k] == copy[i][j]) {
                                copy[i][j] += copy[i][k];
                                copy[i][k] = 0;
                                break;
                            }else break;
                        }
                        k--;
                    }
                }
                for(int j = N - 1; j > 0; j--) {
                    if(copy[i][j] == 0) {
                        int k = j - 1;
                        while(k >= 0) {
                            if(copy[i][k] != 0) {
                                copy[i][j] = copy[i][k];
                                copy[i][k] = 0;
                                break;
                            }
                            k--;
                        }
                    }
                }
            }
        }
        return copy;
    }
}
