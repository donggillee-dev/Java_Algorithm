package SWEA.D4;

import java.io.*;
import java.util.*;

public class Calc2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        HashMap<String, Integer> operHash = new HashMap<>();
        StringBuilder answerSb = new StringBuilder();
        operHash.put("+", 1);
        operHash.put("*", 2);


        for(int tc = 1; tc <= 10; tc++) {
            int N = Integer.parseInt(br.readLine());
            String exp = br.readLine();
            StringBuilder sb = new StringBuilder();
            List<String> prefix = new LinkedList<>();
            Stack<String> operS = new Stack<>();
            Stack<Long> calcS = new Stack<>();

            //후위 연산식으로 만드는 과정
            for(int i = 0; i < N; i++) {
                char ch = exp.charAt(i);

                //연사자를 만나면
                if(ch == '+' || ch == '*') {
                    //저잘해놨던 피연산자를 후위연산식에 넣어줌
                    prefix.add(String.valueOf(sb));
                    sb.delete(0, sb.length());

                    //연산자가 저장되어있는 Stack에서 현재 현산자보다 우선순위가 높은애들 다 꺼내와서 후위연산식에 추가
                    while(!operS.isEmpty() && operHash.get(ch + "") <= operHash.get(operS.peek())) {
                        prefix.add(operS.pop());
                    }
                    operS.push(ch + "");
                } else { //피연산자를 만나면
                    sb.append(ch);
                }
            }

            //마지막 피연산자 넣어줌
            prefix.add(String.valueOf(sb));
            sb.delete(0, sb.length());

            //연산자 스택에 쌓여있는 연산자들 후위 연산식에 추가
            while(!operS.isEmpty()) {
                prefix.add(operS.pop());
            }

            //후위연산식 계산하는 부분
            for(String elem : prefix) {
                //연산자를 만난 경우에는 피연산자 스택에서 두개 꺼내와서 계산
                if(elem.equals("+") || elem.equals("*")) {
                    long value2 = calcS.pop();
                    long value1 = calcS.pop();

                    if(elem.equals("+")) {
                        calcS.push(value1 + value2);
                    } else {
                        calcS.push(value1 * value2);
                    }
                } else { //피연산자의 경우 Stack에 넣어줌
                    calcS.push(Long.parseLong(elem));
                }
            }

            answerSb.append("#" + tc + " ").append(calcS.pop()).append("\n");
        }

        bw.write(String.valueOf(answerSb));
        bw.close();
        br.close();
    }
}
