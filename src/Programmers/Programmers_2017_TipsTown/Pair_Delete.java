package Programmers.Programmers_2017_TipsTown;
import java.io.*;
import java.util.*;

public class Pair_Delete {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        int ans = solution(str);

        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static int solution(String str) {
        Stack<Character> S = new Stack<>();

        for(char e : str.toCharArray()) {
            if(!S.isEmpty() && S.peek() == e) {
                S.pop();
            } else {
                S.push(e);
            }
        }
        if(S.isEmpty()) return 1;
        else return 0;
    }
}
