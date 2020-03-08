import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Test Test = new Test();
        int ans, N;

        N = Integer.parseInt(br.readLine());
        ans = (int)Math.pow((double)2, (double)N) - 1;

        sb.append(ans).append("\n");
        Test.Hanoi(N, '1', '2', '3');

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}

class Test {
    void Hanoi(int N, char from, char by, char to) {

        if(N == 1) {//옮겨야할게 1개이면 그냥 from에서 to로 옮겨줌
            Main.sb.append(from).append(" ").append(to).append("\n");
        } else {
            Hanoi(N - 1, from, to, by);//N-1개에 대해서 from에서 by로 옮겨주고
            Main.sb.append(from).append(" ").append(to).append("\n");//제일 큰 원판은 from에서 to로 옮겨줌
            Hanoi(N -1, by, from, to);//옮겨놨었던 N-1개에 대해서 by에서 to로 다시 옮겨줌
        }
        return;
    }
}
