package BOJ.Greedy.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//지문이 난해해서 많이 헷갈렸던 문제
//가장 큰 가격 => 최대 이익을 볼 곳
//이 가격을 기준으로 이전에 사놓은 자잘한 주식들을 처분 => 그때 산 가격 기준
//고로 뒤에서부터 최대를 찾아가면서 최대값을 갱신해주고 그 전의 애들은 그냥 뺄셈을 통해 손익 실현

public class boj_11501 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while(t --> 0) {
            int n = Integer.parseInt(br.readLine()), max = 0;
            long sum = 0;
            int[] stock = new int[n];
            StringTokenizer stk = new StringTokenizer(br.readLine());

            for(int i = 0; i < n; i++) {
                stock[i] = Integer.parseInt(stk.nextToken());
            }

            for(int i = n - 1; i >= 0; i--) {
                if(max < stock[i]) {
                    max = stock[i];
                } else {
                    sum += (max - stock[i]);
                }
            }

            sb.append(sum).append("\n");
        }

        System.out.print(sb);
    }
}
