package ETC;
import java.io.*;

public class algo_1032 {
    static int N;
    static String[] Cmd;
    static int len;
    static String ans = "";
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        Cmd = new String[N];

        for(int i = 0; i < N; i++) {
            Cmd[i] = br.readLine();
        }
        len = Cmd[0].length();

        for(int i = 0; i < len; i++) {
            boolean check = true;
            for(int j = 0; j < N - 1; j++) {
                if(Cmd[j].charAt(i) != Cmd[N -1].charAt(i)) {
                    check = false;
                    break;
                }
            }
            if(check) ans += Cmd[0].charAt(i);
            else ans += '?';
        }

        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}
