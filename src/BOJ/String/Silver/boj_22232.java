package BOJ.String.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//Logic
//정렬 기준을 나누어서 우선순위큐를 만들면된다
//초기에는 그때그떄 split하려고했는데 오버헤드가 너무 심함

public class boj_22232 {
    private static HashMap<String, Boolean> set = new HashMap<>();

    private static class Word implements Comparable<Word> {
        String filename, extension;

        public Word(String filename, String extension) {
            this.filename = filename;
            this.extension = extension;
        }

        @Override
        public int compareTo(Word o) {
            if(this.filename.compareTo(o.filename) == 0) {
                if(set.get(this.extension) != null && set.get(o.extension) == null) {
                    return -1;
                } else if(set.get(this.extension) == null && set.get(o.extension) != null) {
                    return 1;
                } else {
                    return this.extension.compareTo(o.extension);
                }
            }

            return this.filename.compareTo(o.filename);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        Queue<Word> q = new LinkedList<>();
        PriorityQueue<Word> pq = new PriorityQueue<>();

        for(int i = 0; i < n; i++) {
            String[] arr = br.readLine().split("\\.");
            q.add(new Word(arr[0], arr[1]));
        }

        for(int i = 0; i < m; i++) {
            set.put(br.readLine(), false);
        }

        while(!q.isEmpty()) {
            pq.add(q.poll());
        }

        while(!pq.isEmpty()) {
            Word elem = pq.poll();
            sb.append(elem.filename + "." + elem.extension).append("\n");
        }

        System.out.print(sb);
    }
}
