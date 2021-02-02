package Programmers.Programmers_2018_kakao;
import java.io.*;

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
        int[] Score = {0, 1, 1, 1};//첫번째가 스타상인 경우를 위해 idx1부터 score시작

        for(int i = 0; i < dartResult.length(); i++) {
            char ch = dartResult.charAt(i);
            if((int)ch >= '0' && (int)ch <= '9'){
                if(flag) {//두자릿수인 10을 검증
                    Score[idx] = 10;
                } else {//그 외의 경우에는 한자리수니까 그냥 대입
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

