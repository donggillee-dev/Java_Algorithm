package BOJ.DP.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//dp를 이용해서 간단히 풀 수 있는 문제
//해당 위치의 값은 이전값까지의 최선의 선택에 영향을 받기에 최소, 최대 배열을 만들어서 점화식 생성
//풀이 시간 : 16분

public class boj_2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][3];
        int[][] min = new int[n][3];
        int[][] max = new int[n][3];

        for(int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(stk.nextToken());
                if(i == 0) {
                    min[i][j] = arr[i][j];
                    max[i][j] = arr[i][j];
                }
            }
        }

        for(int i = 0; i < n - 1; i++) {
            for(int j = 0; j < 3; j++) {
                int num = arr[i + 1][j];
                if(j == 0) {
                    min[i + 1][j] = Math.min(min[i][0] + num, min[i][1] + num);
                    max[i + 1][j] = Math.max(max[i][0] + num, max[i][1] + num);
                } else if(j == 1) {
                    min[i + 1][j] = Math.min(min[i][0] + num, Math.min(min[i][1] + num, min[i][2] + num));
                    max[i + 1][j] = Math.max(max[i][0] + num, Math.max(max[i][1] + num, max[i][2] + num));
                } else {
                    min[i + 1][j] = Math.min(min[i][1] + num, min[i][2] + num);
                    max[i + 1][j] = Math.max(max[i][1] + num, max[i][2] + num);
                }
            }
        }
        int maxAns = -987654321;
        int minAns = 987654321;
        for(int i = 0; i < 3; i++) {
            if(min[n - 1][i] < minAns) minAns = min[n - 1][i];
            if(max[n - 1][i] > maxAns) maxAns = max[n - 1][i];
        }

        System.out.println(maxAns + " " + minAns);
    }
}
