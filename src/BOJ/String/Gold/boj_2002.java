package BOJ.String.Gold;

import java.io.*;
import java.util.HashMap;

public class boj_2002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        HashMap<String, Integer> hash1 = new HashMap<>();
        HashMap<String, Integer> hash2 = new HashMap<>();
        int N = Integer.parseInt(br.readLine());

        for(int i = 1; i <= N; i++) {
            hash1.put(br.readLine(), i);
        }

        int answer = 0;
        int num = hash1.get(br.readLine());
        for(int i = 2; i <= N; i++) {
            int tmp = hash1.get(br.readLine());
            if(num > tmp) {
                answer++;
            }
            num = tmp;
        }

        sb.append(answer).append("\n");
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
