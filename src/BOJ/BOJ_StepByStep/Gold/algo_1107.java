package BOJ.BOJ_StepByStep.Gold;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class algo_1107 {

    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        long answer = Long.MAX_VALUE;
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> broken = new ArrayList<>();
        int channel = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        StringTokenizer stk;
        if(N != 0) {
            stk = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                broken.add(stk.nextToken().charAt(0) - '0');
            }
        }

        //그냥 + - 버튼으로 이동했을시의 횟수를 저장
        answer = Math.abs(100 - channel);
        for(int i = 0; i <= 1000000; i++) {
            int tmp = i;
            int length = 0;
            boolean isBroken = false;
            while(tmp > 0) {
                int rem = tmp % 10;
                if(broken.contains(rem)) {
                    isBroken = true;
                    break;
                }
                length++;
                tmp /= 10;
            }
            //0의 경우에는 length가 구해지지 않는다
            //0이 누를 수 있는 버튼인지도 체크해줘야함
            if(i == 0 && length == 0) {
                length = 1;
                if(broken.contains(0)) {
                    isBroken = true;
                }
            }
            //누를 수 있는 버튼을 눌렀을시에 원하는 채널에 제일 가까운 채널에서 + - 로 이동
            if(!isBroken) {
                int diff = Math.abs(i - channel);
                diff += length;
                answer = Math.min(diff, answer);
            }
        }

        sb.append(answer).append("\n");
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

}