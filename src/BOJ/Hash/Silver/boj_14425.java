package BOJ.Hash.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj_14425 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int ans = 0;

        HashSet<String> set = new HashSet<>();

        for(;N>0;N--) {
            set.add(br.readLine());
        }

        for(;M>0;M--) {
            if(set.contains(br.readLine())) ans++;
        }

        System.out.println(ans);
    }
}
