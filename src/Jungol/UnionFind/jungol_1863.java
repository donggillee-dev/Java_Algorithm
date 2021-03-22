package Jungol.UnionFind;

import java.io.*;
import java.util.*;

public class jungol_1863 {
    private static int[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        tree = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            tree[i] = i;
        }
        while(M --> 0) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());

            union(a, b);
        }

        HashMap<Integer, Integer> hash = new HashMap<>();
        int answer = 0;
        for(int i = 1; i <= N; i++) {
            int node = findParent(i);
            if(!hash.containsKey(node)) {
                hash.put(node, 0);
                answer++;
            }
        }
        System.out.println(answer);
    }
    private static int findParent(int x) {
        if(tree[x] == x) return x;
        return tree[x] = findParent(tree[x]);
    }
    private static void union(int a, int b) {
        int parent1 = findParent(a);
        int parent2 = findParent(b);

        if(parent1 > parent2) tree[parent1] = parent2;
        else tree[parent2] = parent1;
    }
}
