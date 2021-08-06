package BOJ.Greedy.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

//Logic
//답을 보고 푼 문제
//다시 풀어볼 것
//앞에서부터 숫자 하나씩 넣고 스택의 맨 위보다 큰 숫자면은 맨 앞으로 보내는게 좋으니까 스택에서 계속 빼준다
//다만 빼줄때 지울 수 있는 k만큼만 빼줘야함

//풀이 시간 : 2시간

public class boj_2812 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken()), k = Integer.parseInt(stk.nextToken());
        int rem = 0;
        String str = br.readLine();
        Stack<Integer> s = new Stack<>();

        for(int i = 0; i < n; i++) {
            int num = str.charAt(i) - '0';

            while(rem < k && !s.isEmpty() && s.peek() < num) {
                s.pop();
                rem++;
            }

            s.add(num);
        }

        for(int i = 0; i < n - k; i++) {
            sb.append(s.get(i));
        }

        System.out.println(sb);
    }
}
