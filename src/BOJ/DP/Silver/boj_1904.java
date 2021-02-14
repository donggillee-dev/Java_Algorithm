package BOJ.DP.Silver;

import java.io.*;

public class boj_1904 {
    static int[] DPA = new int[1000001];
    static int DIV = 15746;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        DPA[1] = 1;
        DPA[2] = 2;
        int N = Integer.parseInt(br.readLine());
        int ans = DP(N);

        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static int DP(int n) {//N번째 타일 조합가능 개수는 N - 2에 대해 00을 붙여보는 것과 N - 1에 대해 1을 붙이는 방식이 잇음
        //하지만 N이 46이상의 경우 int의 범위를 넘어서기때문에 DP 결과값을 DIV(15746)으로 나눠서 저장
        for(int i = 3; i <= n; i++) {
            DPA[i] = (DPA[i-1] + DPA[i-2]) % DIV;
        }
        return DPA[n];
    }
}
