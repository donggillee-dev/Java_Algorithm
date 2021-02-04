package SWEA.D4;

import java.io.*;
import java.util.*;

public class KeyGenerator {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        Queue<Integer> Q = new LinkedList<>();

        for(int i = 0; i < 10; i++) {
            int tc = Integer.parseInt(br.readLine());
            StringTokenizer stk = new StringTokenizer(br.readLine());

            for(int j = 0; j < 8; j++) {
                Q.offer(Integer.parseInt(stk.nextToken()));
            }


            loop:while(true) {
                for(int j = 1; j <= 5; j++) {
                    int tmp = Q.poll();
                    tmp -= j;
                    if(tmp <= 0) {
                        Q.offer(0);
                        break loop;
                    }
                    Q.offer(tmp);
                }
            }

            sb.append("#" + tc + " ");
            while(!Q.isEmpty()) {
                sb.append(Q.poll()).append(" ");
            }
            sb.delete(sb.length() - 1, sb.length());
            sb.append("\n");
        }
        bw.write(String.valueOf(sb));
        bw.close();
        br.close();
    }
}
