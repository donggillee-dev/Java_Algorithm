package BOJ.Greedy.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Logic
//만들어진 문자열의 맨뒤가 B이면 삭제하고 문자열 뒤집어주고
//A이면 그냥 뺴주고
//한마디로 B -> A를 역추적해간다고 생각하면 된다

//풀이 시간 : 35분

public class boj_12904 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringBuilder sb = new StringBuilder(br.readLine());
        int length = str.length();

        while(sb.length() != length) {
            if(sb.charAt(sb.length() - 1) == 'B') {
                sb.setLength(sb.length() - 1);
                sb.reverse();
            } else {
                sb.setLength(sb.length() - 1);
            }
        }

        if(str.equals(sb.toString())) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}
