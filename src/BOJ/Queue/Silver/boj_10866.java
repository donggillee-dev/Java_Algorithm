package BOJ.Queue.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

//Logic
//Deque 자료구조로 해결 가능

public class boj_10866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        Deque<Integer> dq = new LinkedList<>();

        while(N --> 0) {
            String[] cmd = br.readLine().split(" ");

            switch(cmd[0]) {
                case "push_front":
                    dq.addFirst(Integer.parseInt(cmd[1]));
                    break;
                case "push_back":
                    dq.addLast(Integer.parseInt(cmd[1]));
                    break;
                case "pop_front":
                    sb.append(dq.size() == 0 ? -1 : dq.pollFirst()).append("\n");
                    break;
                case "pop_back":
                    sb.append(dq.size() == 0 ? -1 : dq.pollLast()).append("\n");
                    break;
                case "size":
                    sb.append(dq.size()).append("\n");
                    break;
                case "empty":
                    sb.append(dq.size() == 0 ? 1 : 0).append("\n");
                    break;
                case "front":
                    sb.append(dq.size() == 0 ? -1 : dq.peekFirst()).append("\n");
                    break;
                case "back":
                    sb.append(dq.size() == 0 ? -1 : dq.peekLast()).append("\n");
                    break;
            }
        }

        System.out.print(sb);
    }
}
