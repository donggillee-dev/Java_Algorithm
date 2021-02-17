package BOJ.Hash.Silver;

import java.io.*;
import java.util.HashSet;

public class boj_11478 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<String> hash = new HashSet<>();
        String input = br.readLine();
        int length = input.length();

        for(int i = 0; i < length; i++) {
            for(int j = i; j < length; j++) {
                hash.add(input.substring(i, j + 1));
            }
        }

        System.out.println(hash.size());
    }
}
