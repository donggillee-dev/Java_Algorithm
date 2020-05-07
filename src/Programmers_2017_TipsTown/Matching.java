package Programmers_2017_TipsTown;
import java.io.*;
import java.util.StringTokenizer;

public class Matching {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int A = Integer.parseInt(stk.nextToken());
        int B = Integer.parseInt(stk.nextToken());
        int ans = solution(N, A, B);

        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static int solution(int n, int a, int b) {
        int ans = 1;
        int A = a;
        int B = b;

        while(true) {
            if(Math.abs(A - B) == 1) {
                if(A % 2 == 0) {
                    if(B == A - 1) break;
                }
                if(B % 2 == 0) {
                    if(A == B - 1) break;
                }
            }

            A = (A / 2) + (A % 2);
            B = (B / 2) + (B % 2);
            ans++;
        }
        return ans;
    }
}
