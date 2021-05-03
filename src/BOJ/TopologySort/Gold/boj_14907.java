package BOJ.TopologySort.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14907 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cnt = 1;
        int[] visited = new int[26];
        int[] arr = new int[26];
        int[] degree = new int[26];

        StringTokenizer stk = new StringTokenizer(br.readLine());
        int work = stk.nextToken().charAt(0) - 'A';
        int date = Integer.parseInt(stk.nextToken());
        visited[work]++;
        arr[work] = date;

        while(true) {
            stk = new StringTokenizer(br.readLine());
            work = stk.nextToken().charAt(0) - 'A';
            date = Integer.parseInt(stk.nextToken());
            String pre_work = stk.nextToken();
            int length = pre_work.length();

            cnt++;
            arr[work] = date;

            for(int i = 0; i < length; i++) {
                int ch = pre_work.charAt(i) - 'A';

                if(visited[ch]-- == 0) {
                    cnt--;
                }

                degree[work] |= (1 << ch);
            }

            if(cnt == 0) break;
        }

        System.out.println("test complete");
    }
}
