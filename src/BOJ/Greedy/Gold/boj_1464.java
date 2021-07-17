package BOJ.Greedy.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Logic
//앞에서부터 그 다음 문자를 비교하면서 스트링 빌더의 앞에 넣어줄지 뒤에 넣어줄지 생각하면 되는 문제
//큰 애는 빌더의 맨 앞에 => 뒤집어주는 과정
//풀이 시간 : 15분

public class boj_1464 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int length = str.length();
        StringBuilder sb = new StringBuilder();

        sb.append(str.charAt(0));
        for(int i = 1; i < length; i++) {
            if(sb.charAt(i - 1) < str.charAt(i)) {
                sb.insert(0, str.charAt(i));
            } else {
                sb.append(str.charAt(i));
            }
        }

        if(sb.charAt(0) > sb.charAt(length - 1)) sb.reverse();
        System.out.println(sb);
    }
}
