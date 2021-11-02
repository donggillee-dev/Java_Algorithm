package BOJ.Queue.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

//Logic
//디큐로 풀면되는 문제

public class boj_5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringBuilder ans = new StringBuilder();

        while(T --> 0) {
            String cmd = br.readLine();
            int cmdLength = cmd.length();
            boolean flag = true; //true인 경우 정방향
            boolean error = false;
            Deque<Integer> dq = new LinkedList<>();
            int n = Integer.parseInt(br.readLine());

            sb.append(br.readLine());
            sb.delete(sb.length() - 1, sb.length());
            sb.delete(0, 1);

            String[] numArr = sb.toString().split((","));

            for(int i = 0; i < n; i++) {
                dq.add(Integer.parseInt(numArr[i]));
            }

            for(int i = 0; i < cmdLength; i++) {
                if(cmd.charAt(i) == 'R') {
                    flag = !flag;
                } else {
                    if(dq.size() == 0) {
                        error = true;
                        break;
                    }

                    if(flag) {
                        dq.pollFirst();
                    } else {
                        dq.pollLast();
                    }
                }
            }

            if(error) {
                ans.append("error\n");
            } else if(!dq.isEmpty()){
                ans.append("[");

                while(!dq.isEmpty()) {
                    if(flag)
                        ans.append(dq.pollFirst()).append(",");
                    else
                        ans.append(dq.pollLast()).append(",");
                }

                ans.delete(ans.length() - 1, ans.length());
                ans.append("]\n");
            } else if(dq.isEmpty()) {
                ans.append("[]\n");
            }

            sb.setLength(0);
        }

        System.out.print(ans);
    }
}
