package BOJ.Stack.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//Logic
//스택으로 풀면 된다
//다만 마지막에 [ ( 같이 끝나는 경우 no 출력 처리 필요(스택 안 비어있는 경우)

public class boj_4949 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            String str = br.readLine();

            if(str.charAt(0) == '.') break;

            int length = str.length();
            boolean flag = true;
            Stack<Character> s = new Stack<>();

            for(int i = 0; i < length; i++) {
                if(str.charAt(i) == ']') {
                    if(!s.isEmpty() && s.peek() == '[') {
                        s.pop();
                    } else {
                        flag = false;
                        break;
                    }
                } else if(str.charAt(i) == ')') {
                    if(!s.isEmpty() && s.peek() == '(') {
                        s.pop();
                    } else {
                        flag = false;
                        break;
                    }
                } else if(str.charAt(i) == '[' || str.charAt(i) == '(') {
                    s.push(str.charAt(i));
                }
            }

            if(!flag || !s.isEmpty()) {
                sb.append("no");
            } else {
                sb.append("yes");
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }
}
