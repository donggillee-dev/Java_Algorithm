package BOJ.KMP.Platinum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Logic
//pi배열의 끝 값을 가지고 풀어보자는 생각을 계속 팠으면 빨리 풀 수 있었던 문제
//pi 배열의 끝값 => 반복되는 문자열 중 가장 긴 값
//해당 문자열이 겹쳐서 반복되는 문자열이라면 전체 문자열 - 패턴 => 이게 a
//아닌 경우도 생각해줘야함 => 1이 출력되는 경우

//풀이 시간 : 1시간
public class boj_4354 {
    private static int makeTable(String str) {
        int length = str.length();
        int[] pi = new int[length];

        for (int i = 1, j = 0; i < length; i++) {
            while (j > 0 && str.charAt(j) != str.charAt(i)) {
                j = pi[j - 1];
            }

            if (str.charAt(j) == str.charAt(i)) {
                pi[i] = ++j;
            }
        }

        return pi[length - 1]; //문자열에서 가장 긴 반복되는 접두의 길이를 리턴
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String input = br.readLine();
            int length = input.length();
            if (input.charAt(0) == '.') break;

            //반복되는 문자열 길이는 문자열 길이 - 가장 긴 접두의 길이
            //n은 문자열 길이에서 반복되는 문자열 길이 나눈거
            int patLength = makeTable(input);
            int rem = length % (length - patLength);

            if(rem == 0) {
                sb.append(length / (length - patLength)).append("\n");
            } else {//다만 딱 나누어 떨어지는 경우와 그렇지 않는 경우 존재
                sb.append(1).append("\n");
            }
        }

        System.out.print(sb);
    }
}