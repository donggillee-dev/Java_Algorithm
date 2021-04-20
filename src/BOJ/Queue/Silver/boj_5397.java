package BOJ.Queue.Silver;

import java.io.*;
import java.util.*;

//Logic
//Deque 두개를 이용해서 커서의 왼쪽, 오른쪽을 구현한다
// <, >, - 과 같은 커서의 행위에 대한 값이 들어오면 디큐를 이용해서 처리
// 그 외의 경우에는 문자가 들어와지는 경우이므로 왼쪽 디큐 뒤에다가 값을 넣어준다
//풀이 시간 : 10분

public class boj_5397 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int L = Integer.parseInt(br.readLine());

        while (L-- > 0) {
            Deque<Character> left = new LinkedList<>();
            Deque<Character> right = new LinkedList<>();
            String line = br.readLine();
            int length = line.length();

            for(int i = 0; i < length; i++) {
                char ch = line.charAt(i);

                if(ch == '<') {
                    if(!left.isEmpty()) {
                        right.addFirst(left.pollLast());
                    }

                } else if(ch == '>') {
                    if(!right.isEmpty()) {
                        left.addLast(right.pollFirst());
                    }
                } else if(ch == '-') {
                    if(!left.isEmpty()) {
                        left.pollLast();
                    }
                } else {
                    left.addLast(ch);
                }
            }

            while(!left.isEmpty()) {
                sb.append(left.pollFirst());
            }
            while(!right.isEmpty()) {
                sb.append(right.pollFirst());
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
