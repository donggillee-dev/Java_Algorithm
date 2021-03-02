package BOJ.DivideAndConquer.Gold;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

//Logic
//현재 주어지는 B => 즉 제곱횟수를 가지고 분할정복을 수행한다
//해당 숫자 B가 1이 되어지는 경우에는 hash에서 get(1) => 원본 배열을 꺼내 리턴해주고

//그 외의 경웨 B가 짝수인 경우에는 나누기 2 인 숫자로 재귀를 돈다 => 재귀를 돌아 리턴되는 값( B / 2 ) 가 hash에 존재하는 값인 경우에는
//굳이 재귀를 돌 필요없이 hash에서 꺼내와서 그냥 곱해준다

//홀수의 경우에는 짝수로 맞춰주기 위해 B - 1과 A1을 곱해준 값을 리턴해준다.

//풀이 시간 : 30분
public class boj_10830 {
    private static final int divNum = 1000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        long B = Long.parseLong(stk.nextToken());
        int[][] init = new int[N][N];
        HashMap<Long, int[][]> hash = new HashMap<>();
        for(int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                init[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        hash.put((long)1, init);

        int[][] result = recur(B, N, hash);

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                sb.append(result[i][j] % divNum).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
    private static int[][] recur(long num, int N, HashMap<Long, int[][]> hash) {
        int[][] result = new int[N][N], tmp;

        if(num == 1) return hash.get((long)1);

        else if(num % 2 == 0) {
            if(hash.get(num / 2) != null) {
                tmp = hash.get(num / 2);
            } else {
                tmp = recur(num / 2, N, hash);
                hash.put(num / 2, tmp);
            }
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    int sum = 0;
                    for(int k = 0; k < N; k++) {
                        sum += tmp[i][k] * tmp[k][j];
                    }
                    result[i][j] = sum % divNum;
                }
            }
        } else {
            int[][] init = hash.get((long)1);
            if(hash.get(num - 1) != null) {
                tmp = hash.get(num - 1);
            } else {
                tmp = recur(num - 1, N, hash);
                hash.put(num - 1, tmp);
            }

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    int sum = 0;
                    for(int k = 0; k < N; k++) {
                        sum += tmp[i][k] * init[k][j];
                    }
                    result[i][j] = sum % divNum;
                }
            }
        }
        return result;
    }
}
