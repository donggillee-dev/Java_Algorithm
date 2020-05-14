package Samsung_Algo;
import java.io.*;
import java.util.*;

public class algo_14890 {
    static int N, L, ans = 0;
    static int[][] Map;
    static boolean[][] Built;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        L = Integer.parseInt(stk.nextToken());
        Map = new int[N][N];
        Built = new boolean[N][N];
        for(int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                Map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        for(int i = 0; i < N; i++) {
            RowPossible(i);
        }
        for(int i = 0; i < N; i++) {
            ColumnPossible(i);
        }
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void RowPossible(int Row) {
        boolean Possible = true;
        int length = 1;
        int height = Map[Row][0];

        loop1:for(int j = 1; j < N; j++) {
            if(Math.abs(Map[Row][j] - height) > 1) {
                Possible = false;
                break loop1;
            }

            if(Map[Row][j] > height) {//올라가는 경사
                if(length < L) {
                    Possible = false;
                    break loop1;
                }
                for(int k = 1, idx = j - k; k <= L && idx >= 0; k++) {
                    if(Built[Row][idx]) {
                        Possible = false;
                        break loop1;
                    }
                }
                for(int k = 1, idx = j - k; k <= L && idx >= 0; k++) {
                    Built[Row][idx] = true;
                }
                height = Map[Row][j];
                length = 1;
            } else if(Map[Row][j] < height) {//내려가는 경사
                height = Map[Row][j];
                for(int k = 1, idx = j + k; k < L && idx < N; k++) {
                    if(height != Map[Row][idx] || Built[Row][idx]) {
                        Possible = false;
                        break loop1;
                    }
                }
                for(int k = 0, idx = j + k; idx < N && k < L; k++) {
                    Built[Row][idx] = true;
                }
                j += (L - 1);
                height = Map[Row][j];
                length = 1;
            } else {//똑같은 높이인 경우
                length++;
            }
        }
        if(Possible) {
            System.out.println(Row);
            ans++;
        }
        Init_Built();
    }
    private static void ColumnPossible(int Column) {
        boolean Possible = true;
        int length = 1;
        int height = Map[0][Column];

        loop1:for(int j = 1; j < N; j++) {
            if(Math.abs(Map[j][Column] - height) > 1) {
                Possible = false;
                break loop1;
            }

            if(Map[j][Column] > height) {//올라가는 경사
                if(length < L) {
                    Possible = false;
                    break loop1;
                }
                for(int k = 1, idx = j - k; k <= L && idx >= 0; k++) {
                    if(Built[idx][Column]) {
                        Possible = false;
                        break loop1;
                    }
                }
                for(int k = 1, idx = j - k; k <= L && idx >= 0; k++) {
                    Built[idx][Column] = true;
                }
                height = Map[j][Column];
                length = 1;
            } else if(Map[j][Column] < height) {//내려가는 경사 -> 다시 생각해볼 것
                height = Map[j][Column];
                for(int k = 1, idx = j + k; k < L && idx < N; k++) {
                    if(height != Map[idx][Column] || Built[idx][Column]) {
                        Possible = false;
                        break loop1;
                    }
                }
                for(int k = 0, idx = j + k; idx < N && k < L; k++) {
                    Built[idx][Column] = true;
                }
                j += (L - 1);
                height = Map[j][Column];
                length = 1;
            } else {//똑같은 높이인 경우
                length++;
            }
        }
        if(Possible) {
            System.out.println(Column);
            ans++;
        }
        Init_Built();
    }
    private static void Init_Built() {
        for(int i = 0; i < N; i++) {
            Arrays.fill(Built[i], false);
        }
    }
}