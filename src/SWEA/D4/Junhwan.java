package SWEA.D4;

import java.io.*;
import java.util.StringTokenizer;

public class Junhwan {
    private static int answer;
    private static boolean[] visited;
    private static int[] weight;
    private static int[] Factorial = new int[10], Pow = new int[10];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;
        int TestCase = Integer.parseInt(br.readLine());

        Factorial[0] = 1;
        Pow[0] = 1;
        for(int i = 1; i <= 9; i++) {
            Factorial[i] = Factorial[i - 1] * i;
            Pow[i] = Pow[i - 1] * 2;
        }
        for(int tc = 1; tc <= TestCase; tc++) {
            int N = Integer.parseInt(br.readLine());
            int total = 0;
            stk = new StringTokenizer(br.readLine());
            weight = new int[N];
            visited = new boolean[N];

            for(int i = 0; i < N; i++) {
                weight[i] = Integer.parseInt(stk.nextToken());
                total += weight[i];
            }
            permutation(0, 0, 0, total, N);
            sb.append("#" + tc + " ").append(answer).append("\n");
            answer = 0;
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
    private static void permutation(int cnt, int left, int right, int rem, int N) {
        if(cnt == N) {
            answer++;
            return;
        }

        if(left >= rem + right) {
            answer += Factorial[N - cnt] * Pow[N - cnt];
            return;
        }

        for(int i = 0; i < N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                int cur = weight[i];

                permutation(cnt + 1, left + cur, right, rem - cur, N);

                if(cur + right <= left) {
                    permutation(cnt + 1, left, right + cur, rem - cur, N);
                }
                visited[i] = false;
            }
        }
    }
}
