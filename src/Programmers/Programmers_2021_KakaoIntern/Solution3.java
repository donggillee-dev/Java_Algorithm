package Programmers.Programmers_2021_KakaoIntern;

import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.*;

public class Solution3 {
    public static void main(String[] args) throws IOException {
        System.out.println(solution(8, 2, new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"}));
//        System.out.println(solution(8, 2, new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z"}));
    }

    private static String getAns(int size, Stack<Integer> s) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < size; i++) {
            sb.append('O');
        }

        while(!s.isEmpty()) {
            int idx = s.pop();
            sb.insert(idx, 'X');
        }

        return sb.toString();
    }
    private static String solution(int n, int k, String[] cmd) {
        Stack<Integer> s = new Stack<>();
        int size = n;
        for (String c : cmd) {

            switch (c.charAt(0)) {
                case 'U':
                    k -= Integer.parseInt(c.substring(2));
                    break;
                case 'D':
                    k += Integer.parseInt(c.substring(2));
                    break;
                case 'C':
                    s.push(k);
                    size--;
                    if(k >= size) {
                        k -= 1;
                    }
                    break;
                case 'Z':
                    int num = s.pop();
                    if(num <= k) k++;
                    size++;
                    break;
            }
        }

        return getAns(size, s);
    }
}
