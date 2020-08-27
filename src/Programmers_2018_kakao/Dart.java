package Programmers_2018_kakao;
import java.io.*;
import java.util.*;

public class Dart {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();

        int res = solution(str);

        sb.append(res).append("\n");
        bw.write(String.valueOf(sb));

        bw.flush();
        bw.close();
        br.close();
        return;
    }
    static int solution(String dartResult) {
        int answer = 0;
        int idx = 0;
        boolean flag = false;
        int[] Score = {0, 1, 1, 1};

        for(int i = 0; i < dartResult.length(); i++) {
            char ch = dartResult.charAt(i);
            if((int)ch >= '0' && (int)ch <= '9'){
                if(flag) {
                    Score[idx] = 10;
                } else {
                    idx++;
                    Score[idx] = ch - '0';
                    flag = true;
                }
            }
            else {
                flag = false;
                if(ch == 'S');
                else if(ch == 'D')
                    Score[idx] = (int)Math.pow(Score[idx], 2);
                else if(ch == 'T')
                    Score[idx] = (int)Math.pow(Score[idx], 3);
                else if(ch == '*') {
                    Score[idx] *= 2;
                    Score[idx - 1] *= 2;
                } else
                    Score[idx] *= -1;
            }
        }
        for(int i = 1; i < 4; i++) answer += Score[i];

        return answer;
    }
}

