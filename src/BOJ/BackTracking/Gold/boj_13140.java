package BOJ.BackTracking.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

//Logic
//이거보다 더 빠른 방법이 존재할듯?
//Hash를 이용해서 풀었는데 더 빠른 방법 존재할듯

//풀이 시간 : 50분

public class boj_13140 {
    private static int input;
    private static int num1, num2;
    private static char[] arr = {'d', 'e', 'h', 'l', 'o', 'r', 'w'};
    private static boolean[] visited = new boolean[10];
    private static void initHash(HashMap<Character, Integer> hash) {
        hash.put('d', 0);
        hash.put('e', 0);
        hash.put('h', 0);
        hash.put('l', 0);
        hash.put('o', 0);
        hash.put('r', 0);
        hash.put('w', 0);
    }

    private static int getNum1(HashMap<Character, Integer> hash) {
        int num = hash.get('h');
        num *= 10;
        num += hash.get('e');
        num *= 10;
        num += hash.get('l');
        num *= 10;
        num += hash.get('l');
        num *= 10;
        num += hash.get('o');

        return num;
    }

    private static int getNum2(HashMap<Character, Integer> hash) {
        int num = hash.get('w');
        num *= 10;
        num += hash.get('o');
        num *= 10;
        num += hash.get('r');
        num *= 10;
        num += hash.get('l');
        num *= 10;
        num += hash.get('d');

        return num;
    }
    private static void makeAns(StringBuilder sb) {
        sb.append("  ").append(num1).append("\n");
        sb.append("+ ").append(num2).append("\n");
        sb.append("-------").append("\n");

        String tmp = "" + input;
        int size = tmp.length();

        for(int i = 0; i < 7 - size; i++) {
            sb.append(" ");
        }

        sb.append(input);
    }
    private static void dfs(int depth, int idx, HashMap<Character, Integer> hash) {
        if (depth == 7) {
            int tmp1 = getNum1(hash);
            int tmp2 = getNum2(hash);
            if(tmp1 > input || tmp2 > input) return;
            if (input == (tmp1 + tmp2)) {
                num1 = tmp1;
                num2 = tmp2;
            }
            return;
        }

        char key = arr[idx];

        if (key == 'w' || key == 'h') {
            for (int i = 1; i <= 9; i++) {
                if(!visited[i]) {
                    visited[i] = true;
                    hash.put(key, i);
                    dfs(depth + 1, idx + 1, hash);
                    visited[i] = false;
                }
            }
        } else {
            for (int i = 0; i <= 9; i++) {
                if(!visited[i]) {
                    visited[i] = true;
                    hash.put(key, i);
                    dfs(depth + 1, idx + 1, hash);
                    visited[i] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        HashMap<Character, Integer> hash = new HashMap<>();
        initHash(hash);
        input = Integer.parseInt(br.readLine());
        dfs(0, 0, hash);
        if(num1 != 0) {
            makeAns(sb);
        } else {
            sb.append("No Answer");
        }

        System.out.println(sb);
    }
}
