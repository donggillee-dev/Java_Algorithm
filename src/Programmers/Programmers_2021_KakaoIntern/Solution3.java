package Programmers.Programmers_2021_KakaoIntern;

import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.*;

public class Solution3 {
    public static void main(String[] args) throws IOException {
        System.out.println(solution(8, 2, new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"}));
    }

    private static String solution(int n, int k, String[] cmd) {
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();
        boolean[] ans = new boolean[n];
        Stack<Integer> S = new Stack<>();
        Deque<Integer> left = new LinkedList<>();
        Deque<Integer> right = new LinkedList<>();

        for (int i = 0; i <= k; i++) {
            left.addLast(i);
        }

        for (int i = k + 1; i < n; i++) {
            right.addLast(i);
        }

        for (String c : cmd) {
            stk = new StringTokenizer(c);
            char ch = stk.nextToken().charAt(0);
            int x;
            switch (ch) {
                case 'D':
                    x = Integer.parseInt(stk.nextToken());

                    for (int i = 0; i < x; i++) {
                        left.addLast(right.pollFirst());
                    }
                    break;
                case 'C':
                    if (left.size() == 0) {
                        S.push(right.pollFirst());
                    } else {
                        S.push(left.pollLast());
                    }

                    if (right.size() != 0) {
                        left.addLast(right.pollFirst());
                    }
                    break;
                case 'U':
                    x = Integer.parseInt(stk.nextToken());

                    for (int i = 0; i < x; i++) {
                        right.addFirst(left.pollLast());
                    }
                    break;
                case 'Z':
                    if (left.size() == 0) {
                        Deque<Integer> tmp = new LinkedList<>();
                        while (!right.isEmpty() && right.peekFirst() < S.peek()) {
                            tmp.addLast(right.pollFirst());
                        }

                        right.addFirst(S.pop());

                        while (!tmp.isEmpty()) {
                            right.addFirst(tmp.pollLast());
                        }
                    } else if (right.size() == 0) {
                        Deque<Integer> tmp = new LinkedList<>();
                        while (!left.isEmpty() && left.peekLast() > S.peek()) {
                            tmp.addLast(left.pollLast());
                        }

                        left.addLast(S.pop());

                        while (!tmp.isEmpty()) {
                            left.addLast(tmp.pollFirst());
                        }
                    } else if(left.isEmpty() && right.isEmpty()){
                        left.addLast(S.pop());
                    } else {
                        if (left.peekLast() < S.peek()) {
                            Deque<Integer> tmp = new LinkedList<>();
                            while (!right.isEmpty() && right.peekFirst() > S.peek()) {
                                tmp.addLast(right.pollFirst());
                            }
                            right.addFirst(S.pop());

                            while (!tmp.isEmpty()) {
                                right.addFirst(tmp.pollLast());
                            }
                        } else if (left.peekLast() > S.peek()) {
                            Deque<Integer> tmp = new LinkedList<>();

                            while (!left.isEmpty() && left.peekLast() > S.peek()) {
                                tmp.addFirst(left.pollLast());
                            }
                            left.addLast(S.pop());

                            while (!tmp.isEmpty()) {
                                left.addLast(tmp.pollFirst());
                            }
                        }
                    }
                    break;
            }
        }

        while (!left.isEmpty()) {
            ans[left.pollFirst()] = true;
        }
        while (!right.isEmpty()) {
            ans[right.pollFirst()] = true;
        }

        for (int i = 0; i < n; i++) {
            if (ans[i]) {
                sb.append('O');
            } else {
                sb.append('X');
            }
        }
        return sb.toString();
    }
}
