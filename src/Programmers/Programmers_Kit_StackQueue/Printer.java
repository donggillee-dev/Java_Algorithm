package Programmers.Programmers_Kit_StackQueue;
import java.util.*;

public class Printer {
    public static void main(String[] args) {
        int[] priorities = {1, 1, 9, 1, 1};
        int location = 5;
        Solution sol = new Solution();
        System.out.println(sol.solution(priorities, location));
    }
    private static class Solution {
        public int solution(int[] priorities, int location) {
            int answer = 0;
            int n_th = 0;
            Queue<Document> Spool = new LinkedList<>();
            Queue<Integer> Q = new PriorityQueue<>(Collections.reverseOrder());

            for(int i = 0; i < priorities.length; i++) {
                Spool.offer(new Document(i, priorities[i]));
                Q.offer(priorities[i]);
            }
            loop1:while(!Q.isEmpty()) {
                int priVal = Q.poll();
                n_th++;
                loop2:while(!Spool.isEmpty()) {
                    Document Doc = Spool.poll();

                    if(priVal == Doc.priority) {
                        if(location == Doc.pos) {
                            answer = n_th;
                            break loop1;
                        }
                        break loop2;
                    } else {
                        Spool.offer(Doc);
                    }
                }
            }

            return answer;
        }
    }
    private static class Document {
        int pos;
        int priority;
        public Document(int pos, int pri) {
            this.pos = pos;
            this.priority = pri;
        }
    }
}


