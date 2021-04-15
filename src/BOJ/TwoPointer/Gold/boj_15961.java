package BOJ.TwoPointer.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//Logic
//슬라이딩 윈도우로 풀면 되는 문제
//대신 쿠폰여부에 대해서 체크할떄 쿠폰초밥을 먹는게 초밥 자체 cnt를 증가시켜줄 필요는 없음
//걔는 주방장이 임시적으로 주는거니까 중복 제거 cnt에 1을 더해봐서 정답과 같은지만 비교하면됨

//풀이 시간 : 20분
public class boj_15961 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int ans = 0;
        int N = Integer.parseInt(stk.nextToken());
        int D = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());
        int C = Integer.parseInt(stk.nextToken());
        int[] chobab = new int[3001];
        int[] arr = new int[N + K];

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        for(int i = N; i < arr.length; i++) {
            arr[i] = arr[i - N];
        }

        int start = 0;
        int end = K - 1;
        int tmp = 0;
        for(int i = start; i <= end; i++) {
            if(chobab[arr[i]] == 0) {
                tmp++;
            }
            chobab[arr[i]]++;
        }

        if(chobab[C] == 0) {
            ans = Math.max(ans, tmp + 1);
        } else {
            ans = Math.max(ans, tmp);
        }

        int length = arr.length;
        while(end < length) {
            chobab[arr[start]]--;
            if(chobab[arr[start]] == 0) tmp--;
            start++;
            end++;
            if(end == length) break;
            if(chobab[arr[end]] == 0) tmp++;
            chobab[arr[end]]++;
            if(chobab[C] == 0) {
                ans = Math.max(ans, tmp + 1);
            } else {
                ans = Math.max(ans, tmp);
            }
        }

        System.out.println(ans);
    }
}
