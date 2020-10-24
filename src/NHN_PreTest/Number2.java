package NHN_PreTest;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class Number2 {
    public static void main(String[] args) throws IOException {
        String[] arr = {"2(B(R)2(G))1(B2(B(R)))"};
        solution(1, arr);
    }
    private static void solution(int numOfOrder, String[] orderArr) throws IOException {
        Stack<Character> S = new Stack<>();
        Queue<String> answerQueue = new LinkedList<>();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < numOfOrder; i++) {
            String order = orderArr[i];
            String innerStr = "";
            for(int j = 0; j < order.length(); j++) {
                char order_ch = order.charAt(j);
                boolean isInner = false;
                if(order_ch == ')') {
                    char pop_ch;
                    while(true) {
                        pop_ch = S.pop();
                        if(pop_ch == '(') {
                            isInner = true;
                            break;
                        } else if(Character.isDigit(pop_ch)){
                            break;
                        } else innerStr = pop_ch + innerStr;
                    }
                    if(isInner) {

                    } else {

                    }
                } else {
                    S.push(order_ch);
                }
            }
        }
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        return;
    }
}
