package Programmers.Programmers_2021_First_LinePlus.Part1;

import java.io.*;
import java.util.*;

public class Solution3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();


        Solution.solution(new int[]{3, 2, 1}, new int[]{1, 3, 2});
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static class Solution {
        private static int[] solution(int[] enter, int[] leave) {
            int[] answer = new int[enter.length];
            Deque<Integer> Q = new LinkedList<>();
            Deque<Integer> tmpQ = new LinkedList<>();

            for(int in : enter) {
                Q.add(in);
            }

            for(int out : leave) {
                while(Q.peekFirst() != out) {
                    int tmp = Q.pollFirst();
                    answer[tmp - 1]++;
                    tmpQ.addLast(tmp);
                }
                Object[] arr = tmpQ.toArray();
                int size = arr.length;
                for(int i = 0; i < size - 1; i++) {
                    for(int j = i + 1; j < size; j++) {
                        answer[(int)arr[i]- 1]++;
                        answer[(int)arr[j] - 1]++;
                    }
                }

                int tmp = Q.pollFirst();
                answer[tmp - 1] += tmpQ.size();
                while(!tmpQ.isEmpty()) {
                    Q.addFirst(tmpQ.pollLast());
                }
            }
            return answer;
        }
    }
}
