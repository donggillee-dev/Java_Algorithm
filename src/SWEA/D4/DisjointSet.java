package SWEA.D4;

import java.io.*;
import java.util.*;

public class DisjointSet {
    static int[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TestCase = Integer.parseInt(br.readLine());
        StringTokenizer stk;

        for(int tc = 1; tc <= TestCase; tc++) {
            stk = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(stk.nextToken());
            int M = Integer.parseInt(stk.nextToken());
            tree = new int[N + 1];
            sb.append("#" + tc + " ");

            for(int i = 1; i <= N; i++) {
                tree[i] = i;
            }

            for(int i = 0; i < M; i++) {
                stk = new StringTokenizer(br.readLine());
                int oper = Integer.parseInt(stk.nextToken());
                int a = Integer.parseInt(stk.nextToken());
                int b = Integer.parseInt(stk.nextToken());

                if(oper == 0) {
                    Union(a, b);
                } else {
                    if(findParent(a) == findParent(b)) {
                        sb.append(1);
                    } else {
                        sb.append(0);
                    }
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    private static int findParent(int start) {
        if(tree[start] == start) return start;
        else return tree[start] = findParent(tree[start]);
    }
    private static void Union(int a, int b) {
        int parent1 = findParent(a);
        int parent2 = findParent(b);

        if(parent1 == parent2) return;
        tree[parent2] = parent1;
    }
}
