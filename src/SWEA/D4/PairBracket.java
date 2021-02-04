package SWEA.D4;

import java.io.*;
import java.util.Stack;

public class PairBracket {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Character> S = new Stack<>();
        StringBuilder answerSb = new StringBuilder();
        StringBuilder sb = new StringBuilder();

        for(int i = 1; i < 11; i++) {
            int N = Integer.parseInt(br.readLine());
            sb.append(br.readLine());

            for(int j = 0; j < N; j++) {
                if(S.isEmpty()) {
                    S.push(sb.charAt(j));
                } else {
                    char ch = S.peek();
                    if(sb.charAt(j) == ')' && ch == '(' ||
                        sb.charAt(j) == '}' && ch == '{' ||
                        sb.charAt(j) == '>' && ch =='<' ||
                        sb.charAt(j) == ']' && ch == '[') {
                        S.pop();
                    } else {
                        S.push(sb.charAt(j));
                    }

                }
            }
            answerSb.append("#" + i + " " );
            if(S.size() == 0) answerSb.append("1");
            else answerSb.append("0");

            answerSb.append("\n");
            S.clear();
            sb.delete(0, sb.length());
        }
        bw.write(String.valueOf(answerSb));
        bw.close();
        br.close();
    }
}
