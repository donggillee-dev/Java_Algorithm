package Programmers_2017_TipsTown;
import java.io.*;
import java.util.*;

public class Word_Puzzle {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());

        String[] strs = new String[stk.countTokens()];
        String t = br.readLine();
        int ans = 0;

        for(int i = 0; i < strs.length; i++) {
            strs[i] = stk.nextToken();
        }


        ans = solution(strs, t);
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static int solution(String[] strs, String t) {
        int answer = 0;

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("Hello Java");

        return answer;
    }
}
