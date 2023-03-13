package BOJ.IMP.Bronze;

import java.io.*;

public class boj_2816 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine()), idx = 0;
        String[] channel = new String[N];

        for(int i = 0; i < N ; i++) {
            channel[i] = br.readLine();
        }

        while(!channel[idx].equals("KBS1")) {
            sb.append("1");
            idx++;
        }

        while(idx > 0) {
            if(channel[idx - 1].equals("KBS2")) {
                channel[idx] = "KBS2";
                channel[idx - 1] = "";
            }
            sb.append("4");
            idx--;
        }

        while(!channel[idx].equals("KBS2")) {
            sb.append("1");
            idx++;
        }

        while(idx > 1) {
            sb.append("4");
            idx--;
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
