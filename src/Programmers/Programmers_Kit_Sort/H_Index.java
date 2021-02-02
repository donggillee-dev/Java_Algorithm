package Programmers.Programmers_Kit_Sort;
import java.util.*;

public class H_Index {
    public static void main(String[] args) {
        int[] citations = {3, 0, 6, 1, 5};
        Solution sol = new Solution();
        System.out.println(sol.solution(citations));
    }
    private static class Solution {
        public int solution(int[] citations) {
            int answer = 0;
            Queue<Integer> Q = new LinkedList<>();
            Queue<Integer> under = new LinkedList<>();

            Arrays.sort(citations);

            for(int citation : citations) {
                Q.add(citation);
            }

            for(int i = 0; i <= 10000; i++) {
                int same = 0;
                while(!Q.isEmpty()) {
                    int citation = Q.peek();
                    if(citation <= i) {
                        if(citation == i) same++;
                        under.offer(Q.poll());
                    } else {
                        break;
                    }
                }
                if(under.size() <= i && (Q.size() + same) >= i) answer = i;
            }

            return answer;
        }
    }
}
