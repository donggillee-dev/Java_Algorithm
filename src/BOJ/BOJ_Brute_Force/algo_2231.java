package BOJ.BOJ_Brute_Force;
import java.io.*;

public class algo_2231 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine().trim());
        int MIN = Integer.MAX_VALUE;

        for(int  i = 1; i < N; i++) {
            int tmp = i;
            int sum = i;
            while(tmp % 10 != 0) {
                sum += tmp % 10;
                tmp /= 10;
            }
            if(sum == N && i <= MIN) MIN = i;
        }
        if(MIN == Integer.MAX_VALUE) sb.append(0).append("\n");
        else sb.append(MIN).append("\n");
        bw.write(String.valueOf(sb));
        br.close();
        bw.flush();
        bw.close();
        return;
    }
}
