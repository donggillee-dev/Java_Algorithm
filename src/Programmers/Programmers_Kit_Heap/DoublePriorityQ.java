package Programmers.Programmers_Kit_Heap;
import java.util.*;

public class DoublePriorityQ {
    public static void main(String[] args) {
        String[] operation = {"I 7","I 5","I -5","D -1"};
        Solution sol = new Solution();
        int[] answer = sol.solution(operation);

        System.out.println(Arrays.toString(answer));
    }
    private static class Solution {
        public int[] solution(String[] operations) {
            int[] answer = new int[2];

            Queue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());
            Queue<Integer> minQueue = new PriorityQueue<>();

            for(String operation : operations) {
                String[] cmd = operation.split(" ");
                if(cmd[0].equals("I")) {
                    maxQueue.add(Integer.parseInt(cmd[1]));
                    minQueue.add(Integer.parseInt(cmd[1]));
                } else if(!maxQueue.isEmpty()){
                    if(cmd[1].equals("1")) deleteElement(minQueue, maxQueue.poll());
                    else deleteElement(maxQueue, minQueue.poll());
                }
            }
            answer[0] = maxQueue.isEmpty() ? 0 : maxQueue.poll();
            answer[1] = minQueue.isEmpty() ? 0 : minQueue.poll();
            return answer;
        }
        public void deleteElement(Queue<Integer> queue, int num) {
            List<Integer> temp = new ArrayList<>();
            while(!queue.isEmpty()) {
                int extract = queue.poll();
                if(extract == num) break;
                temp.add(extract);
            }
            queue.addAll(temp);
        }
    }
}


