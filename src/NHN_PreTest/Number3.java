package NHN_PreTest;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class Number3 {
    public static void main(String[] args) throws IOException {
        String[] arr = {"2(B(R)2(G))1(B2(B(R)))"};
        solution(1, arr);
    }
    private static void solution(int numOfOrder, String[] orderArr) throws IOException {
        Stack<Character> S = new Stack<>();
        Stack<Character> answerStack = new Stack<>();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < numOfOrder; i++) {
            String order = orderArr[i];
            String innerStr = "";
            String answer = "";
            for(int j = 0; j < order.length(); j++) {
                char order_ch = order.charAt(j);
                if (order_ch == ')') {
                    char pop_ch;
                    while (true) {
                        pop_ch = S.pop();
                        if (pop_ch == '(') break;
                        else innerStr = pop_ch + innerStr;
                    }
                    pop_ch = S.pop();
                    if (Character.isDigit(pop_ch)) {
                        String tmp = "";
                        for (int idx = 0; idx < pop_ch - '0'; idx++) {
                            tmp += innerStr;
                        }
                        for (int idx = 0; idx < tmp.length(); idx++) {
                            S.push(tmp.charAt(idx));
                        }
                    } else {
                        String tmp = "";
                        for (int idx = 0; idx < innerStr.length(); idx++) {
                            tmp += pop_ch;
                            tmp += innerStr.charAt(idx);
                        }
                        for (int idx = 0; idx < tmp.length(); idx++) {
                            S.push(tmp.charAt(idx));
                        }
                    }
                } else {
                    S.push(order_ch);
                }
                innerStr = "";
            }
            while(!S.isEmpty()) {
                answerStack.add(S.pop());
            }

            while(!answerStack.isEmpty()) {
                char ch_pop = answerStack.pop();

                if(Character.isDigit(ch_pop)) {
                    char ch = answerStack.pop();
                    for(int idx = 0; idx < ch_pop - '0'; idx++) {
                        answer += ch;
                    }
                } else answer += ch_pop;

            }
            System.out.println(answer);
        }
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        return;
    }
}
