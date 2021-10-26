package BOJ.IMP.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//가장 최소 높이, 최대 높이에 대해서 일일히 시뮬레이션 돌려보면 된다

public class boj_18111 {
    private static int stoi(String str) {
        return Integer.parseInt(str);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = stoi(stk.nextToken());
        int M = stoi(stk.nextToken());
        int B = stoi(stk.nextToken());
        int[][] arr = new int[N][M];
        int min = 987654321, max = -987654321;
        int ansTime = Integer.MAX_VALUE, ansHeight = 0;

        for(int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[i][j] = stoi(stk.nextToken());
                min = Math.min(arr[i][j], min);
                max = Math.max(arr[i][j], max);
            }
        }

        for(int h = min; h <= max; h++) {
            int remove_sum = 0;
            int append_sum = 0;

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    int diff = h - arr[i][j];

                    if(diff < 0) {
                        remove_sum -= diff;
                    } else {
                        append_sum += diff;
                    }
                }
            }

            if(append_sum <= remove_sum + B) {
                int time = (remove_sum * 2) + append_sum;

                if(time <= ansTime) {
                    if(time != ansTime) {
                        ansTime = time;
                    }
                    ansHeight = h;
                }
            }
        }

        System.out.println(ansTime + " " + ansHeight);
    }
}
