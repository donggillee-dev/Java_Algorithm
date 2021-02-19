package BOJ.Greedy.Silver;

import java.io.*;
import java.util.StringTokenizer;

public class boj_19539 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int cnt = 0, sum = 0;
        StringTokenizer stk = new StringTokenizer(br.readLine());

        int num;
        while(N --> 0) {
            num = Integer.parseInt(stk.nextToken());
            sum += num;
            if(num % 2 != 0) cnt++;
        }

        if(sum % 3 == 0 && cnt <= sum / 3) System.out.println("YES");
        else System.out.println("NO");
    }
}
