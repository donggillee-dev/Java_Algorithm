package Programmers.Programmers_Lv2;

import java.io.*;

public class BinaryTransfer {
    public static void main(String[] args) throws IOException {
        //** 로직 두개를 구현하면 된다
        //1. x의 모든 0을 제거하는 로직
        //2. x의 길이를 c라 했을시 c 를 이진법으로 변환하는 로직
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int[] answer = new int[2];

        sb.append(br.readLine());
        //x의 모든 0을 제거하는 로직
        while(sb.length() > 1) {
            for(int i = 0; i < sb.length(); i++) {
                if(sb.charAt(i) == '0') {
                    sb.delete(i, i + 1);
                    i--;
                    answer[1]++;
                }
            }

            int num = sb.length();
            sb.delete(0, sb.length());
            while(num > 1) {
                sb.insert(0, num % 2);
                num /= 2;
            }
            if(num == 1) sb.insert(0, 1);
            answer[0]++;
        }
        sb.delete(0, sb.length());
        sb.append(answer[0] + " " + answer[1]).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}
