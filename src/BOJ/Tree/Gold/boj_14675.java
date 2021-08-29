package BOJ.Tree.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//Logic
//우선 단절점 -> 연결된 간선이 두개인 점이라면 yes
//단절선 -> 트리에서는 사이클 없음 그러므로 무조건 yes

public class boj_14675 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;

        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] list = new ArrayList[n + 1];

        for(int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < n - 1; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        int q = Integer.parseInt(br.readLine());

        while(q --> 0) {
            stk = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(stk.nextToken());
            int k = Integer.parseInt(stk.nextToken());

            if(t == 2) {
                sb.append("yes\n");
            } else {
                int cnt = list[k].size();

                if(cnt >= 2) {
                    sb.append("yes\n");
                } else {
                    sb.append("no\n");
                }
            }
        }

        System.out.print(sb);
    }
}
