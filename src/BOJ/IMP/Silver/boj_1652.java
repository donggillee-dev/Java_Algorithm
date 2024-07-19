package BOJ.IMP.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1652 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine()), rowCnt = 0, colCnt = 0;
        char[][] arr = new char[N][N];

        for(int i = 0; i < N; i++) {
            sb.append(br.readLine());

            for(int j = 0; j < N; j++) {
                arr[i][j] = sb.charAt(j);
            }

            String[] splitArr = sb.toString().split("X");

            for(String str : splitArr) {
                if(str.length() >= 2) rowCnt++;
            }

            sb.setLength(0);
        }


        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                sb.append(arr[j][i]);
            }

            String[] splitArr = sb.toString().split("X");

            for(String str : splitArr) {
                if(str.length() >= 2) colCnt++;
            }

            sb.setLength(0);
        }

        sb.append(rowCnt).append(" ").append(colCnt);

        System.out.println(sb.toString());
    }
}
