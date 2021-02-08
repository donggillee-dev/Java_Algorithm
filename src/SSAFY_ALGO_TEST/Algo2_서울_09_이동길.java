package SSAFY_ALGO_TEST;

import java.io.*;
import java.util.StringTokenizer;

public class Algo2_서울_09_이동길 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder answerSb = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        //각 개인 최소 능력치가 M이상
        //팀원 3명의 문제해경응력의 합이 S이

        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int S = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int answer = 0;

        //동아리 수만큼 for-loop
        for(int i = 0; i < N; i++) {
            //각 동아리의 개개인의 점수 정보 입력 받음
            stk = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(stk.nextToken());
            int x2 = Integer.parseInt(stk.nextToken());
            int x3 = Integer.parseInt(stk.nextToken());

            //조건에 만족하는 동아리인 경우에는
            if(x1 >= M && x2 >= M && x3 >= M && (x1 + x2 + x3) >= S) {
                //가입 가능한 동아리 수++
                answer++;
                //학생들 능력치 StringBuilder에 넣어줌
                sb.append(x1 + " " + x2 + " " + x3 + " ");
            }
        }
        //정답 StringBuilder에 동아리 수 먼저 넣어주고
        answerSb.append(answer).append("\n");
        //정답 StringBuilder에다가 능력치 StringBuilder 넣어주고 출력
        answerSb.append(sb).append("\n");
        bw.write(answerSb.toString());
        bw.close();
        br.close();
    }
}
