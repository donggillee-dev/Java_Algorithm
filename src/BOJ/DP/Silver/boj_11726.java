package BOJ.DP.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//Logic
//n이 3인 모양의 직사각형은 n이1 떄와 n이 2일떄를 합쳐서 만들 수 있는 모양이다
//풀이 시간 : 25분
public class boj_11726 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        if(n == 1) {
            System.out.println(1);
        } else if(n == 2) {
            System.out.println(2);
        } else {
            int[] answer = new int[n + 1];
            answer[1] = 1;
            answer[2] = 2;

            for(int i = 3; i <= n; i++) {
                answer[i] = (answer[i - 1] + answer[i - 2]) % 10007;
            }
            System.out.println(answer[n]);
        }
    }
}
