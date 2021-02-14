package BOJ.String.Gold;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class boj_1013 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder tmp;
        StringBuilder sb = new StringBuilder();

        Pattern pattern = Pattern.compile("((100+1+)|(01))+");
        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
            tmp = new StringBuilder();
            tmp.append(br.readLine());
            Matcher matcher = pattern.matcher(tmp.toString());

            if(matcher.matches()) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
