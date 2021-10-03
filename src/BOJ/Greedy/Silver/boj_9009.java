package BOJ.Greedy.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//Logic
//피보나치 수열을 만들어 놓고 가장 큰 수부터 넣어보면서
//원하는 값에 도달 가능한지 아닌지를 가지고 스택에 넣어서 정답 만듬

//다시 풀어야함

public class boj_9009 {
    private static long[] makeFib(int length) {
        long[] fib = new long[length];

        fib[0] = 0;
        fib[1] = 1;

        for(int i = 2; i < length; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }

        return fib;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine()), length = 50;
        long[] fib = makeFib(length);

        while(t --> 0) {
            Stack<Long> s = new Stack<>();
            long tar = Long.parseLong(br.readLine());
            long sum = 0;
            for(int i = length - 1; i > 0; i--) {
                if(sum + fib[i] > tar) continue;

                sum += fib[i];
                s.push(fib[i]);
            }

            while(!s.isEmpty()) {
                sb.append(s.pop()).append(" ");
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }
}
