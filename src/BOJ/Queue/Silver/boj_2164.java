package BOJ.Queue.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//Logic
//큐로 구현하면 된다

public class boj_2164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> q = new LinkedList<>();

        for(int i = 1; i <= N; i++) {
            q.add(i);
        }

        while(N != 1) {
            q.poll();
            q.add(q.poll());
            N--;
        }

        System.out.println(q.poll());
    }
}
