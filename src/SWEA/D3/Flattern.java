package SWEA.D3;

import java.io.*;
import java.util.*;

public class Flattern {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int TestCase = 10;
        PriorityQueue<Integer> descQueue = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> ascdQueue = new PriorityQueue<>();

        for(int tc = 0; tc < TestCase; tc++) {
            int dump = Integer.parseInt(br.readLine());
            StringTokenizer stk = new StringTokenizer(br.readLine());

            int token, top, bottom;
            while(stk.hasMoreTokens()) {
                token = Integer.parseInt(stk.nextToken());
                descQueue.offer(token);
                ascdQueue.offer(token);
            }

            for(int i = 0; i < dump; i++) {
                int diff = Math.abs(descQueue.peek() - ascdQueue.peek());
                if( diff == 0 || diff == 1 ) break;


                //가장 높은 층과 가장 낮은 층을 뽑아와서 더해주고 빼줌
                top = descQueue.poll();
                bottom = ascdQueue.poll();
                top--;
                bottom++;

                ascdQueue.offer(bottom);
                descQueue.offer(top);

            }
            //테스트케이스가 끝났으면 큐 초기화해주고 StringBuilder에 정답 append
            sb.append("#" + (tc + 1)).append(" ").append(Math.abs(descQueue.poll() - ascdQueue.poll())).append("\n");
            descQueue.clear();
            ascdQueue.clear();

        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}
