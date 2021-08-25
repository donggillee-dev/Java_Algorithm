package BOJ.IMP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//정말 별로인 문제...
//처음에는 소용돌이 전체를 만들어서 부분을 잘라서 출력하려고했지만
//메모리 초과를 생각 못했다
//결과적으로 부분 배열을 만드는데 그 부분 배열을 만들때, 배열에 들어가는 값은 따로 돌리고 진행중인 index 행/열이
//해당 배열의 범위 내로 들어오면 배열에 값을 넣어주는 방식으로 해결
// -> 힌트를 보고 생각

//풀이 시간 : 1시간 20분
public class boj_1022 {
    private static int r1, c1, r2, c2, length;
    private static int[] dir_r = {-1, 0, 1, 0};
    private static int[] dir_c = {0, -1, 0, 1};
    private static int[][] arr;
    private static void makeArr() {
        int row = 0, col = 0, num = 1, width = 1;
        int dir = 3, cnt = 0;

        while(true) {
            if(arr[0][0] != 0 && arr[r2 - r1][0] != 0 && arr[0][c2 - c1] != 0 && arr[r2 - r1][c2 - c1] != 0) break;

            if(row >= r1 && row <= r2 && col >= c1 && col <= c2) {
                arr[row - r1][col - c1] = num;
            }

            row += dir_r[dir];
            col += dir_c[dir];
            num++;
            cnt++;

            if(cnt == width) {
                cnt = 0;
                if(dir == 2 || dir == 0) width++;

                dir++;
                dir %= 4;
            }
        }
    }
    private static int getMax() {
        int max = 0;
        int sr = 0;
        int sc = 0;
        int er = r2 - r1;
        int ec = c2 - c1;

        for(int i = sr; i <= er; i++) {
            for(int j = sc; j <= ec; j++) {
                max = Math.max(max, arr[i][j]);
            }
        }

        while(max > 0) {
            length++;
            max /= 10;
        }

        return length;
    }
    private static String makeAns(int r1, int c1, int r2, int c2) {
        int sr = 0;
        int sc = 0;
        int er = r2 - r1;
        int ec = c2 - c1;
        String format = "%" + length + "d ";
        StringBuilder sb = new StringBuilder();

        for(int i = sr; i <= er; i++) {
            for(int j = sc; j <= ec; j++) {
                sb.append(String.format(format, arr[i][j]));
            }
            sb.append("\n");
        }

        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        r1 = Integer.parseInt(stk.nextToken());
        c1 = Integer.parseInt(stk.nextToken());
        r2 = Integer.parseInt(stk.nextToken());
        c2 = Integer.parseInt(stk.nextToken());

        arr = new int[r2 - r1 + 1][c2 - c1 + 1];

        makeArr();
        getMax();
        System.out.print(makeAns(r1, c1, r2, c2));
    }
}
