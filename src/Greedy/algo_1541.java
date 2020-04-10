package Greedy;
import java.io.*;

public class algo_1541 {
    static int ans = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String[] Seq = new String[999];
        String str = br.readLine();

        Seq = str.split("\\-");

        for(int i = 0; i < Seq.length; i++) {
            String[] plus = new String[100];
            int sum = 0;
            plus = Seq[i].split("\\+");

            for(int j = 0; j < plus.length; j++) {
                sum += Integer.parseInt(plus[j]);
            }
            if(i == 0) {
                ans += sum;
            } else {
                ans -= sum;
            }
        }
        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}
