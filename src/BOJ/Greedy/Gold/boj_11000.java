package BOJ.Greedy.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//Logic
//강의 시간에 대한 정보들을 정렬한다(1순위 시작시간, 2순위 끝나는 시간)
//정렬된 강의를 하나씩 뽑아와서 q에 넣는다
//q에는 강의에 대한 end시간이 저장된다
//넣어줄때 가장 빨리 끝나는 시간과 현재 pq에 존재한 시작시간이 빠른 강의가 겹치지 않는다면 걔를 빼고 pq의 end를 넣어준다
//겹치는 경우에는 강의실 하나 더 늘려야하므로 q에 end시간 그냥 넣어줌
//현식이 풀이를 보고 푼거니까 다시 풀어보자!!

public class boj_11000 {
    private static int n;

    private static class Info implements Comparable<Info> {
        int start, end;

        public Info(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Info o) {
            if (this.start == o.start) {
                return this.end - o.end;
            }

            return this.start - o.start;
        }
    }

    private static int stoi(String str) {
        return Integer.parseInt(str);
    }

    private static PriorityQueue<Info> input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        PriorityQueue<Info> pq = new PriorityQueue<>();
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            pq.add(new Info(stoi(stk.nextToken()), stoi(stk.nextToken())));
        }

        return pq;
    }

    private static int solution(PriorityQueue<Info> pq) {
        PriorityQueue<Integer> q = new PriorityQueue<>();

        q.add(pq.poll().end);

        while(!pq.isEmpty()) {
            Info elem = pq.poll();

            if(q.peek() <= elem.start) {
                q.poll();
            }

            q.add(elem.end);
        }

        return q.size();
    }

    public static void main(String[] args) throws IOException {
        PriorityQueue<Info> pq = null;

        try {
            pq = input();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(solution(pq));
    }
}
