package BOJ.Hash.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//Logic
//문제 이해가 어려웠던 문제
//순서를 재배치하는 것이므로 그 방식을 역으로 엮어서 생각

//풀이 시간 : 25분

public class boj_9322 {
    private static class Info implements Comparable<Info> {
        String token;
        int idx;

        public Info(String token, int idx) {
            this.token = token;
            this.idx = idx;
        }

        @Override
        public int compareTo(Info o) {
            return this.idx - o.idx;
        }
    }
    private static HashMap<String, Integer> makeFirstPublicKey(int length, String str) {
        StringTokenizer stk = new StringTokenizer(str);
        HashMap<String, Integer> hash = new HashMap<>();

        for(int i = 0; i < length; i++) {
            hash.put(stk.nextToken(), i);
        }

        return hash;
    }

    private static HashMap<Integer, Integer> makeIdxHashByKey(int length, HashMap<String, Integer> firstKey, String str) {
        StringTokenizer stk = new StringTokenizer(str);
        HashMap<Integer, Integer> hash = new HashMap<>();

        for(int i = 0; i < length; i++) {
            hash.put(i, firstKey.get(stk.nextToken()));
        }

        return hash;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        PriorityQueue<Info> pq = new PriorityQueue();

        while(t --> 0) {
            int length = Integer.parseInt(br.readLine());
            HashMap<String, Integer> firstKey = makeFirstPublicKey(length, br.readLine());
            HashMap<Integer, Integer> idxHash = makeIdxHashByKey(length, firstKey, br.readLine());

            StringTokenizer stk = new StringTokenizer(br.readLine());

            for(int i = 0; i < length; i++) {
                String token = stk.nextToken();
                int idx = idxHash.get(i);

                pq.add(new Info(token, idx));
            }

            while(!pq.isEmpty()) {
                sb.append(pq.poll().token).append(" ");
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }
}
