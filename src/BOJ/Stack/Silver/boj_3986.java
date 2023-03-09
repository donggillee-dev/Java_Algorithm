package BOJ.Stack.Silver;

import java.io.*;
import java.util.Stack;

public class boj_3986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Character> s = new Stack<>();

        int N = Integer.parseInt(br.readLine());
        int ans = 0;

        for (int i = 0; i < N; i++) {
            sb.append(br.readLine());
            int len = sb.length();

            for (int j = 0; j < len; j++) {
                char ch = sb.charAt(j);

                if (!s.isEmpty() && s.peek() == ch) {
                    s.pop();
                    continue;
                }
                s.push(ch);
            }

            if(s.isEmpty()) ans++;

            s.clear();
            sb.setLength(0);
        }

        System.out.println(ans);
        br.close();
    }
}
