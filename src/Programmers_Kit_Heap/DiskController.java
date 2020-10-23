package Programmers_Kit_Heap;
import java.util.*;

public class DiskController {
    public static void main(String[] args) {
        int[][] jobs = {{0, 3}, {1, 9}, {2, 6}};
        Solution sol = new Solution();
        int answer = sol.solution(jobs);

        System.out.println(answer);
        return;
    }
    private static class Solution {
        public int solution(int[][] jobs) {
            LinkedList<Info> worklist = new LinkedList<>(); //모든 스케쥴 담은 작업리스트
            PriorityQueue<Info> processing = new PriorityQueue<>(new Comparator<Info>() { //프로세싱 큐
                @Override
                public int compare(Info o1, Info o2) {
                    return o1.needTime - o2.needTime;
                }
            });

            for(int[] job : jobs) {
                worklist.offer(new Info(job[0], job[1]));
            }

            Collections.sort(worklist, new Comparator<Info>() { //작업리스트는 input된 time 순서대로
                @Override
                public int compare(Info o1, Info o2) {
                    return o1.inTime - o2.inTime;
                }
            });
            //프로세실 큐는 요청하는 작업량 오름차순으로 정렬된 priorityQueued임.
            //작업리스트에서 현재 시간보다 일찍 요청을 한 리스트들을 뽑아 프로세싱 큐에 넣어줌
            //프로세싱 큐에서 하나 처리함 -> 시간은 또 처리한 만큼 더해줌
            //다시 현재시간보다 일찍 요청을 한 리스트들을 뽑아 프로세싱 큐에 넣어줌

            //현재시간보다 일찍 요청을 한 작업이 작업리스트에서 보이지 않으면 1초씩 현재 시간을 늘려줌

            int answer = 0;
            int cnt = 0;
            int time = worklist.peek().inTime;

            while(cnt < jobs.length) {
                while(!worklist.isEmpty() && worklist.peek().inTime <= time) processing.offer(worklist.poll());

                if(!processing.isEmpty()) {
                    Info proc = processing.poll();
                    time += proc.needTime;
                    answer += time - proc.inTime;
                    cnt++;
                } else
                    time++;
            }
            return answer / cnt;
        }
    }
    private static class Info {
        int inTime;
        int needTime;
        public Info(int it, int nt) {
            this.inTime = it;
            this.needTime = nt ;
        }
    }
}
