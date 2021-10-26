package BOJ.Stack.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//Logic
//스택으로 그때그때 가능한거 비교해서 꺼내주면 됨
//꺼내줄 수 없게끔 되거나 idx가 목표숫자를 만족시키지 못하면 NO 출력

public class boj_1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Integer> s = new Stack<>();
        int stackNum = 1;
        int idx = 0, num = Integer.parseInt(br.readLine());
        int[] arr = new int[num];

        for(int i = 0; i < num; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        while(idx < num && stackNum <= num + 1) {
            if(s.isEmpty() || (!s.isEmpty() && s.peek() != arr[idx])) {
                s.push(stackNum++);
                sb.append("+\n");
            } else if(!s.isEmpty() && s.peek() == arr[idx]) {
                s.pop();
                idx++;
                sb.append("-\n");
            }
        }

        if(idx == num) {
            System.out.print(sb);
        } else {
            System.out.print("NO");
        }
    }
}
