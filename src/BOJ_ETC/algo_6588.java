package BOJ_ETC;

import java.io.*;

public class algo_6588 {
    static int MAX = 1000000;
    static boolean[] An = new boolean[MAX + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        An[0] = An[1] = true;

        for(int i = 2; i * i <= MAX; i++) {
            for(int j = i * i; j <= MAX; j += i) {
                An[j] = true;
            }
        }

        while(true) {
            boolean flag = false;
            int n = Integer.parseInt(br.readLine());
            if(n == 0) break;
            else {
                int Left = 2, Right = n - 2;
                while(Left <= n/2 && Right >= n/2) {
                    if(!An[Left] && !An[Right]) {
                        sb.append(n + " = " + Left + " + " + Right).append("\n");
                        flag = true;
                        break;
                    }
                    Left++;
                    Right--;
                }
            }
            if(flag == false) sb.append("Goldbach's conjecture is wrong.").append("\n");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}
