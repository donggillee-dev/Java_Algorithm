package BOJ.Stack.Silver;

import java.io.*;
import java.util.StringTokenizer;

public class boj_10828 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;
        int[] Stack = new int[10001];
        int peek = 0;
        int N = Integer.parseInt(br.readLine());
        String input;

        while(N --> 0) {
            input = br.readLine();

            switch(input.charAt(0)) {
                case 'p':
                    if(input.charAt(1) == 'u') {
                        Stack[peek++] = Integer.parseInt(input.substring(5));
                    } else {
                        if(peek == 0) {
                            sb.append(-1);
                        } else {
                            sb.append(Stack[--peek]);
                        }
                        sb.append("\n");
                    }
                    break;
                case 's':
                    sb.append(peek).append("\n");
                    break;
                case 'e':
                    if(peek == 0) {
                        sb.append(1);
                    } else {
                        sb.append(0);
                    }
                    sb.append("\n");
                    break;
                case 't':
                    if(peek == 0) {
                        sb.append(-1);
                    } else {
                        sb.append(Stack[peek - 1]);
                    }
                    sb.append("\n");
                    break;
            }
        }
        bw.write(String.valueOf(sb));
        bw.close();
        br.close();
    }
}
