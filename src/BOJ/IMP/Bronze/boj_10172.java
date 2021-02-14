package BOJ.IMP.Bronze;

import java.io.*;

public class boj_10172 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        sb.append("|\\_/|").append("\n");
        sb.append("|q p|   /}").append("\n");
        sb.append("( 0 )\"\"\"\\").append("\n");
        sb.append("|\"^\"`    |").append("\n");
        sb.append("||_/=\\\\__|");
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
