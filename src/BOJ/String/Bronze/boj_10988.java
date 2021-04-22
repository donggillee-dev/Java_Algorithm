package BOJ.String.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//Logic
//문자열 앞뒤 인덱스로 일치여부 확인하는 반복문 돌면됨
//풀이 시간 : 3분
public class boj_10988 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        for(int start = 0, end = str.length() - 1; end >= start; end--, start++) {
            if(str.charAt(start) != str.charAt(end)) {
                System.out.print(0);
                return;
            }
        }
        System.out.print(1);
    }
}
