package Programmers.Programmers_2021_Second_LinePlus;

//Logic
//우선순위큐 두개로 스케줄러를 구현한다
//작업요청큐, 작업대기큐 이렇게 두개 존재
//작업요청큐의 맨앞에 있는 프로세스의 중요도

import java.util.*;

public class Solution3 {
    private static class Info implements Comparable<Info> {
        int start, dur, cate, prior;

        public Info(int start, int dur, int cate, int prior) {
            this.start = start;
            this.dur = dur;
            this.cate = cate;
            this.prior = prior;
        }

        @Override
        public int compareTo(Info o) {
            return this.start - o.start;
        }
    }

    private static void initPq(int[][] jobs, PriorityQueue<Info> pq) {
        for (int[] job : jobs) {
            pq.add(new Info(job[0], job[1], job[2], job[3]));
        }
    }

    private static int[] solution(int[][] jobs) {
        int time = 0, curJob = 0;
        Deque<Integer> dq = new LinkedList<>();
        PriorityQueue<Info> reqQ = new PriorityQueue<>();
        PriorityQueue<Info> waitQ = new PriorityQueue<>();
        HashMap<Integer, Integer> totPriorHash = new HashMap<>();
        List<Map.Entry<Integer, Integer>> listEntiry;

        initPq(jobs, reqQ);

        while (!reqQ.isEmpty()) {
            if (waitQ.isEmpty()) {
                if (reqQ.peek().start >= time) {
                    Info inf = reqQ.poll();

                    curJob = inf.cate;
                    time += (inf.start + inf.dur);
                } else {
                    while (!reqQ.isEmpty() && reqQ.peek().start < time) {
                        Info inf = reqQ.poll();

                        if(inf.cate == curJob && inf.start == time) {
                            curJob = inf.cate;
                            time += inf.dur;

                            continue;
                        }
                        waitQ.add(inf);

                        totPriorHash.put(inf.cate, totPriorHash.getOrDefault(inf.cate, 0) + inf.prior);
                    }
                }
            } else { //대기 큐가 비어있지 않다면
                //연속된 작업을 처리할 수 있는지 확인
                if (!reqQ.isEmpty() && reqQ.peek().cate == curJob && reqQ.peek().start == time) {
                    Info inf = reqQ.poll();
                    curJob = inf.cate;
                    time += inf.dur;
                } else { //대기큐에 있는 것들 중에 중요도의 합이 가장 큰거

                    while (!reqQ.isEmpty() && reqQ.peek().start < time) {
                        Info inf = reqQ.poll();
                        waitQ.add(inf);

                        totPriorHash.put(inf.cate, totPriorHash.getOrDefault(inf.cate, 0) + inf.prior);
                    }

                    listEntiry = new ArrayList<Map.Entry<Integer, Integer>>(totPriorHash.entrySet());

                    Collections.sort(listEntiry, new Comparator<Map.Entry<Integer, Integer>>() {
                        public int compare(Map.Entry<Integer, Integer> obj1, Map.Entry<Integer, Integer> obj2) {
                            return obj2.getValue().compareTo(obj1.getValue());
                        }
                    });

                    PriorityQueue<Info> tmpQ = new PriorityQueue<>();
                    curJob = listEntiry.get(0).getKey();

                    while (!waitQ.isEmpty()) {
                        Info inf = waitQ.poll();
                        if (inf.cate == curJob) {
                            time += inf.dur;
                            totPriorHash.put(curJob, totPriorHash.get(curJob) - inf.prior);
                            waitQ.poll();
                            break;
                        } else {
                            tmpQ.add(inf);
                        }
                    }

                    waitQ.addAll(tmpQ);
                }
            }

            if(curJob == 0) continue;

            if (!dq.isEmpty()) {
                if (dq.peekLast() != curJob) {
                    dq.addLast(curJob);
                }
            } else {
                dq.addLast(curJob);
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                }

                return o2[1] - o1[1];
            }
        });

        for (int key : totPriorHash.keySet()) {
            pq.add(new int[]{key, totPriorHash.get(key)});
        }

        PriorityQueue<Info> tmp = new PriorityQueue<>();

        while (true) {
            while (!waitQ.isEmpty()) {

                if(waitQ.peek().start == time && waitQ.peek().cate == curJob) {
                    time += waitQ.peek().dur;
                    PriorityQueue<int[]> tmpPq = new PriorityQueue<>();

                    while(!pq.isEmpty()) {
                        int[] elem = pq.peek();

                        if(elem[0] == waitQ.peek().cate) {
                            elem[1] -= waitQ.peek().prior;
                            break;
                        } else {
                            tmpPq.add(pq.poll());
                        }
                    }

                    pq.addAll(tmpPq);
                    continue;
                }

                Info inf = waitQ.poll();

                if (inf.cate == pq.peek()[0]) {
                    curJob = inf.cate;

                    int[] elem = pq.poll();
                    elem[1] -= inf.prior;

                    pq.add(elem);
                    break;
                } else {
                    tmp.add(inf);
                }
            }

            if (!dq.isEmpty()) {
                if (dq.peekLast() != curJob) {
                    dq.addLast(curJob);
                }
            } else {
                dq.addLast(curJob);
            }

            if (tmp.isEmpty() && waitQ.isEmpty()) break;
        }
        int size = dq.size();
        int[] answer = new int[size];
        for(int i = 0; i < size; i++) {
            answer[i] = dq.pollFirst();
        }
        return answer;
    }

    public static void main(String[] args) {
//        int[][] arr = {{1, 5, 2, 3}, {2, 2, 3, 2}, {3, 1, 3, 3}, {5, 2, 1, 5}, {7, 1, 1, 1}, {9, 1, 1, 1}, {10, 2, 2, 9}};
//        int[][] arr = {{1, 2, 1, 5}, {2, 1, 2, 100}, {3, 2, 1, 5}, {5, 2, 1, 5}};
        int[][] arr = {{0, 5, 1, 1}, {2, 4, 3, 3}, {3, 4, 4, 5}, {5, 2, 3, 2}};
        solution(arr);
    }
}
