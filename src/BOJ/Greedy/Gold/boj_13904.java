package BOJ.Greedy.Gold;

//Logic
//이전에 풀었던 그리디 문제와 비슷
//점수 순으로 우선 정렬하고 선택한 과제를 마감일로부터 가장 가까운 날에 삽입?
//풀이 시간 : 6분

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_13904 {
    private static class Info implements Comparable<Info>{ //과제 클래스
        int d, w;

        public Info(int d, int w) {
            this.d = d;
            this.w = w;
        }

        @Override //우선순위큐에 넣기 위해 정렬함수 implement
        public int compareTo(Info o) {
            return o.w - this.w;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Info> pq = new PriorityQueue<>();
        StringTokenizer stk;

        int ans = 0;
        boolean[] day = new boolean[1001]; //날짜에 과제 선택 여부를 위한 자료구조

        int n = Integer.parseInt(br.readLine());

        while(n --> 0) { //입력부
            stk = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(stk.nextToken());
            int w = Integer.parseInt(stk.nextToken());

            pq.add(new Info(d, w));
        }

        while(!pq.isEmpty()) { //로직
            Info elem = pq.poll();

            while(elem.d --> 0) { //해당 날짜부터 1일까지 역순으로 탐색해서
                if(!day[elem.d]) { //과제 처리할 수 있는 날에 처리 후 점수 획득
                    day[elem.d] = true;
                    ans += elem.w;
                    break;
                }
            }
        }

        System.out.println(ans);
    }
}
