import java.io.*;

public class Test1 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int tmp = N / 2;

        if(tmp % 2 != 0) {
            sb.append(7);
            tmp -= 1;
        }

        for(int i = 0; i < tmp; i++) {
            sb.append(1);
        }
        sb.append("\n");

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}
