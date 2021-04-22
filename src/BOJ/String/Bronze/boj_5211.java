package BOJ.String.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Logic
//문자열 처리에 있어서 split함수를 쓸때 특수문자의 경우 \\붙이기 필요
//프로그래머스에서 마주쳤으면 한참동안 고민했을문제...
//풀이 시간 : 27분

public class boj_5211 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c_major = 0, a_minor = 0;
        String[] arr = br.readLine().split("\\|");
        int length = arr.length;
        char ch = '\0';
        for(int i = 0; i < length; i++) {
            ch = arr[i].charAt(0);
            if(isC(ch)) c_major++;
            else if(isA(ch)) a_minor++;
        }

        if(c_major == a_minor) {
            if(isC(ch)) System.out.println("C-major");
            else if(isA(ch)) System.out.println("A-minor");
        } else {
            if(c_major > a_minor) System.out.println("C-major");
            else System.out.println("A-minor");
        }
    }
    private static boolean isA(char ch) {
        if(ch == 'A' || ch == 'D' || ch == 'E') return true;
        return false;
    }
    private static boolean isC(char ch) {
        if(ch == 'C' || ch == 'F' || ch == 'G') return true;
        return false;
    }
}
