package BOJ.Greedy.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//그때그때 다른 위치를 발견하면 3 * 3 만큼 뒤집어줌
//최종적으로 동일한 배열인지 확인해서 결과 도출

public class boj_1080 {

    private static int n, m;

    private static int stoi(String str) {
        return Integer.parseInt(str);
    }

    private static void makeArr(BufferedReader br, boolean[][] arr, boolean[][] res) throws IOException {
        for(int i = 0; i < n * 2; i++) {
            String str = br.readLine();
            for(int j = 0; j < m; j++) {
                if(i / n == 0) {
                    arr[i][j] = str.charAt(j) == '1';
                } else {
                    res[i % n][j] = str.charAt(j) == '1';
                }
            }
        }
    }

    private static void flip(int i, int j, boolean[][] arr) {
        for(int row = i; row < i + 3; row++) {
            for(int col = j; col < j + 3; col++) {
                arr[row][col] = !arr[row][col];
            }
        }
    }

    private static int solve(boolean[][] arr, boolean[][] res) {
        int ret = 0;

        for(int i = 0; i <= n - 3; i++) {
            for(int j = 0; j <= m - 3; j++) {
                if(arr[i][j] != res[i][j]) {
                    flip(i, j, arr);
                    ret++;
                }
            }
        }

        return ret;
    }

    private static boolean isSame(boolean[][] arr, boolean[][] res) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(arr[i][j] != res[i][j]) return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = stoi(stk.nextToken());
        m = stoi(stk.nextToken());
        boolean[][] arr = new boolean[n][m];
        boolean[][] res = new boolean[n][m];

        makeArr(br, arr, res);
        int ans = solve(arr, res);

        if(isSame(arr, res)) System.out.println(ans);
        else System.out.println(-1);
    }
}
