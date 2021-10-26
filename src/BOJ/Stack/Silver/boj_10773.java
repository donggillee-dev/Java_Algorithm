package BOJ.Stack.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//Logic
//스택으로 풀면 된다

public class boj_10773 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> s = new Stack<>();
        int K = Integer.parseInt(br.readLine());
        int ans = 0;

        while(K -- > 0) {
            int num = Integer.parseInt(br.readLine());

            if(num == 0) s.pop();
            else s.push(num);
        }

        for(int num : s) {
            ans += num;
        }

        System.out.println(ans);
    }
}
