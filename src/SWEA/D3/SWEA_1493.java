package SWEA.D3;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class SWEA_1493 {
    private static int[][] arr = new int[301][301];
    private static class Info {
        int row, col;
        public Info(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        HashMap<Integer, Info> hash = new HashMap<>();
        int num = 1;
        for(int i = 1; i <= 300; i++) {
            int row = 1;
            int col = i;
            while(col >= 1) {
                arr[row][col] = num;
                hash.put(num, new Info(row, col));
                row++;
                col--;
                num++;
            }
        }

        int TestCase = Integer.parseInt(br.readLine());
        StringTokenizer stk;
        for(int tc = 1; tc <= TestCase; tc++) {
            stk = new StringTokenizer(br.readLine());
            int numA = Integer.parseInt(stk.nextToken());
            int numB = Integer.parseInt(stk.nextToken());

            Info A = hash.get(numA);
            Info B = hash.get(numB);

            sb.append("#" + tc + " ").append(arr[A.row + B.row][A.col + B.col]).append("\n");
        }
        bw.write(String.valueOf(sb));
        bw.close();
        br.close();
    }
}
