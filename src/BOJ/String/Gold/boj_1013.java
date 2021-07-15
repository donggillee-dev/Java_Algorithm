package BOJ.String.Gold;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

//Pattern, Matcher 클래스를 사용하면 되는 문제
//Pattern.compile로 패턴 클래스 생성
//Matches 클래스로 문자열에 대해 pattern.matcher로 가져옴

//다시 풀어본 풀이 시간 : 5분

public class boj_1013 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile("((100+1+)|(01))+");
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            Matcher matcher = pattern.matcher(br.readLine());
            if(matcher.matches()) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");
        }

        System.out.print(sb);
    }
}
