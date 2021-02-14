package BOJ.BruteForce.Silver;

import java.io.*;

public class boj_1436 {
    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        String Doom_str = "666";
        int Doom_num = 666;
        int ans = 0;

        while(true) {
            String tmp = Integer.toString(Doom_num);
            if(tmp.contains(Doom_str)) {
                ans++;
                if(ans == N) break;
            }
            Doom_num++;
        }

        sb.append(Doom_num).append("\n");
        bw.write(String.valueOf(sb));
        br.close();
        bw.flush();
        bw.close();
        return;
    }
}
