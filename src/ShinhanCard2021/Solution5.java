package ShinhanCard2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution5 {
    private static int stoi(String str) {
        return Integer.parseInt(str);
    }
    private static String solution(int n, int[] arr1, int[] arr2, int[] arr3) {
        StringBuilder sb = new StringBuilder();
        int[] ans = new int[n];
        boolean[] visited = new boolean[n + 1];

        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> noneQ = new LinkedList<>();

        for(int i = 0; i < n; i++) {
            if(arr1[i] == arr2[i]) {
                q.add(arr1[i]);
                visited[arr1[i]] = true;
            } else if(arr1[i] == arr3[i]) {
                q.add(arr1[i]);
                visited[arr1[i]] = true;
            } else if(arr2[i] == arr3[i]) {
                q.add(arr2[i]);
                visited[arr2[i]] = true;
            }
        }

        for(int i = 1; i <= n; i++) {
            if(!visited[i]) {
                noneQ.add(i);
            }
        }

        while(!noneQ.isEmpty()) {
            HashMap<Integer, Boolean> hash = new HashMap<>();
            int elem = noneQ.poll();

            for(int i = 0; i < n; i++) {
                if(arr1[i] == elem && i != 0) {
                    hash.put(arr1[i - 1], false);
                    break;
                }
            }
            for(int i = 0; i < n; i++) {
                if(arr2[i] == elem && i != 0) {
                    hash.put(arr2[i - 1], false);
                    break;
                }
            }
            for(int i = 0; i < n; i++) {
                if(arr2[i] == elem && i != 0) {
                    hash.put(arr3[i - 1], false);
                    break;
                }
            }
            int idx = 0, pos = 0;
            Queue<Integer> tmpQ = new LinkedList<>();

            while(!q.isEmpty()) {
                if(hash.get(q.peek()) != null) {
                    pos = idx;
                }
                tmpQ.add(q.poll());
                idx++;
            }

            idx = 0;

            while(!tmpQ.isEmpty()) {
                if(idx == pos) {
                    q.add(elem);
                } else {
                    q.add(tmpQ.poll());
                }
                idx++;
            }
        }

        while(!q.isEmpty()) {
            sb.append(q.poll()).append(" ");
        }

        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];
        int[] arr3 = new int[n];

        StringTokenizer stk1 = new StringTokenizer(br.readLine());
        StringTokenizer stk2 = new StringTokenizer(br.readLine());
        StringTokenizer stk3 = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            arr1[i] = stoi(stk1.nextToken());
            arr2[i] = stoi(stk2.nextToken());
            arr3[i] = stoi(stk3.nextToken());
        }

        System.out.println(solution(n, arr1, arr2, arr3));
    }
}
