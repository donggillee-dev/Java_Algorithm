package BOJ.BOJ_StepByStep.Silver;

import java.io.*;
import java.util.Stack;

public class algo_2504 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        Stack<String> S = new Stack<>();
        String input = br.readLine();
        boolean flag = true;

        loop:for(int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            int tmp = 0;
            switch(ch) {
                case '(':
                    S.push(ch + "");
                    break;
                case ')':
                    if(S.isEmpty() || S.peek().equals("[")) {
                        flag = false;
                        break loop;
                    } else if(!S.isEmpty() && S.peek().equals("(")){
                        S.pop();
                        if(!S.isEmpty() && !S.peek().equals("(") && !S.peek().equals("[")) {
                            tmp = Integer.parseInt(S.pop());
                            tmp += 2;
                            S.push(tmp + "");
                        } else {
                            S.push(2 + "");
                        }
                    } else {
                        tmp = Integer.parseInt(S.pop());
                        if(S.isEmpty() || !S.peek().equals("(")) {
                            flag = false;
                            break loop;
                        } else {
                            S.pop();
                            tmp *= 2;
                            if(!S.isEmpty() && !S.peek().equals("(") && !S.peek().equals("[")) {
                                tmp += Integer.parseInt(S.pop());
                                S.push(tmp + "");
                            } else {
                                S.push(tmp + "");
                            }
                        }
                    }
                    break;
                case '[':
                    S.push(ch + "");
                    break;
                case ']':
                    if(S.isEmpty() || S.peek().equals("(")) {
                        flag = false;
                        break loop;
                    } else if(S.peek().equals("[")){
                        S.pop();
                        if(!S.isEmpty() && !S.peek().equals("(") && !S.peek().equals("[")) {
                            tmp = Integer.parseInt(S.pop());
                            tmp += 3;
                            S.push(tmp + "");
                        } else {
                            S.push(3 + "");
                        }
                    } else {
                        tmp = Integer.parseInt(S.pop());
                        if(S.isEmpty() || !S.peek().equals("[")) {
                            flag = false;
                            break loop;
                        } else {
                            S.pop();
                            tmp *= 3;
                            if(!S.isEmpty() && !S.peek().equals("(") && !S.peek().equals("[")) {
                                tmp += Integer.parseInt(S.pop());
                                S.push(tmp + "");
                            } else {
                                S.push(tmp + "");
                            }
                        }
                    }
                    break;
            }

        }
        if(S.isEmpty() || S.size() > 1 || S.peek().equals("(") || S.peek().equals(")") || S.peek().equals("]") || S.peek().equals("["))
            flag = false;

        if(!flag) {
            sb.append(0);
        } else {
            sb.append(S.pop());
        }

        bw.write(String.valueOf(sb));
        bw.close();
        br.close();
    }
}