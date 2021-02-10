package BOJ.BOJ_StepByStep.Bronze;

import java.io.*;

public class algo_10448 {
    static int[] DP = new int[51];
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for(int i = 1; i < DP.length; i++) {
            DP[i] = (i)*(i + 1)/2;
        }

        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if(DFS(0, 0, num))
                sb.append(1).append("\n");
            else
                sb.append(0).append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static boolean DFS(int depth, int sum, int num) {
        if(depth == 3) {
            if(sum == num)
                return true;
            else return false;
        }

        for(int i = 1; i < DP.length; i++) {
            if(sum < num) {
                sum += DP[i];
                if(DFS(depth + 1, sum, num)) return true;
                sum -= DP[i];
            }
        }
        return false;
    }
}
