package BOJ.Queue.Silver;

import java.io.*;
import java.util.PriorityQueue;

public class boj_1927 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int input;
        PriorityQueue<Integer> Q = new PriorityQueue<>();

        while(N --> 0) {
            input = Integer.parseInt(br.readLine());

            if(input == 0) {
                if(Q.size() == 0) {
                    sb.append(0);
                } else {
                    sb.append(Q.poll());
                }
                sb.append("\n");
            } else {
                Q.add(input);
            }
        }
        bw.write(String.valueOf(sb));
        bw.close();
        br.close();
    }
}
