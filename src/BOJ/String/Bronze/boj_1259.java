package BOJ.String.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Logic
//문자열 뒤집는 문제
//StringBuilder의 reverse를 호출하면 빌더에 들어있는 문자열이 아예 바뀜

public class boj_1259 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringBuilder tmp = new StringBuilder();

        while(true) {
            tmp.append(br.readLine());

            if(Integer.parseInt(tmp.toString()) == 0) break;

            String origin = tmp.toString();
            tmp.reverse();

            if(tmp.toString().equals(origin)) {
                sb.append("yes\n");
            } else {
                sb.append("no\n");
            }

            tmp.setLength(0);
        }

        System.out.print(sb);
    }
}
