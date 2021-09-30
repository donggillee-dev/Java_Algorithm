package ShinhanCard2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Solution2 {
    private static HashMap<Character, Integer> initAnsHash() {
        HashMap<Character, Integer> hash = new HashMap<>();

        hash.put('W', 0);
        hash.put('B', 0);

        return hash;
    }
    private static HashMap<Character, Boolean> initPossHash(String str) {
        HashMap<Character, Boolean> possHash = new HashMap<>();
        boolean[] visited = new boolean[26];
        int length = str.length();

        visited[str.charAt(0) - 'a'] = true;
        possHash.put(str.charAt(0), true);

        for(int i = 1; i < length; i++) {
            if(str.charAt(i) != str.charAt(i - 1)) {
                if(visited[str.charAt(i) - 'a']) {
                    possHash.put(str.charAt(i), false);
                } else {
                    visited[str.charAt(i) - 'a'] = true;
                    possHash.put(str.charAt(i), true);
                }
            }
        }

        return possHash;
    }
    private static char getAns(String str) {
        HashMap<Character, Integer> hash = initAnsHash();
        HashMap<Character, Boolean> possHash = initPossHash(str);

        for(char key : possHash.keySet()) {
            if(possHash.get(key)) {
                if((key - 'a') % 2 != 0) {
                    hash.put('B', hash.get('B') + 1);
                } else {
                    hash.put('W', hash.get('W') + 1);
                }
            }
        }

        if(hash.get('B') > hash.get('W')) return 'B';
        else return 'W';
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        System.out.println(getAns(str));
    }
}
