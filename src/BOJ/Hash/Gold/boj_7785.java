package BOJ.Hash.Gold;

import java.io.*;
import java.util.*;

//TreeMap에 reverseorder를 넣어주어서 정렬을 또 해줄 필요가 없음
//
//        풀이 시간 : 15분

public class boj_7785 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        Map<String, String> hash = new TreeMap<>(Comparator.reverseOrder());
        int N = Integer.parseInt(br.readLine());
        StringTokenizer stk;
        while(N --> 0) {
            stk = new StringTokenizer(br.readLine());
            String name = stk.nextToken();
            String log = stk.nextToken();
            if(log.equals("leave"))
                hash.remove(name);
            else
                hash.put(name, log);
        }

        for (String key : hash.keySet()) {
            sb.append(key).append("\n");
        }
        bw.write(String.valueOf(sb));
        bw.close();
        br.close();
    }
}
