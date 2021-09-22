package BOJ.Greedy.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Logic
//가장 적은 숫자 더미들을 뒤집어 주면 되는 문제
//각 숫자더미는 문자열 순차탐색에서 바뀌는 부분으로 체크해주면 된다

public class boj_1439 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int zero = 0, one = 0, length = str.length();

        if(str.charAt(0) == '0') zero++;
        else one++;

        for(int i = 1; i < length; i++) {
            if(str.charAt(i) != str.charAt(i - 1)) {
                if(str.charAt(i) == '1') one++;
                else zero++;
            }
        }

        System.out.println(Math.min(zero, one));
    }
}
