package BOJ.String.Silver;

import java.io.*;
import java.util.Stack;

public class boj_17413 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        Stack<Character> S = new Stack<>();
        String input = br.readLine();
        int length = input.length();

        for(int i = 0; i < length; i++) {
            if(input.charAt(i) == '<') {
                while(true) {
                    char ch = input.charAt(i);
                    if(ch == '>') {
                        sb.append(ch);
                        break;
                    } else {
                        sb.append(ch);
                    }
                    i++;
                }
            } else {
                while(i < length) {
                    char ch = input.charAt(i);
                    if(!S.isEmpty() && (ch == '<' || ch == ' ')) {
                        while(!S.isEmpty()) {
                            char tmp = S.pop();
                            if(tmp == ' ' || tmp == '<') continue;
                            sb.append(tmp);
                        }
                        i--;
                        if(input.charAt(i + 1) == ' ') {
                            sb.append(' ');
                        }
                        break;
                    } else {
                        S.push(ch);
                        i++;
                    }
                }
            }
        }
        while(!S.isEmpty()) {
            char ch = S.pop();
            if(ch == ' ') break;
            sb.append(ch);
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
