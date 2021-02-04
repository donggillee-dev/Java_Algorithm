package SWEA.D4;
import java.io.*;

public class CuttingPipe {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder answerSb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());


        for(int tc = 1; tc <= T; tc++) {
            StringBuilder sb = new StringBuilder(br.readLine());
            int answer = 0;
            int stick = 0;
            for(int i = 0; i < sb.length(); i++) {
                if(sb.charAt(i) == '(') {
                    if(sb.charAt(i + 1) == ')') {
                        answer += stick;
                        i++;
                    } else {
                        stick++;
                        answer++;
                    }
                } else {
                    stick--;
                }
            }
            answerSb.append("#" + tc + " ").append(answer).append("\n");
        }

        bw.write(String.valueOf(answerSb));
        bw.close();
        br.close();
    }
}


//import java.io.*;
//import java.util.Stack;
//
//public class CuttinPipe {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringBuilder answerSb = new StringBuilder();
//
//        int T = Integer.parseInt(br.readLine());
//
//        for(int i = 0; i < T; i++) {
//            Stack<Character> S = new Stack<>();
//            StringBuilder sb = new StringBuilder();
//            sb.append(br.readLine());
//            int answer = 0;
//
//            boolean isCutted = false;
//
//            for(int j = 0; j < sb.length(); j++) {
//                if(!S.isEmpty()) {
//                    if(sb.charAt(j) == ')' && S.peek() == '(') {
//                        S.pop();
//                        if(!isCutted) {
//                            answer += S.size();
//                            isCutted = true;
//                        } else {
//                            answer++;
//                        }
//                    } else {
//                        isCutted = false;
//                        S.push(sb.charAt(j));
//                    }
//                } else {
//                    isCutted = false;
//                    S.push(sb.charAt(j));
//                }
//            }
//
//            answerSb.append("#" + (i + 1) + " ").append(answer).append("\n");
//        }
//
//        bw.write(String.valueOf(answerSb));
//        bw.close();
//        br.close();
//    }
//}
