package BOJ.UnionFind.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//유니온 파인드로 풀 수 있는 문제
//연산 0일 경우 합쳐주고
//연산 1일 경우에는 각 부모를 찾아만 와야함
//풀이 시간 : 10분

public class boj_1717 {
    private static int find(int x, int[] p) {
        if(x == p[x]) return x;
        else return p[x] = find(p[x], p);
    }
    private static void unionFind(int op, int a, int b, int[] p, StringBuilder sb) {
        a = find(a, p);
        b = find(b, p);

        if(op == 1) {
            if(a == b) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");
        } else {
            if(a != b) p[b] = a;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        int[] p = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            p[i] = i;
        }

        for(int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(stk.nextToken());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());

            unionFind(op, a, b, p, sb);
        }
        System.out.print(sb);
    }
}
