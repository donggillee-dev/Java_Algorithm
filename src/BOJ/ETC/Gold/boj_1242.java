package BOJ.ETC.Gold;

import java.io.*;
import java.util.*;
//Logic
//정수론을 이용하여 해결할 수 있는 문제
//동호의 인덱스가 중요하다
//제거되는 사람은 start인덱스에서 K - 1을 더해 N으로 나누어주었을때 나오는 인덱스값
//한명씩 제거될때마다 제거된 사람의 인덱스를 start로 잡고
//전체 인원은 한명 줄이고 횟수는 1회 늘리고
//제거되는 사람이 동호보다 앞에있으면 동호의 인덱스는 1 줄어들어야힘

public class boj_1242 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken()); //사람의 수
        int K = Integer.parseInt(stk.nextToken()); //변하지 않는 값
        int M = Integer.parseInt(stk.nextToken()) - 1; //동호의 인덱스

        // 1 - n -> 0 - (n - 1) 를 통해 mod연선을 쉽게 하기 위해 동호의 인덱스 줄임

        int start = 0;
        int ans = 1;

        while(true) {
            int rem = ( start + K - 1 ) % N;

            if(rem == M) break;

            if(rem < M) { //지워지는 사람이 동호보다 앞에 있다
                M -= 1;
            }

            start = rem;
            N -= 1;
            ans += 1;
        }

        System.out.print(ans);
    }
}
