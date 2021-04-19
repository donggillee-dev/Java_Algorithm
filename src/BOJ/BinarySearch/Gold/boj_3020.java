package BOJ.BinarySearch.Gold;

import java.io.*;
import java.util.*;

public class boj_3020 {
    public static void main(String[] args) throws IOException {
        //종유석과 석순의 각 높이순으로 정렬
        //각각에 대해서 이분탐색 ->
        //해당 높이에서 종유석 석순 중 뭐라도 부딪히는 것의 갯수를 알기 위해서는 각 높이에 대한 갯수의 누적합을 사용해야함

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken()), h = Integer.parseInt(stk.nextToken());
        int[] seok = new int[h + 1];
        int[] jong = new int[h + 1];
        int[] seok_sum = new int[h + 1];
        int[] jong_sum = new int[h + 1];

        for(int i = 0; i < n / 2; i++) {
            seok[Integer.parseInt(br.readLine())]++;
            jong[Integer.parseInt(br.readLine())]++;
        }

        //높이에 대해서 이분탐색 해야함
        for(int i = 1; i <= h; i++) {
            seok_sum[i] += seok_sum[i - 1] + seok[i];
            jong_sum[i] += jong_sum[i - 1] + jong[i];
        }

        //높이 x에 대해서 종유석과 석순이 부딪히는 갯수를 누적합으로 계산을 한다
        int ans = 987654321;
        int cnt = 1;
        for(int i = 1; i <= h; i++) {
            int tmp = 0;

            tmp += (seok_sum[h] - seok_sum[i - 1]);
            tmp += (jong_sum[h] - jong_sum[h - i]);
            //구한 누적합을 이용해서 높이 i에 대해 석순은 i - 1만큼의 낮은 애들을 다 빼주고
            //종유석은 h - i 애들에 대해서 빼줘야함

            if(tmp < ans) {
                cnt = 1;
                ans = tmp;
            } else if(tmp == ans) {
                cnt++;
            }
        }

        System.out.println(ans + " " + cnt);
    }
}
