package BOJ.DivideAndConquer.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//이전에 풀었던 문제와 비슷해서 (boj_4256) 풀이를 응용해서 풀어보았지만 풀리지 않아서 답지를 봄
//이런 문제는 전위, 중위, 후위 탐색 방식에 대해서 인지하고 그림으로 그려봐야 하는 문제이다
//풀이 시간 : 1시간

public class boj_2263 {
    private static int n;
    private static int[] idx;
    private static int[] in;
    private static int[] post;
    private static StringBuilder ans;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ans = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        idx = new int[n + 1];
        in = new int[n + 1];
        post = new int[n + 1];
        StringTokenizer stk = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            in[i] = Integer.parseInt(stk.nextToken());
            idx[in[i]] = i;
        }

        stk = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            post[i] = Integer.parseInt(stk.nextToken());
        }
    }

    private static void pre(int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) return;

        int rootIdx = idx[post[postEnd]];
        int leftSize = rootIdx - inStart;
        int rightSize = inEnd - rootIdx;

        ans.append(in[rootIdx]).append(" ");

        pre(inStart, rootIdx - 1, postStart, postStart + leftSize - 1);
        pre(rootIdx + 1, inEnd, postEnd - rightSize, postEnd - 1);

    }

    public static void main(String[] args) throws IOException {
        input();
        pre(1, n, 1, n);

        System.out.print(ans);
    }
}
