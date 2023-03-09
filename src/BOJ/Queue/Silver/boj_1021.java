package BOJ.Queue.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1021 {
    private static int M = 0;
    private static int N = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        Deque<Integer> dq = new LinkedList<>();
        Queue<Integer> q = new LinkedList<>();
        int ans = 0;

        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        stk = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            q.add(Integer.parseInt(stk.nextToken()));
        }

        for(int i = 1; i <= N; i++) {
            dq.addLast(i);
        }

        ans = getMinAns(dq, q);

        System.out.println(ans);
    }

    private static int getMinAns(Deque<Integer> dq, Queue<Integer> q) {
        int cnt = 0;

        while(!q.isEmpty()) {
            if(dq.peekFirst() != q.peek()) {
                int tmp = 0;
                int num = q.peek();

                for(int elem : dq) {
                   if(elem == num) {
                       break;
                   }
                   tmp++;
                }

                if(tmp < dq.size() - tmp) { //더 왼쪽에 있는 경우
                    while(dq.peekFirst() != q.peek()) {
                        cnt++;
                        dq.addLast(dq.pollFirst());
                    }
                } else { //더 오른쪽 아니면 동등한 경우
                    while(dq.peekFirst() != q.peek()) {
                        cnt++;
                        dq.addFirst(dq.pollLast());
                    }
                }
            }
            dq.pollFirst();
            q.poll();
        }

        return cnt;
    }
}
