package BOJ.IMP.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//구간에 대해서 반복문을 돌아주면서 벨소리가 울리는 타이밍을 mod연산
//추가적인 시간이 오래걸린 이유는 노래가 다 끝나고 난 이후의 벨소리 울리는 처리를 이상하게 해줘서...
//풀이 시간 : 14분

public class boj_1333 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int l = Integer.parseInt(stk.nextToken());
        int d = Integer.parseInt(stk.nextToken());

        int start = 0;
        int end = 0;

        for(int i = 1; i <= n; i++) {
            end = start + l;
            start = end + 5;

            for(int j = end; j < start; j++) {
                if(j % d == 0) {
                    System.out.println(j);
                    return;
                }
            }
        }
        for(int i = start; ; i++) {
            if(i % d == 0) {
                System.out.println(i);
                return;
            }
        }
    }
}
